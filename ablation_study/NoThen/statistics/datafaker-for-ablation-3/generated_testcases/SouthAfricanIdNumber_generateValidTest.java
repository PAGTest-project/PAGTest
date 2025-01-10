
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SouthAfricanIdNumber_generateValidTest {

    private SouthAfricanIdNumber southAfricanIdNumber;
    private BaseProviders mockBaseProviders;

    @BeforeEach
    public void setUp() {
        southAfricanIdNumber = new SouthAfricanIdNumber();
        mockBaseProviders = mock(BaseProviders.class);
    }

    @Test
    public void testGenerateValid_Female() {
        IdNumberRequest request = mock(IdNumberRequest.class);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.FEMALE;

        when(Utils.birthday(mockBaseProviders, request)).thenReturn(birthday);
        when(Utils.gender(mockBaseProviders, request)).thenReturn(gender);
        when(mockBaseProviders.number().numberBetween(0, 5000)).thenReturn(1234);
        when(mockBaseProviders.options().option(anyString())).thenReturn("08");

        PersonIdNumber result = southAfricanIdNumber.generateValid(mockBaseProviders, request);

        assertEquals("900101123408", result.getIdNumber().substring(0, 12));
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }

    @Test
    public void testGenerateValid_Male() {
        IdNumberRequest request = mock(IdNumberRequest.class);
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        Gender gender = Gender.MALE;

        when(Utils.birthday(mockBaseProviders, request)).thenReturn(birthday);
        when(Utils.gender(mockBaseProviders, request)).thenReturn(gender);
        when(mockBaseProviders.number().numberBetween(5000, 10_000)).thenReturn(6789);
        when(mockBaseProviders.options().option(anyString())).thenReturn("18");

        PersonIdNumber result = southAfricanIdNumber.generateValid(mockBaseProviders, request);

        assertEquals("900101678918", result.getIdNumber().substring(0, 12));
        assertEquals(birthday, result.getBirthday());
        assertEquals(gender, result.getGender());
    }
}
