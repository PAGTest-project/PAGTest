
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
        long bound = 100L;
        long result = randomService.nextLong(bound);
        assertThat(result).isGreaterThanOrEqualTo(0L).isLessThan(bound);
    }

    @Test
    void testNextLongWithNegativeBound() {
        assertThatThrownBy(() -> randomService.nextLong(-1L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("bound must be positive: -1");
    }

    @Test
    void testNextLongWithZeroBound() {
        assertThatThrownBy(() -> randomService.nextLong(0L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("bound must be positive: 0");
    }

    @ParameterizedTest
    @MethodSource("randomServiceProvider")
    void testNextLongWithDifferentBounds(RandomService randomService) {
        long bound = 50L;
        long result = randomService.nextLong(bound);
        assertThat(result).isGreaterThanOrEqualTo(0L).isLessThan(bound);
    }

    private static Stream<Arguments> randomServiceProvider() {
        return Stream.of(
            Arguments.of(new RandomService(new Random())),
            Arguments.of(new RandomService())
        );
    }
}
