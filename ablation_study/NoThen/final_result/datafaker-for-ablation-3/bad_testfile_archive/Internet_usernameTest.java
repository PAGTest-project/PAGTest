
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import net.datafaker.service.FakerContext;

public class Internet_usernameTest {

    @Test
    public void testUsername() {
        BaseFaker faker = mock(BaseFaker.class);
        Name name = mock(Name.class);
        FakerContext context = mock(FakerContext.class);
        when(faker.name()).thenReturn(name);
        when(name.firstName()).thenReturn("John");
        when(name.lastName()).thenReturn("Doe");
        when(faker.getContext()).thenReturn(context);
        when(context.getLocale()).thenReturn(java.util.Locale.ENGLISH);

        Internet internet = new Internet(faker);
        String result = internet.username();

        assertEquals("john.doe", result);
    }

    @Test
    public void testUsernameWithSpecialCharacters() {
        BaseFaker faker = mock(BaseFaker.class);
        Name name = mock(Name.class);
        FakerContext context = mock(FakerContext.class);
        when(faker.name()).thenReturn(name);
        when(name.firstName()).thenReturn("O'Reilly");
        when(name.lastName()).thenReturn("Smith");
        when(faker.getContext()).thenReturn(context);
        when(context.getLocale()).thenReturn(java.util.Locale.ENGLISH);

        Internet internet = new Internet(faker);
        String result = internet.username();

        assertEquals("o.reilly.smith", result);
    }
}
