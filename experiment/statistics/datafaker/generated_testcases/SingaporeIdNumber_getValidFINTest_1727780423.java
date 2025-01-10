
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SingaporeIdNumber_getValidFINTest {

    @Test
    public void testGetValidFIN_SingaporeanTwentiethCentury() {
        BaseProviders f = Mockito.mock(BaseProviders.class);
        LocalDate mockBirthDate = LocalDate.of(1980, 1, 1);
        when(f.timeAndDate().birthday(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockBirthDate);

        String result = SingaporeIdNumber.getValidFIN(f, SingaporeIdNumber.Type.SINGAPOREAN_TWENTIETH_CENTURY);

        assertEquals(11, result.length());
        assertEquals('A', result.charAt(0));
    }

    @Test
    public void testGetValidFIN_ForeignerTwentyFirstCentury() {
        BaseProviders f = Mockito.mock(BaseProviders.class);
        LocalDate mockBirthDate = LocalDate.of(2010, 1, 1);
        when(f.timeAndDate().birthday(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockBirthDate);

        String result = SingaporeIdNumber.getValidFIN(f, SingaporeIdNumber.Type.FOREIGNER_TWENTY_FIRST_CENTURY);

        assertEquals(11, result.length());
        assertEquals('A', result.charAt(0));
    }
}
