
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Lorem_wordsTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseFaker();
        lorem = new Lorem(faker);
    }

    @Test
    public void testWordsWithPositiveNumber() {
        int num = 5;
        List<String> result = lorem.words(num);
        assertThat(result).hasSize(num);
        for (String word : result) {
            assertThat(word).isNotBlank();
        }
    }

    @Test
    public void testWordsWithZero() {
        int num = 0;
        List<String> result = lorem.words(num);
        assertThat(result).isEmpty();
    }

    @Test
    public void testWordsWithNegativeNumber() {
        int num = -5;
        List<String> result = lorem.words(num);
        assertThat(result).isEmpty();
    }
}
