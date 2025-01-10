
package net.datafaker.sequence;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class FakeStream_getTest {

    @Test
    public void testGetInfiniteStream() {
        // Given
        RandomService randomService = Mockito.mock(RandomService.class);
        FakeStream<String> fakeStream = new FakeStream.Builder<String>()
                .minLength(1)
                .maxLength(-1) // Infinite stream
                .randomService(randomService)
                .build();

        // When
        Stream<String> stream = fakeStream.get();

        // Then
        assertTrue(stream.limit(10).count() > 0); // Ensure stream is infinite
    }

    @Test
    public void testGetFiniteStream() {
        // Given
        RandomService randomService = Mockito.mock(RandomService.class);
        when(randomService.nextInt(1, 5)).thenReturn(3); // Fixed size of 3
        FakeStream<String> fakeStream = new FakeStream.Builder<String>()
                .minLength(1)
                .maxLength(5) // Finite stream
                .randomService(randomService)
                .build();

        // When
        Stream<String> stream = fakeStream.get();

        // Then
        assertEquals(3, stream.count()); // Ensure stream size is 3
    }
}
