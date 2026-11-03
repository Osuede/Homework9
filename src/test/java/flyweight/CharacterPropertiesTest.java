package flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CharacterPropertiesTest {

    private final CharacterProperties properties = new CharacterProperties("Arial", 12, "Red");

    // getFont

    @Test
    void getFontReturnsTheFont() {
        assertEquals("Arial", properties.getFont());
    }

    // getSize

    @Test
    void getSizeReturnsTheSize() {
        assertEquals(12, properties.getSize());
    }

    // getColor

    @Test
    void getColorReturnsTheColor() {
        assertEquals("Red", properties.getColor());
    }
}
