package proxy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ProxyDriverTest {

    // main

    @Test
    void mainRunsWithoutErrors() {
        assertDoesNotThrow(() -> ProxyDriver.main(new String[0]));
    }
}
