
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FakeValuesService_examplifyTest {

    @Test
    void testExamplify_nullInput() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);

        String result = fakeValuesService.examplify(null, context);

        assertNull(result);
    }

    @Test
    void testExamplify_mixedInput() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);
        RandomService randomService = mock(RandomService.class);
        when(context.getRandomService()).thenReturn(randomService);
        when(randomService.nextInt(10)).thenReturn(0);

        String result = fakeValuesService.examplify("a1b2", context);

        assertEquals("0000", result);
    }
}
