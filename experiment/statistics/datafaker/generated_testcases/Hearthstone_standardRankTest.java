
package net.datafaker.providers.videogame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import net.datafaker.providers.base.BaseFaker;

public class Hearthstone_standardRankTest {

    private Hearthstone hearthstone;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseFaker.class);
        hearthstone = new Hearthstone(faker);
    }

    @Test
    public void testStandardRankLegend() {
        when(faker.resolve("games.hearthstone.rank")).thenReturn("Legend");
        when(faker.random().nextInt(1, 65000)).thenReturn(12345);

        String rank = hearthstone.standardRank();
        assertTrue(rank.startsWith("Legend 12345"));
    }

    @Test
    public void testStandardRankNonLegend() {
        when(faker.resolve("games.hearthstone.rank")).thenReturn("Rank");
        when(faker.random().nextInt(1, 10)).thenReturn(5);

        String rank = hearthstone.standardRank();
        assertTrue(rank.startsWith("Rank 5"));
    }
}
