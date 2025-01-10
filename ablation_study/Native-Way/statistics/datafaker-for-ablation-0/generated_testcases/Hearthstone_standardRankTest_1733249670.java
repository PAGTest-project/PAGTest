
package net.datafaker.providers.videogame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Hearthstone_standardRankTest {
    private Hearthstone hearthstone;

    @BeforeEach
    public void setUp() {
        hearthstone = new Hearthstone(new VideoGameProviders() {});
    }

    @Test
    void testStandardRankLegend() {
        String rank = hearthstone.standardRank();
        if (rank.startsWith("Legend")) {
            assertTrue(rank.matches("Legend \\d+"));
        }
    }

    @Test
    void testStandardRankNonLegend() {
        String rank = hearthstone.standardRank();
        if (!rank.startsWith("Legend")) {
            assertTrue(rank.matches("[A-Za-z]+ \\d+"));
        }
    }

    @Test
    void testStandardRankNotNull() {
        String rank = hearthstone.standardRank();
        assertNotNull(rank);
    }
}
