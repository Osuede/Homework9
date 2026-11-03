package flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class DocumentTest {

    private final CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
    private final Document document = new Document(factory);
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // JUnit gives each test its own empty temporary folder and deletes it afterwards
    @TempDir
    Path tempDir;

    @BeforeEach
    void captureOutput() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(originalOut);
    }

    // addCharacter

    @Test
    void addCharacterAddsToTheEnd() {
        document.addCharacter('H', "Arial", 12, "Red");
        document.addCharacter('i', "Arial", 12, "Red");
        assertEquals("Hi", document.getText());
    }

    @Test
    void charactersThatLookTheSameShareOneObject() {
        document.addCharacter('H', "Arial", 12, "Red");
        document.addCharacter('i', "Arial", 12, "Red");
        assertSame(document.getCharacter(0).getProperties(), document.getCharacter(1).getProperties(),
                "both characters should point to the same shared properties object");
    }

    // addText

    @Test
    void addTextAddsEveryCharacter() {
        document.addText("Hello", "Calibri", 14, "Blue");
        assertEquals("Hello", document.getText());
    }

    @Test
    void addTextCreatesOnlyOnePropertiesObject() {
        document.addText("Hello", "Calibri", 14, "Blue");
        assertEquals(1, factory.getCount(), "5 characters that look the same should share 1 object");
    }

    // editCharacter

    @Test
    void editCharacterChangesTheLetter() {
        document.addText("Hallo", "Arial", 12, "Red");
        document.editCharacter(1, 'e', "Arial", 12, "Red");
        assertEquals("Hello", document.getText());
    }

    @Test
    void editCharacterChangesTheProperties() {
        document.addText("Hi", "Arial", 12, "Red");
        document.editCharacter(0, 'H', "Verdana", 16, "Black");
        assertEquals("Verdana", document.getCharacter(0).getProperties().getFont());
    }

    @Test
    void editingOneCharacterDoesNotChangeTheOthers() {
        document.addText("Hi", "Arial", 12, "Red");
        document.editCharacter(0, 'H', "Verdana", 16, "Black");
        assertEquals("Arial", document.getCharacter(1).getProperties().getFont(),
                "the other character should still use the Arial properties");
    }

    // getCharacter

    @Test
    void getCharacterReturnsTheCharacterAtThatPosition() {
        document.addText("Hi", "Arial", 12, "Red");
        assertEquals('i', document.getCharacter(1).getValue());
    }

    // getText

    @Test
    void newDocumentHasNoText() {
        assertEquals("", document.getText());
    }

    // save

    @Test
    void saveWritesOneLinePerCharacter() throws IOException {
        document.addText("Hi", "Arial", 12, "Red");
        Path file = tempDir.resolve("doc.txt");
        document.save(file.toString());
        assertTrue(Files.exists(file), "save should create the file");
        assertEquals(List.of("H,Arial,12,Red", "i,Arial,12,Red"), Files.readAllLines(file));
    }

    // load

    @Test
    void loadReadsTheLettersBack() throws IOException {
        Path file = tempDir.resolve("doc.txt");
        Files.write(file, List.of("H,Arial,12,Red", "i,Calibri,14,Blue"));
        document.load(file.toString());
        assertEquals("Hi", document.getText());
    }

    @Test
    void loadRestoresEachCharactersProperties() throws IOException {
        Path file = tempDir.resolve("doc.txt");
        Files.write(file, List.of("H,Arial,12,Red", "i,Calibri,14,Blue"));
        document.load(file.toString());
        CharacterProperties properties = document.getCharacter(1).getProperties();
        assertEquals("Calibri", properties.getFont());
        assertEquals(14, properties.getSize());
        assertEquals("Blue", properties.getColor());
    }

    @Test
    void loadReplacesWhatWasThereBefore() throws IOException {
        document.addText("Old", "Arial", 12, "Red");
        Path file = tempDir.resolve("doc.txt");
        Files.write(file, List.of("H,Arial,12,Red", "i,Arial,12,Red"));
        document.load(file.toString());
        assertEquals("Hi", document.getText());
    }

    @Test
    void savedThenLoadedDocumentReusesTheSharedObjects() throws IOException {
        document.addText("Hello", "Arial", 12, "Red");
        document.addText("World", "Calibri", 14, "Blue");
        Path file = tempDir.resolve("doc.txt");
        document.save(file.toString());

        Document loaded = new Document(factory);
        loaded.load(file.toString());

        assertEquals("HelloWorld", loaded.getText());
        assertEquals(2, factory.getCount(), "loading should reuse the 2 existing objects, not create new ones");
    }

    // printDocument

    @Test
    void printDocumentShowsEachLetterWithItsProperties() {
        document.addText("Hi", "Verdana", 16, "Black");
        document.printDocument();
        String printed = output.toString();
        assertEquals(2, printed.lines().count(), "should print one line per character");
        assertTrue(printed.contains("H"), "should print the letter H");
        assertTrue(printed.contains("Verdana") && printed.contains("16") && printed.contains("Black"),
                "should print the font, size and color");
    }
}
