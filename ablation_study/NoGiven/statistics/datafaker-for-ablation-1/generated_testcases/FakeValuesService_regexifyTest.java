
package net.datafaker.service;

import com.github.curiousoddman.rgxgen.RgxGen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FakeValuesService_regexifyTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        fakeValuesService = Mockito.spy(new FakeValuesService());
        context = Mockito.mock(FakerContext.class);
        randomService = Mockito.mock(RandomService.class);
        when(context.getRandomService()).thenReturn(randomService);
    }

    @Test
    public void testRegexify_NewRegex() {
        String regex = "[a-z]";
        RgxGen rgxGen = RgxGen.parse(regex);
        when(randomService.getRandomInternal()).thenReturn(new java.util.Random());

        String result = fakeValuesService.regexify(regex, context);

        assertEquals(rgxGen.generate(new java.util.Random()), result);
    }

    @Test
    public void testRegexify_CachedRegex() {
        String regex = "[a-z]";
        RgxGen rgxGen = RgxGen.parse(regex);
        Mockito.doReturn(rgxGen).when(fakeValuesService).expression2generex.get(regex);
        when(randomService.getRandomInternal()).thenReturn(new java.util.Random());

        String result = fakeValuesService.regexify(regex, context);

        assertEquals(rgxGen.generate(new java.util.Random()), result);
    }
}
