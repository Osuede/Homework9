package flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class TextCharacterTest {

    private final CharacterProperties properties = new CharacterProperties("Arial", 12, "Red");
    private final TextCharacter character = new TextCharacter('H', properties);

    // getValue

    @Test
    void getValueReturnsTheLetter() {
        assertEquals('H', character.getValue());
    }

    // getProperties

    @Test
    void getPropertiesReturnsTheSharedObject() {
        assertSame(properties, character.getProperties());
    }
}
