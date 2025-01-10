
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
    void testWordsWithPositiveNumber() {
        int num = 5;
        List<String> words = lorem.words(num);
        assertThat(words).hasSize(num);
        for (String word : words) {
            assertThat(word).isNotBlank();
        }
    }

    @Test
    void testWordsWithZero() {
        int num = 0;
        List<String> words = lorem.words(num);
        assertThat(words).isEmpty();
    }

    @Test
    void testWordsWithNegativeNumber() {
        int num = -5;
        List<String> words = lorem.words(num);
        assertThat(words).isEmpty();
    }
}
