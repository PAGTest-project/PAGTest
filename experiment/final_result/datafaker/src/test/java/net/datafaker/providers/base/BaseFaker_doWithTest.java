
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.concurrent.Callable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseFaker_doWithTest {

    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseFaker();
    }

    @Test
    public void testDoWithLocaleSuccess() throws Exception {
        Callable<String> callable = () -> "Test Result";
        Locale locale = Locale.FRENCH;

        String result = faker.doWith(callable, locale);

        assertEquals("Test Result", result);
    }

    @Test
    public void testDoWithLocaleException() {
        Callable<String> callable = () -> {
            throw new Exception("Test Exception");
        };
        Locale locale = Locale.FRENCH;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            faker.doWith(callable, locale);
        });

        assertEquals("Test Exception", exception.getCause().getMessage());
    }
}
