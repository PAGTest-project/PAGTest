
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Nation_flagTest {
    private Nation nation;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public Object fakeValuesService() {
                return new Object() {
                    public Object fetch(String key, Object context) {
                        return Arrays.asList(72, 101, 108, 108, 111); // "Hello" in ASCII
                    }
                };
            }
        };
        nation = new Nation(faker);
    }

    @Test
    void testFlag() {
        String flag = nation.flag();
        assertThat(flag).isNotBlank();
    }

    @Test
    void testFlagWithMockData() {
        List<Integer> mockFlagInts = Arrays.asList(72, 101, 108, 108, 111); // "Hello" in ASCII
        ByteBuffer byteBuffer = ByteBuffer.allocate(mockFlagInts.size());
        for (Integer flagInt : mockFlagInts) {
            byteBuffer.put(flagInt.byteValue());
        }
        String expectedFlag = new String(byteBuffer.array(), StandardCharsets.UTF_8);

        String actualFlag = nation.flag();
        assertThat(actualFlag).isEqualTo(expectedFlag);
    }
}
