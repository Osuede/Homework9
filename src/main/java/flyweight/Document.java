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
    }

    public void load(String filePath) throws IOException {

    }

    public void printDocument() {

    }
}
