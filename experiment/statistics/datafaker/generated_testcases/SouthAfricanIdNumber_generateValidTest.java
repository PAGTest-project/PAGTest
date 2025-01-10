
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseFaker;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SouthAfricanIdNumber_generateValidTest {

    private SouthAfricanIdNumber southAfricanIdNumber;
    private BaseFaker faker;
    private IdNumberRequest request;

    @BeforeEach
    public void setUp() {
        southAfricanIdNumber = new SouthAfricanIdNumber();
        faker = mock(BaseFaker.class);
        request = mock(IdNumberRequest.class);
    }

    @Test
    public void testGenerateValid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        when(Utils.birthday(faker, request)).thenReturn(birthday);
        when(Utils.gender(faker, request)).thenReturn(gender);
        when(faker.number().numberBetween(5000, 10_000)).thenReturn(5001);
        when(faker.options().option(anyString())).thenReturn("18");

        // When
        PersonIdNumber result = southAfricanIdNumber.generateValid(faker, request);

        // Then
        String expectedBasePart = "900101" + "5001" + "18";
        String expectedChecksum = String.valueOf(southAfricanIdNumber.calculateChecksum(expectedBasePart, 12));
        String expectedIdNumber = expectedBasePart + expectedChecksum;
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }
}
