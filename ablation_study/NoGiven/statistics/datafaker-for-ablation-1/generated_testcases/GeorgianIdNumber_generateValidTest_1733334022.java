
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber;
import net.datafaker.providers.base.PersonIdNumber;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GeorgianIdNumber_generateValidTest {

    @Test
    public void testGenerateValid() {
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        IdNumber.IdNumberRequest request = Mockito.mock(IdNumber.IdNumberRequest.class);

        when(faker.numerify("###########")).thenReturn("12345678901");
        when(Utils.birthday(faker, request)).thenReturn("1990-01-01");
        when(Utils.gender(faker, request)).thenReturn("Male");

        GeorgianIdNumber georgianIdNumber = new GeorgianIdNumber();
        PersonIdNumber result = georgianIdNumber.generateValid(faker, request);

        assertEquals("12345678901", result.getIdNumber());
        assertEquals("1990-01-01", result.getBirthday());
        assertEquals("Male", result.getGender());
    }
}
