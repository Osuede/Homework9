package flyweight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Document {
    private final List<TextCharacter> characters = new ArrayList<>();
    private final CharacterPropertiesFactory factory;

    public Document(CharacterPropertiesFactory factory) {
        this.factory = factory;
    }

    public void addCharacter(char value, String font, int size, String color) {
        CharacterProperties properties = factory.getProperties(font, size, color);
        characters.add(new TextCharacter(value, properties));
    }

    public void addText(String text, String font, int size, String color) {
        for (char value : text.toCharArray()) {
            addCharacter(value, font, size, color);
        }
    }

    public void editCharacter(int index, char value, String font, int size, String color) {
        CharacterProperties properties = factory.getProperties(font, size, color);
        characters.set(index, new TextCharacter(value, properties));
    }

    public TextCharacter getCharacter(int index) {
        return characters.get(index);
    }

    public String getText() {
        StringBuilder text = new StringBuilder();
        for (TextCharacter character : characters) {
            text.append(character.getValue());
        }
        return text.toString();
    }

    public void save(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        for (TextCharacter character : characters) {
            CharacterProperties properties = character.getProperties();
            lines.add(character.getValue() + "," + properties.getFont() + ","
                    + properties.getSize() + "," + properties.getColor());
        }
        Files.write(Path.of(filePath), lines);
    }

    public void load(String filePath) throws IOException {
        characters.clear();
        for (String line : Files.readAllLines(Path.of(filePath))) {
            char value = line.charAt(0);
            String[] parts = line.substring(2).split(",");
            addCharacter(value, parts[0], Integer.parseInt(parts[1]), parts[2]);
        }
    }

    public void printDocument() {
        for (TextCharacter character : characters) {
            CharacterProperties properties = character.getProperties();
            System.out.println(character.getValue() + " - " + properties.getFont() + ", "
                    + properties.getSize() + ", " + properties.getColor());
        }
    }
}
