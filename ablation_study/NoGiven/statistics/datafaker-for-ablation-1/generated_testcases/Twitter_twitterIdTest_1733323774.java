
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;

public class Twitter_twitterIdTest {
    private Twitter twitter;

    @BeforeEach
    public void setUp() {
        twitter = new Twitter(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
    }

    @Test
    void testTwitterIdValidLength() {
        int expectedLength = 10;
        String twitterId = twitter.twitterId(expectedLength);
        assertThat(twitterId).hasSize(expectedLength);
    }

    @Test
    void testTwitterIdInvalidLength() {
        int expectedLength = 5;
        String twitterId = twitter.twitterId(expectedLength);
        assertThat(twitterId).isNotNull();
    }

    @Test
    void testTwitterIdFormat() {
        int expectedLength = 15;
        String twitterId = twitter.twitterId(expectedLength);
        Pattern pattern = Pattern.compile("\\d{" + expectedLength + "}");
        assertThat(twitterId).matches(pattern);
    }

    @Test
    void testTwitterIdUniqueness() {
        int expectedLength = 20;
        String twitterId1 = twitter.twitterId(expectedLength);
        String twitterId2 = twitter.twitterId(expectedLength);
        assertThat(twitterId1).isNotEqualTo(twitterId2);
    }
}
