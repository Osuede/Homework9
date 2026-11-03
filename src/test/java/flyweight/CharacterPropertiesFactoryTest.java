package flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class CharacterPropertiesFactoryTest {

    private final CharacterPropertiesFactory factory = new CharacterPropertiesFactory();

    // getProperties

    @Test
    void getPropertiesReturnsTheRequestedValues() {
        CharacterProperties properties = factory.getProperties("Calibri", 14, "Blue");
        assertNotNull(properties, "the factory should return a properties object");
        assertEquals("Calibri", properties.getFont());
        assertEquals(14, properties.getSize());
        assertEquals("Blue", properties.getColor());
    }

    @Test
    void samePropertiesReturnTheSameSharedObject() {
        CharacterProperties first = factory.getProperties("Arial", 12, "Red");
        CharacterProperties second = factory.getProperties("Arial", 12, "Red");
        assertNotNull(first, "the factory should return a properties object");
        assertSame(first, second, "the same font, size and color should give back the SAME object");
    }

    @Test
    void differentPropertiesReturnDifferentObjects() {
        CharacterProperties red = factory.getProperties("Arial", 12, "Red");
        CharacterProperties blue = factory.getProperties("Arial", 12, "Blue");
        assertNotNull(red, "the factory should return a properties object");
        assertNotSame(red, blue, "a different color should give a different object");
    }

    // getCount

    @Test
    void newFactoryHasNoProperties() {
        assertEquals(0, factory.getCount());
    }

    @Test
    void countOnlyGrowsForNewCombinations() {
        factory.getProperties("Arial", 12, "Red");
        factory.getProperties("Arial", 12, "Red");
        factory.getProperties("Arial", 12, "Red");
        factory.getProperties("Verdana", 16, "Black");
        assertEquals(2, factory.getCount(), "3 identical requests + 1 different one should create only 2 objects");
    }
}
