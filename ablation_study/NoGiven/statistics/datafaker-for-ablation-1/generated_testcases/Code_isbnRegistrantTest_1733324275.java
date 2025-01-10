
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class Code_isbnRegistrantTest {

    @Test
    void testIsbnRegistrant() {
        BaseProviders faker = mock(BaseProviders.class);
        Code code = new Code(faker);

        when(faker.random().nextInt(6)).thenReturn(0); // ct = 1
        when(faker.number().numberBetween(9500000, 9999999)).thenReturn(9500000);
        when(faker.number().digits(1)).thenReturn("1");

        String result = code.isbnRegistrant();
        assertEquals("9500000-1", result);

        when(faker.random().nextInt(6)).thenReturn(5); // ct = 6
        when(faker.number().numberBetween(0, 1)).thenReturn(0);
        when(faker.number().digit()).thenReturn("1");
        when(faker.number().digits(6)).thenReturn("123456");

        result = code.isbnRegistrant();
        assertEquals("01-123456", result);

        when(faker.random().nextInt(6)).thenReturn(6); // Invalid ct
        assertThrows(IllegalStateException.class, code::isbnRegistrant);
    }
}
