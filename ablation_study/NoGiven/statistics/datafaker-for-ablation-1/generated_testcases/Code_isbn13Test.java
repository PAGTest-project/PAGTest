
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Code_isbn13Test {

    @Test
    public void testIsbn13WithSeparator() {
        BaseProviders faker = mock(BaseProviders.class);
        Code code = spy(new Code(faker));

        when(faker.expression("#{code.isbn_gs1}")).thenReturn("978");
        when(faker.expression("#{code.isbn_group}")).thenReturn("1");
        when(faker.expression("#{code.isbn_registrant}")).thenReturn("234567-8");
        doReturn(5).when(code).isbn13CheckDigit(any(StringBuilder.class));

        String expected = "978-1-234567-8-5";
        String actual = code.isbn13(true);

        assertEquals(expected, actual);
    }

    @Test
    public void testIsbn13WithoutSeparator() {
        BaseProviders faker = mock(BaseProviders.class);
        Code code = spy(new Code(faker));

        when(faker.expression("#{code.isbn_gs1}")).thenReturn("978");
        when(faker.expression("#{code.isbn_group}")).thenReturn("1");
        when(faker.expression("#{code.isbn_registrant}")).thenReturn("234567-8");
        doReturn(5).when(code).isbn13CheckDigit(any(StringBuilder.class));

        String expected = "978123456785";
        String actual = code.isbn13(false);

        assertEquals(expected, actual);
    }
}
