
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_usernameTest {

    @Test
    public void testUsername() {
        BaseProviders faker = mock(BaseProviders.class);
        Name name = mock(Name.class);
        when(faker.name()).thenReturn(name);
        when(name.firstName()).thenReturn("John");
        when(name.lastName()).thenReturn("Doe");
        when(faker.getContext().getLocale()).thenReturn(java.util.Locale.ENGLISH);

        Internet internet = new Internet(faker);
        String result = internet.username();

        assertEquals("john.doe", result);
    }

    @Test
    public void testUsernameWithSpecialCharacters() {
        BaseProviders faker = mock(BaseProviders.class);
        Name name = mock(Name.class);
        when(faker.name()).thenReturn(name);
        when(name.firstName()).thenReturn("O'Reilly");
        when(name.lastName()).thenReturn("Smith");
        when(faker.getContext().getLocale()).thenReturn(java.util.Locale.ENGLISH);

        Internet internet = new Internet(faker);
        String result = internet.username();

        assertEquals("o.reilly.smith", result);
    }
}
