
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.TimeAndDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BulgarianIdNumber_generateInvalidTest {
    private BulgarianIdNumber generator;
    private BaseProviders faker;
    private TimeAndDate timeAndDate;

    @BeforeEach
    public void setUp() {
        generator = new BulgarianIdNumber();
        faker = mock(BaseProviders.class);
        timeAndDate = mock(TimeAndDate.class);
        when(faker.timeAndDate()).thenReturn(timeAndDate);
    }

    @Test
    public void testGenerateInvalid() {
        // Given
        when(timeAndDate.birthday()).thenReturn(java.time.LocalDate.of(1980, 3, 20));
        when(faker.number().numberBetween(0, 5)).thenReturn(2);
        when(faker.number().digits(2)).thenReturn("05");

        // When
        String invalidIdNumber = generator.generateInvalid(faker);

        // Then
        assertEquals("800320054", invalidIdNumber);
    }
}
