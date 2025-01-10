
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class BulgarianIdNumber_generateInvalidTest {
    private BulgarianIdNumber generator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        generator = new BulgarianIdNumber();
        faker = mock(BaseProviders.class);
    }

    @Test
    void testGenerateInvalid() {
        LocalDate birthDate = LocalDate.of(1980, 3, 20);
        when(faker.timeAndDate().birthday()).thenReturn(birthDate);
        when(faker.number().numberBetween(0, 5)).thenReturn(2);
        when(faker.number().digits(2)).thenReturn("03");

        String basePart = "80032003";
        when(faker.number().digits(2)).thenReturn("03");
        when(faker.number().numberBetween(0, 5)).thenReturn(2);

        String invalidId = generator.generateInvalid(faker);
        assertEquals(basePart + "2", invalidId);
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
