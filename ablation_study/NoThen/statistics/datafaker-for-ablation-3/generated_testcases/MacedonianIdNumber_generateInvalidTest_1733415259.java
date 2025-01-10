
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class MacedonianIdNumber_generateInvalidTest {
    private MacedonianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new MacedonianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateInvalid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().numberBetween(0, 2)).thenReturn(0);
        when(faker.number().numberBetween(0, 500)).thenReturn(123);
        when(faker.number().numberBetween(500, 1000)).thenReturn(678);

        Gender gender = Gender.MALE;
        String basePart = "010190041123";
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.number().numberBetween(0, MacedonianIdNumber.REGIONS.size())).thenReturn(0);
        when(faker.number().numberBetween(0, 500)).thenReturn(123);

        String expectedInvalidId = basePart + ((generator.checksum(basePart) + 1) % 10);
        String actualInvalidId = generator.generateInvalid(faker);

        assertEquals(expectedInvalidId, actualInvalidId);
    }

    @Test
    public void testChecksum() {
        assertEquals(6, generator.checksum("010100650000"));
        assertEquals(4, generator.checksum("923456789012"));
    }
}
