package flyweight;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class FlyweightDriverTest {

    // main

    @Test
    void mainRunsWithoutErrors() {
        assertDoesNotThrow(() -> FlyweightDriver.main(new String[0]));
    }
}
