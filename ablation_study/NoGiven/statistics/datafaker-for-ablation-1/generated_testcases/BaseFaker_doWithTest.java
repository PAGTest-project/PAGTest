
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Callable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BaseFaker_doWithTest {
    private BaseFaker faker;
    private Random random;

    @BeforeEach
    final void before() {
        random = new Random();
        faker = new BaseFaker(random);
    }

    @Test
    void testDoWithLocaleChange() throws Exception {
        Locale originalLocale = faker.getContext().getLocale();
        Locale newLocale = Locale.FRANCE;
        String expectedResult = "Test Result";

        Callable<String> callable = () -> {
            assertThat(faker.getContext().getLocale()).isEqualTo(newLocale);
            return expectedResult;
        };

        String result = faker.doWith(callable, newLocale);
        assertThat(result).isEqualTo(expectedResult);
        assertThat(faker.getContext().getLocale()).isEqualTo(originalLocale);
    }

    @Test
    void testDoWithRuntimeException() {
        Locale newLocale = Locale.GERMANY;

        Callable<String> callable = () -> {
            throw new RuntimeException("Test Exception");
        };

        assertThatThrownBy(() -> faker.doWith(callable, newLocale))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Test Exception");
    }

    @Test
    void testDoWithCheckedException() {
        Locale newLocale = Locale.ITALY;

        Callable<String> callable = () -> {
            throw new Exception("Test Checked Exception");
        };

        assertThatThrownBy(() -> faker.doWith(callable, newLocale))
            .isInstanceOf(RuntimeException.class)
            .hasCauseInstanceOf(Exception.class)
            .hasMessageContaining("Test Checked Exception");
    }
}
