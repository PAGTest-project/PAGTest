
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.service.RandomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChineseIdNumber_generateValidTest {

    private BaseProviders faker;
    private IdNumberRequest request;
    private RandomService randomService;
    private ChineseIdNumber chineseIdNumber;

    @BeforeEach
    public void setup() {
        faker = Mockito.mock(BaseProviders.class);
        request = Mockito.mock(IdNumberRequest.class);
        randomService = Mockito.mock(RandomService.class);
        chineseIdNumber = Mockito.spy(new ChineseIdNumber());

        when(faker.random()).thenReturn(randomService);
        when(faker.options().option(ChineseIdNumber.LOCATIONS)).thenReturn("110000");
        when(randomService.nextInt(10)).thenReturn(1, 2, 3);
    }

    @Test
    public void testGenerateValid() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);
        when(chineseIdNumber.birthday(faker, request)).thenReturn(birthday);
        when(chineseIdNumber.gender(faker, request)).thenReturn("Male");

        PersonIdNumber result = chineseIdNumber.generateValid(faker, request);

        assertEquals("11000019900101123", result.getIdNumber());
        assertEquals(birthday, result.getBirthday());
        assertEquals("Male", result.getGender());
    }
}
