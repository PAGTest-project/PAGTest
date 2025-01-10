
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Internet_usernameTest {

    @Test
    public void testUsername() {
        // Given
        BaseFaker faker = mock(BaseFaker.class);
        FakerContext context = mock(FakerContext.class);
        when(faker.getContext()).thenReturn(context);
        when(context.getLocale()).thenReturn(java.util.Locale.ENGLISH);

        Name name = mock(Name.class);
        when(faker.name()).thenReturn(name);
        when(name.firstName()).thenReturn("John");
        when(name.lastName()).thenReturn("Doe");

        Internet internet = new Internet(faker);

        // When
        String result = internet.username();

        // Then
        assertEquals("john.doe", result);
    }

    @Test
    public void testUsernameWithSpecialCharacters() {
        // Given
        BaseFaker faker = mock(BaseFaker.class);
        FakerContext context = mock(FakerContext.class);
        when(faker.getContext()).thenReturn(context);
        when(context.getLocale()).thenReturn(java.util.Locale.ENGLISH);

        Name name = mock(Name.class);
        when(faker.name()).thenReturn(name);
        when(name.firstName()).thenReturn("O'Reilly");
        when(name.lastName()).thenReturn("Smith");

        Internet internet = new Internet(faker);

        // When
        String result = internet.username();

        // Then
        assertEquals("oreilly.smith", result);
    }
}
