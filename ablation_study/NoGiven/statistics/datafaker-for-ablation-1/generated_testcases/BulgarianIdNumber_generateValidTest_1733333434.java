
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
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
        // Mocking dependencies
        LocalDate birthday = LocalDate.of(1980, 3, 20);
        Gender gender = Gender.MALE;
        when(faker.timeAndDate().birthday()).thenReturn(birthday);
        when(faker.gender()).thenReturn(gender);

        // Mocking basePart method
        String basePart = "800320001";
        when(generator.basePart(any(), any(), any())).thenReturn(basePart);

        // Mocking checksum method
        when(generator.checksum(basePart)).thenReturn(1);

        // Test generateValid method
        IdNumberRequest request = new IdNumberRequest();
        PersonIdNumber idNumber = generator.generateValid(faker, request);

        assertThat(idNumber.getIdNumber()).isEqualTo(basePart + "1");
        assertThat(idNumber.getBirthday()).isEqualTo(birthday);
        assertThat(idNumber.getGender()).isEqualTo(gender);
    }

    @Test
    void testChecksum() {
        assertThat(generator.checksum("803205603")).isEqualTo(1);
        assertThat(generator.checksum("800101000")).isEqualTo(8);
        assertThat(generator.checksum("750102001")).isEqualTo(8);
        assertThat(generator.checksum("820630876")).isEqualTo(0);
        assertThat(generator.checksum("560628204")).isEqualTo(7);
        assertThat(generator.checksum("752316926")).isEqualTo(3);
        assertThat(generator.checksum("755201000")).isEqualTo(5);
        assertThat(generator.checksum("754201103")).isEqualTo(0);
    }
}
