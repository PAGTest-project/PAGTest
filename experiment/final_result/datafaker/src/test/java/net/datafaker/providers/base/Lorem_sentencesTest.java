
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Lorem_sentencesTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseFaker();
        lorem = new Lorem(faker);
    }

    @Test
    void testSentencesWithPositiveCount() {
        int sentenceCount = 5;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertThat(sentences).hasSize(sentenceCount);
        sentences.forEach(sentence -> assertThat(sentence).endsWith("."));
    }

    @Test
    void testSentencesWithZeroCount() {
        int sentenceCount = 0;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertThat(sentences).isEmpty();
    }
}
