
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RandomService_nextLongTest {

    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        randomService = new RandomService();
    }

    @Test
    void testNextLongWithPositiveBound() {
        long bound = 10L;
        long result = randomService.nextLong(bound);
        assertThat(result).isGreaterThanOrEqualTo(0L).isLessThan(bound);
    }

    @Test
    void testNextLongWithBoundOne() {
        long bound = 1L;
        long result = randomService.nextLong(bound);
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testNextLongWithNegativeBound() {
        long bound = -10L;
        assertThatThrownBy(() -> randomService.nextLong(bound))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("bound must be positive: " + bound);
    }

    @Test
    void testNextLongWithZeroBound() {
        long bound = 0L;
        assertThatThrownBy(() -> randomService.nextLong(bound))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("bound must be positive: " + bound);
    }

    @ParameterizedTest
    @MethodSource("randomServiceProvider")
    void testLongWithinBoundary(RandomService randomService) {
        assertThat(randomService.nextLong(1)).isZero();

        for (int i = 1; i < 10; i++) {
            assertThat(randomService.nextLong(2)).isLessThan(2L);
        }
    }

    private static Stream<Arguments> randomServiceProvider() {
        return Stream.of(
                Arguments.of(new RandomService(new Random())),
                Arguments.of(new RandomService())
        );
    }
}
