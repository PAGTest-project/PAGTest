
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class MacedonianIdNumber_generateValidTest {
    private MacedonianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MacedonianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateValid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String basePart = "010190000000";
        String expectedIdNumber = basePart + "6"; // Assuming checksum for this basePart is 6

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(gender);
        when(generator.basePart(faker, birthday, gender)).thenReturn(basePart);
        when(generator.checksum(basePart)).thenReturn(6);

        IdNumberRequest request = mock(IdNumberRequest.class);

        // When
        PersonIdNumber result = generator.generateValid(faker, request);

        // Then
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }

    @Test
    public void testGenerateInvalid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;
        String basePart = "010190000000";
        String expectedInvalidIdNumber = basePart + "7"; // Assuming checksum for this basePart is 6, invalid checksum would be 7

        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(gender);
        when(generator.basePart(faker, birthday, gender)).thenReturn(basePart);
        when(generator.checksum(basePart)).thenReturn(6);

        // When
        String result = generator.generateInvalid(faker);

        // Then
        assertEquals(expectedInvalidIdNumber, result);
    }

    @Test
    public void testChecksum() {
        assertEquals(6, generator.checksum("010100650000"));
        assertEquals(4, generator.checksum("923456789012"));
    }
}
