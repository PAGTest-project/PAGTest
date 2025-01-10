
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Nation_flagTest {

    @Test
    public void testFlag() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        FakeValuesService fakeValuesService = Mockito.mock(FakeValuesService.class);
        when(faker.fakeValuesService()).thenReturn(fakeValuesService);

        List<Integer> flagInts = Arrays.asList(72, 101, 108, 108, 111); // "Hello" in ASCII
        when(fakeValuesService.fetch("nation.flag", faker.getContext())).thenReturn(flagInts);

        Nation nation = new Nation(faker);

        // When
        String result = nation.flag();

        // Then
        assertEquals("Hello", result);
    }
}
