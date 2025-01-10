
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

class SouthAfricanIdNumber_isValidEnZASsnTest {

    @Test
    void testIsValidEnZASsn_ValidSsn() {
        // Given
        BaseProviders faker = mock(BaseProviders.class);
        when(faker.options().option(anyString())).thenReturn("18");
        when(faker.number().numberBetween(0, 5000)).thenReturn(1234);
        IdNumberRequest request = mock(IdNumberRequest.class);
        when(request.getBirthDate()).thenReturn(LocalDate.of(1990, 1, 1));
        when(request.getGender()).thenReturn(Gender.FEMALE);

        SouthAfricanIdNumber idNumberGenerator = new SouthAfricanIdNumber();
        PersonIdNumber validSsn = idNumberGenerator.generateValid(faker, request);

        // When
        boolean isValid = SouthAfricanIdNumber.isValidEnZASsn(validSsn.getIdNumber());

        // Then
        assertTrue(isValid);
    }

    @Test
    void testIsValidEnZASsn_InvalidLength() {
        // Given
        String invalidLengthSsn = "123456789012"; // 12 characters instead of 13

        // When
        boolean isValid = SouthAfricanIdNumber.isValidEnZASsn(invalidLengthSsn);

        // Then
        assertFalse(isValid);
    }

    @Test
    void testIsValidEnZASsn_InvalidDate() {
        // Given
        String invalidDateSsn = "9902305678901"; // Invalid date (February 30th)

        // When
        boolean isValid = SouthAfricanIdNumber.isValidEnZASsn(invalidDateSsn);

        // Then
        assertFalse(isValid);
    }

    @Test
    void testIsValidEnZASsn_InvalidChecksum() {
        // Given
        String invalidChecksumSsn = "9001015000089"; // Correct date, but incorrect checksum

        // When
        boolean isValid = SouthAfricanIdNumber.isValidEnZASsn(invalidChecksumSsn);

        // Then
        assertFalse(isValid);
    }
}
