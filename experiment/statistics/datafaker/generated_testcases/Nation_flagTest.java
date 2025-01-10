
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.List;

public class Nation_flagTest {

    private Nation nation;
    private BaseProviders faker;
    private FakeValuesService fakeValuesService;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        fakeValuesService = mock(FakeValuesService.class);
        when(faker.fakeValuesService()).thenReturn(fakeValuesService);
        nation = new Nation(faker);
    }

    @Test
    public void testFlag() {
        // Given
        List<Integer> flagInts = Arrays.asList(72, 101, 108, 108, 111); // "Hello" in ASCII
        when(fakeValuesService.fetch("nation.flag", any())).thenReturn(flagInts);

        // When
        String result = nation.flag();

        // Then
        assertThat(result).isEqualTo("Hello");
    }
}
