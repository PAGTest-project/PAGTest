
package net.datafaker.providers.videogame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import net.datafaker.providers.base.BaseFaker;

public class Hearthstone_standardRankTest {
    private Hearthstone hearthstone;

    @BeforeEach
    public void setUp() {
        hearthstone = new Hearthstone(new BaseFaker());
    }

    @Test
    public void testStandardRankLegend() {
        String rank = hearthstone.standardRank();
        if (rank.startsWith("Legend")) {
            assertTrue(rank.matches("Legend \\d+"));
        }
    }

    @Test
    public void testStandardRankNonLegend() {
        String rank = hearthstone.standardRank();
        if (!rank.startsWith("Legend")) {
            assertTrue(rank.matches("[A-Za-z]+ \\d+"));
        }
    }

    @Test
    public void testStandardRankNotNull() {
        String rank = hearthstone.standardRank();
        assertNotNull(rank);
    }
}
