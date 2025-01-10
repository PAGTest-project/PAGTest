
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Lorem_sentencesTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders();
        lorem = new Lorem(baseProviders);
    }

    @Test
    void testSentencesWithPositiveSentenceCount() {
        int sentenceCount = 5;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertThat(sentences).hasSize(sentenceCount);
        sentences.forEach(sentence -> assertThat(sentence).endsWith("."));
    }

    @Test
    void testSentencesWithZeroSentenceCount() {
        int sentenceCount = 0;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertThat(sentences).isEmpty();
    }

    @Test
    void testSentencesWithNegativeSentenceCount() {
        int sentenceCount = -5;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertThat(sentences).isEmpty();
    }
}
