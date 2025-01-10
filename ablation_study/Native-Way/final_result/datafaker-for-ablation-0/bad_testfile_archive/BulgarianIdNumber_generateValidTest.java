
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

public class BulgarianIdNumber_generateValidTest {
    private BulgarianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new BulgarianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    void testGenerateValid() {
        LocalDate birthday = LocalDate.of(1980, 3, 20);
        Gender gender = Gender.MALE;
        String basePart = "800320";
        String expectedIdNumber = basePart + "1";

        when(faker.dateAndTime().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(gender);
        when(faker.number().digits(2)).thenReturn("00");

        IdNumberRequest request = new IdNumberRequest(0, 0, null);
        PersonIdNumber result = generator.generateValid(faker, request);

        assertEquals(expectedIdNumber, result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }

    @Test
    void testGenerateInvalid() {
        LocalDate birthday = LocalDate.of(1980, 3, 20);
        Gender gender = Gender.MALE;
        String basePart = "800320";
        String expectedInvalidIdNumber = basePart + "2";

        when(faker.dateAndTime().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(gender);
        when(faker.number().digits(2)).thenReturn("00");

        String result = generator.generateInvalid(faker);

        assertEquals(expectedInvalidIdNumber, result);
    }

    @Test
    void testChecksum() {
        assertEquals(1, generator.checksum("803205603"));
        assertEquals(8, generator.checksum("800101000"));
        assertEquals(8, generator.checksum("750102001"));
        assertEquals(0, generator.checksum("820630876"));
        assertEquals(7, generator.checksum("560628204"));
        assertEquals(3, generator.checksum("752316926"));
        assertEquals(5, generator.checksum("755201000"));
        assertEquals(0, generator.checksum("754201103"));
    }
}
