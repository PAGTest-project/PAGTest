
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
import static org.mockito.Mockito.*;

public class ChineseIdNumber_generateValidTest {

    private BaseProviders faker;
    private RandomService randomService;
    private IdNumberRequest request;
    private ChineseIdNumber chineseIdNumber;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        randomService = Mockito.mock(RandomService.class);
        request = new IdNumberRequest(18, 60, "MALE");
        chineseIdNumber = new ChineseIdNumber();

        when(faker.random()).thenReturn(randomService);
        when(faker.options().option(ChineseIdNumber.LOCATIONS)).thenReturn("110000");
        when(faker.date().birthday(18, 60)).thenReturn(LocalDate.of(1990, 1, 1));
    }

    @Test
    public void testGenerateValid() {
        // Given
        when(randomService.nextInt(10)).thenReturn(1, 2, 3);

        // When
        PersonIdNumber result = chineseIdNumber.generateValid(faker, request);

        // Then
        assertEquals("11000019900101123", result.getIdNumber());
        assertEquals(LocalDate.of(1990, 1, 1), result.getBirthday());
        assertEquals("MALE", result.getGender());
    }
}
