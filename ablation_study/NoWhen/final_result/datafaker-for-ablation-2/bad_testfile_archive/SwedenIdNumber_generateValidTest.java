
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

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        f = Mockito.mock(BaseProviders.class);
    }

    @Test
    void testGenerateValid() {
        IdNumber.IdNumberRequest request = new IdNumber.IdNumberRequest(0, 0, IdNumber.GenderRequest.MALE);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(f.numerify("###")).thenReturn("123");
        when(f.options().option(SwedenIdNumber.PLUS_MINUS)).thenReturn("-");
        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(Utils.gender(f, request)).thenReturn(IdNumber.GenderRequest.MALE);

        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        assertThat(result.getIdNumber()).startsWith("900101-123");
        assertThat(result.getBirthday()).isEqualTo(birthday);
        assertThat(result.getGender()).isEqualTo(IdNumber.GenderRequest.MALE);
    }

    @Test
    void testGenerateValidWithPlus() {
        IdNumber.IdNumberRequest request = new IdNumber.IdNumberRequest(0, 0, IdNumber.GenderRequest.FEMALE);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(f.numerify("###")).thenReturn("123");
        when(f.options().option(SwedenIdNumber.PLUS_MINUS)).thenReturn("+");
        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(Utils.gender(f, request)).thenReturn(IdNumber.GenderRequest.FEMALE);

        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        assertThat(result.getIdNumber()).startsWith("900101+123");
        assertThat(result.getBirthday()).isEqualTo(birthday);
        assertThat(result.getGender()).isEqualTo(IdNumber.GenderRequest.FEMALE);
    }

    @Test
    void testGenerateValidChecksum() {
        IdNumber.IdNumberRequest request = new IdNumber.IdNumberRequest(0, 0, IdNumber.GenderRequest.MALE);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(f.numerify("###")).thenReturn("123");
        when(f.options().option(SwedenIdNumber.PLUS_MINUS)).thenReturn("-");
        when(Utils.birthday(f, request)).thenReturn(birthday);
        when(Utils.gender(f, request)).thenReturn(IdNumber.GenderRequest.MALE);

        PersonIdNumber result = swedenIdNumber.generateValid(f, request);

        String basePart = "900101-123";
        int expectedChecksum = SwedenIdNumber.calculateChecksum(basePart);
        assertThat(result.getIdNumber()).endsWith(String.valueOf(expectedChecksum));
    }
}
