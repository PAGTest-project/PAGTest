
package net.datafaker.sequence;

import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FakeCollection_getTest {

    private RandomService randomService;
    private Supplier<String> supplier;

    @BeforeEach
    public void setUp() {
        randomService = Mockito.mock(RandomService.class);
        supplier = () -> "test";
    }

    @Test
    public void testGet() {
        // Given
        int minLength = 5;
        int maxLength = 10;
        when(randomService.nextInt(minLength, maxLength)).thenReturn(7);

        FakeCollection<String> fakeCollection = new FakeCollection.Builder<String>()
                .suppliers(supplier)
                .minLen(minLength)
                .maxLen(maxLength)
                .randomService(randomService)
                .build();

        // When
        List<String> result = fakeCollection.get();

        // Then
        assertEquals(7, result.size());
        result.forEach(item -> assertEquals("test", item));
    }
}
