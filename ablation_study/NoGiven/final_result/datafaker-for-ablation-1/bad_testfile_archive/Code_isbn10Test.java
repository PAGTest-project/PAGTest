
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Code_isbn10Test {

    @Test
    public void testIsbn10WithSeparator() {
        BaseProviders faker = mock(BaseProviders.class);
        Code code = spy(new Code(faker));

        when(faker.expression("#{code.isbn_group}")).thenReturn("0");
        when(faker.expression("#{code.isbn_registrant}")).thenReturn("123456");
        doReturn(5).when(code).isbn10CheckDigit(any(StringBuilder.class));

        String result = code.isbn10(true);
        assertEquals("0-123456-5", result);
    }

    @Test
    public void testIsbn10WithoutSeparator() {
        BaseProviders faker = mock(BaseProviders.class);
        Code code = spy(new Code(faker));

        when(faker.expression("#{code.isbn_group}")).thenReturn("1");
        when(faker.expression("#{code.isbn_registrant}")).thenReturn("654321");
        doReturn(10).when(code).isbn10CheckDigit(any(StringBuilder.class));

        String result = code.isbn10(false);
        assertEquals("1654321X", result);
    }
}
