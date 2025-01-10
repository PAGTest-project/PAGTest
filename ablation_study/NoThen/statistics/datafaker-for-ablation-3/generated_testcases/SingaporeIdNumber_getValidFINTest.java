
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SingaporeIdNumber_getValidFINTest {

    @Test
    public void testGetValidFIN_SingaporeanTwentiethCentury() {
        // Given
        BaseProviders f = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(1980, 1, 1);
        PersonIdNumber expectedIdNumber = new PersonIdNumber("S1234567A", birthDate, PersonIdNumber.Gender.MALE);

        when(f.timeAndDate().birthday(Mockito.anyInt(), Mockito.anyInt())).thenReturn(birthDate);
        when(f.random().nextInt(0, 9)).thenReturn(1, 2, 3, 4, 5, 6, 7);
        when(f.bool().bool()).thenReturn(true);
        when(f.gender()).thenReturn(PersonIdNumber.Gender.MALE);

        // When
        String result = SingaporeIdNumber.getValidFIN(f, SingaporeIdNumber.Type.SINGAPOREAN_TWENTIETH_CENTURY);

        // Then
        assertEquals(expectedIdNumber.idNumber(), result);
    }

    @Test
    public void testGetValidFIN_ForeignerTwentyFirstCentury() {
        // Given
        BaseProviders f = Mockito.mock(BaseProviders.class);
        LocalDate birthDate = LocalDate.of(2010, 1, 1);
        PersonIdNumber expectedIdNumber = new PersonIdNumber("G1234567X", birthDate, PersonIdNumber.Gender.FEMALE);

        when(f.timeAndDate().birthday(Mockito.anyInt(), Mockito.anyInt())).thenReturn(birthDate);
        when(f.random().nextInt(0, 9)).thenReturn(1, 2, 3, 4, 5, 6, 7);
        when(f.bool().bool()).thenReturn(false);
        when(f.gender()).thenReturn(PersonIdNumber.Gender.FEMALE);

        // When
        String result = SingaporeIdNumber.getValidFIN(f, SingaporeIdNumber.Type.FOREIGNER_TWENTY_FIRST_CENTURY);

        // Then
        assertEquals(expectedIdNumber.idNumber(), result);
    }
}
