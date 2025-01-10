
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.concurrent.Callable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BaseFaker_doWithTest {
    private BaseFaker baseFaker;

    @BeforeEach
    public void setUp() {
        baseFaker = new BaseFaker();
    }

    @Test
    public void testDoWithLocaleSuccess() throws Exception {
        Callable<String> callable = () -> baseFaker.resolve("name.first_name");
        String result = baseFaker.doWith(callable, Locale.FRANCE);
        assertThat(result).isNotNull();
    }

    @Test
    public void testDoWithLocaleException() {
        Callable<String> callable = () -> {
            throw new Exception("Test exception");
        };
        assertThatThrownBy(() -> baseFaker.doWith(callable, Locale.FRANCE))
            .isInstanceOf(RuntimeException.class)
            .hasCauseInstanceOf(Exception.class);
    }

    @Test
    public void testDoWithLocaleFinallyBlock() throws Exception {
        Locale initialLocale = baseFaker.getContext().getLocale();
        Callable<String> callable = () -> baseFaker.resolve("name.first_name");
        baseFaker.doWith(callable, Locale.FRANCE);
        assertThat(baseFaker.getContext().getLocale()).isEqualTo(initialLocale);
    }
}
