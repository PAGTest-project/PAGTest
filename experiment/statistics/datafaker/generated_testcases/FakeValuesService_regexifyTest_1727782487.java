
package net.datafaker.service;

import com.github.curiousoddman.rgxgen.RgxGen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FakeValuesService_regexifyTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private RandomService randomService;

    @BeforeEach
    void setUp() {
        fakeValuesService = new FakeValuesService();
        context = Mockito.mock(FakerContext.class);
        randomService = Mockito.mock(RandomService.class);
        when(context.getRandomService()).thenReturn(randomService);
    }

    @Test
    void testRegexify_NewRegex() {
        String regex = "[a-z]";
        RgxGen rgxGen = Mockito.mock(RgxGen.class);
        when(rgxGen.generate(Mockito.any())).thenReturn("a");
        when(randomService.getRandomInternal()).thenReturn(new java.util.Random());

        String result = fakeValuesService.regexify(regex, context);

        assertEquals("a", result);
    }

    @Test
    void testRegexify_CachedRegex() {
        String regex = "[a-z]";
        RgxGen rgxGen = Mockito.mock(RgxGen.class);
        fakeValuesService.expression2generex.put(regex, rgxGen);
        when(rgxGen.generate(Mockito.any())).thenReturn("b");
        when(randomService.getRandomInternal()).thenReturn(new java.util.Random());

        String result = fakeValuesService.regexify(regex, context);

        assertEquals("b", result);
    }
}
