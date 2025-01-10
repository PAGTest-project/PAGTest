
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MoldovanIdNumber_generateValidTest {

    private MoldovanIdNumber generator;
    private BaseProviders faker;
    private IdNumberRequest request;

    @BeforeEach
    public void setUp() {
        generator = new MoldovanIdNumber();
        faker = mock(BaseProviders.class);
        request = mock(IdNumberRequest.class);
    }

    @Test
    public void testGenerateValid() {
        // Given
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(Utils.birthday(faker, request)).thenReturn(birthday);
        when(Utils.randomGender(faker)).thenReturn(PersonIdNumber.Gender.M);
        when(faker.number().digits(3)).thenReturn("123");
        when(faker.number().digits(5)).thenReturn("45678");

        // When
        PersonIdNumber result = generator.generateValid(faker, request);

        // Then
        assertEquals("29012312345678" + generator.checksum("29012312345678"), result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals(PersonIdNumber.Gender.M, result.getGender());
    }
}
