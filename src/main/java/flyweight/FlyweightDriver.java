package flyweight;

import java.io.IOException;

public class FlyweightDriver {
    public static void main(String[] args) throws IOException {
        CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
        Document document = new Document(factory);

        document.addText("Hello", "Arial", 12, "Red");
        document.addText("World", "Calibri", 14, "Blue");
        document.addText("CS", "Verdana", 16, "Black");
        document.addText("5800", "Arial", 16, "Blue");
        System.out.println("Created: " + document.getText());

        document.editCharacter(0, 'H', "Verdana", 16, "Black");
        System.out.println("Edited the first H to Verdana, 16, Black");

        document.save("HelloWorldCS5800.txt");
        System.out.println("Saved to HelloWorldCS5800.txt");

        Document loaded = new Document(factory);
        loaded.load("HelloWorldCS5800.txt");
        System.out.println();
        System.out.println("===== Loaded from HelloWorldCS5800.txt =====");
        loaded.printDocument();

        System.out.println();
        System.out.println(loaded.getText().length() + " characters share "
                + factory.getCount() + " properties objects");
    }
}
