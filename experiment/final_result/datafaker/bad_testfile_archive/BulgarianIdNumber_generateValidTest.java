
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BulgarianIdNumber_generateValidTest {
    private BulgarianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new BulgarianIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    void testGenerateValid() {
        // Given
        LocalDate birthday = LocalDate.of(1980, 3, 20);
        Gender gender = Gender.MALE;
        String basePart = "800320";
        String expectedIdNumber = basePart + "1"; // Assuming checksum for "800320" is "1"

        when(Utils.birthday(faker, Mockito.any(IdNumberRequest.class))).thenReturn(birthday);
        when(Utils.gender(faker, Mockito.any(IdNumberRequest.class))).thenReturn(gender);
        when(generator.basePart(faker, birthday, gender)).thenReturn(basePart);
        when(generator.checksum(basePart)).thenReturn(1); // Change to return an Integer

        // When
        PersonIdNumber result = generator.generateValid(faker, new IdNumberRequest(0, 0, null));

        // Then
        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }
}
