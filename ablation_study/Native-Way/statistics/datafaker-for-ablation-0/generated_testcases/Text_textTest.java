
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;
import net.datafaker.Faker;

public class Text_textTest {

    private Text text;

    @BeforeEach
    public void setUp() {
        text = new Text(new BaseProviders() {
            @Override
            public Faker getFaker() {
                return new Faker();
            }
        });
    }

    @Test
    void testTextWithMinimumLength() {
        String result = text.text(5, 10, false, false, false);
        assertThat(result.length()).isBetween(5, 10);
    }

    @Test
    void testTextWithMaximumLength() {
        String result = text.text(15, 20, false, false, false);
        assertThat(result.length()).isBetween(15, 20);
    }

    @Test
    void testTextWithUppercase() {
        String result = text.text(5, 10, true, false, false);
        assertThat(result).containsPattern(Pattern.compile("[A-Z]"));
    }

    @Test
    void testTextWithSpecialCharacters() {
        String result = text.text(5, 10, false, true, false);
        assertThat(result).containsPattern(Pattern.compile("[!@#$%^&*]"));
    }

    @Test
    void testTextWithDigits() {
        String result = text.text(5, 10, false, false, true);
        assertThat(result).containsPattern(Pattern.compile("[0-9]"));
    }

    @Test
    void testTextWithAllOptions() {
        String result = text.text(5, 10, true, true, true);
        assertThat(result).containsPattern(Pattern.compile("[A-Z]"));
        assertThat(result).containsPattern(Pattern.compile("[!@#$%^&*]"));
        assertThat(result).containsPattern(Pattern.compile("[0-9]"));
    }

    @Test
    void testTextWithExactLength() {
        String result = text.text(10, 10, false, false, false);
        assertThat(result.length()).isEqualTo(10);
    }

    @Test
    void testTextWithNoOptions() {
        String result = text.text(5, 10, false, false, false);
        assertThat(result).containsPattern(Pattern.compile("[a-z]"));
    }
}
