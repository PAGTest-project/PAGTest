
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Lorem_paragraphsTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        };
        lorem = new Lorem(baseProviders);
    }

    @Test
    public void testParagraphsWithCount() {
        int paragraphCount = 5;
        List<String> paragraphs = lorem.paragraphs(paragraphCount);
        assertThat(paragraphs).hasSize(paragraphCount);
        paragraphs.forEach(paragraph -> assertThat(paragraph).isNotBlank());
    }

    @Test
    public void testParagraphsWithZeroCount() {
        int paragraphCount = 0;
        List<String> paragraphs = lorem.paragraphs(paragraphCount);
        assertThat(paragraphs).isEmpty();
    }

    @Test
    public void testParagraphsWithNegativeCount() {
        int paragraphCount = -5;
        List<String> paragraphs = lorem.paragraphs(paragraphCount);
        assertThat(paragraphs).isEmpty();
    }
}
