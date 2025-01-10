
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SwedenIdNumber_generateValidTest {

    private SwedenIdNumber swedenIdNumber;
    private BaseProviders f;
    private IdNumber.IdNumberRequest request;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        f = Mockito.mock(BaseProviders.class);
        request = Mockito.mock(IdNumber.IdNumberRequest.class);
    }

    @Test
    public void testGenerateValid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(f.numerify("###")).thenReturn("123");
        when(f.options().option(SwedenIdNumber.PLUS_MINUS)).thenReturn("-");
        when(Utils.gender(f, request)).thenReturn(Gender.MALE);

        // When
        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        // Then
        String expectedBasePart = "900101-123";
        String expectedIdNumber = expectedBasePart + calculateChecksum(expectedBasePart);
        assertThat(result.getIdNumber()).isEqualTo(expectedIdNumber);
        assertThat(result.getBirthDate()).isEqualTo(birthday);
        assertThat(result.getGender()).isEqualTo(Gender.MALE);
    }

    private int calculateChecksum(String basePart) {
        // Mocked checksum calculation for testing purposes
        return 4; // Example checksum
    }
}
