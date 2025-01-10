
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
        when(f.timeAndDate().birthday(Mockito.anyInt(), Mockito.anyInt())).thenReturn(LocalDate.of(1980, 1, 1));
        when(f.random().nextInt(0, 9)).thenReturn(1, 2, 3, 4, 5, 6, 7);
        when(f.timeAndDate()).thenReturn(Mockito.mock(BaseProviders.TimeAndDate.class));

        String result = SingaporeIdNumber.getValidFIN(f, SingaporeIdNumber.Type.SINGAPOREAN_TWENTIETH_CENTURY);
        assertEquals("A1234567A", result);
    }

    @Test
    public void testGetValidFIN_ForeignerTwentyFirstCentury() {
        BaseProviders f = Mockito.mock(BaseProviders.class);
        when(f.timeAndDate().birthday(Mockito.anyInt(), Mockito.anyInt())).thenReturn(LocalDate.of(2020, 1, 1));
        when(f.random().nextInt(0, 9)).thenReturn(1, 2, 3, 4, 5, 6, 7);
        when(f.timeAndDate()).thenReturn(Mockito.mock(BaseProviders.TimeAndDate.class));

        String result = SingaporeIdNumber.getValidFIN(f, SingaporeIdNumber.Type.FOREIGNER_TWENTY_FIRST_CENTURY);
        assertEquals("A1234567X", result);
    }
}
