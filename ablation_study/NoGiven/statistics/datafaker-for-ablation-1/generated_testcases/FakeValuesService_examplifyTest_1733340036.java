
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FakeValuesService_examplifyTest {

    @Test
    void testExamplifyWithNullExample() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);

        String result = fakeValuesService.examplify(null, context);

        assertNull(result);
    }

    @Test
    void testExamplifyWithMixedCharacters() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);
        RandomService randomService = mock(RandomService.class);
        when(context.getRandomService()).thenReturn(randomService);
        when(randomService.nextInt(10)).thenReturn(1, 2); // Mocking different values for each call

        String result = fakeValuesService.examplify("a1b2", context);

        assertEquals("a1b2", result);
    }
}
