
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.Options;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MexicanIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        Options options = Mockito.mock(Options.class);
        IdNumberRequest request = Mockito.mock(IdNumberRequest.class);

        when(faker.options()).thenReturn(options);
        when(options.option(MexicanIdNumber.CONSONANT)).thenReturn('B');
        when(options.option(MexicanIdNumber.VOWEL)).thenReturn('A');
        when(options.option(MexicanIdNumber.STATES)).thenReturn("AG");

        when(Utils.gender(faker, request)).thenReturn(Gender.FEMALE);
        when(Utils.birthday(faker, request)).thenReturn(LocalDate.of(1990, 1, 1));

        MexicanIdNumber mexicanIdNumber = new MexicanIdNumber();

        // When
        PersonIdNumber result = mexicanIdNumber.generateValid(faker, request);

        // Then
        assertEquals(18, result.getIdNumber().length());
        assertEquals('M', result.getIdNumber().charAt(10));
        assertEquals("19900101", result.getIdNumber().substring(4, 10));
    }
}
