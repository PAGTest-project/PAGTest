
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
    public void testStandardRankLegend() {
        String rank = hearthstone.standardRank();
        assertTrue(rank.startsWith("Legend"));
        assertTrue(rank.matches("Legend \\d+"));
    }

    @Test
    public void testStandardRankNonLegend() {
        String rank = hearthstone.standardRank();
        if (!rank.startsWith("Legend")) {
            assertTrue(rank.matches("[A-Za-z]+ \\d+"));
        }
    }

    @Test
    public void testWildRank() {
        String rank = hearthstone.wildRank();
        assertNotNull(rank);
        assertTrue(rank.matches("[ A-Za-z0-9]+"));
    }

    @Test
    public void testMainProfession() {
        String profession = hearthstone.mainProfession();
        assertNotNull(profession);
    }

    @Test
    public void testMainCharacter() {
        String character = hearthstone.mainCharacter();
        assertNotNull(character);
    }

    @Test
    public void testMainPattern() {
        String pattern = hearthstone.mainPattern();
        assertNotNull(pattern);
    }

    @Test
    public void testBattlegroundsScore() {
        int score = hearthstone.battlegroundsScore();
        assertTrue(score >= 0 && score <= 16000);
    }
}
