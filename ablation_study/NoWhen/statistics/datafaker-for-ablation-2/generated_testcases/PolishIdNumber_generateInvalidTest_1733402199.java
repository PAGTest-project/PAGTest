
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PolishIdNumber_generateInvalidTest {

    @Test
    public void testGenerateInvalid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        when(faker.timeAndDate().birthday()).thenReturn(LocalDate.of(1990, 1, 1));
        when(faker.number().randomDigit()).thenReturn(1, 2, 3);
        when(faker.random().nextInt(5)).thenReturn(2);

        PolishIdNumber polishIdNumber = new PolishIdNumber();

        // When
        String result = polishIdNumber.generateInvalid(faker);

        // Then
        assertEquals("1990010112345", result);
    }
}
