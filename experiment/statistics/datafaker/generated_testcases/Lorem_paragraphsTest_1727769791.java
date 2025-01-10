
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Lorem_paragraphsTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        lorem = new Lorem(faker);
    }

    @Test
    public void testParagraphsWithCount() {
        int paragraphCount = 5;
        List<String> paragraphs = lorem.paragraphs(paragraphCount);

        assertThat(paragraphs).hasSize(paragraphCount);
        for (String paragraph : paragraphs) {
            assertThat(paragraph).isNotBlank();
        }
    }
}
