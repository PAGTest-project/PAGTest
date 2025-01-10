
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;
import net.datafaker.providers.base.PersonIdNumber;
import net.datafaker.providers.base.PersonIdNumber.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static net.datafaker.idnumbers.SouthAfricanIdNumber.isValidEnZASsn;
import static net.datafaker.idnumbers.SouthAfricanIdNumber.sequentialNumber;
import static net.datafaker.providers.base.PersonIdNumber.Gender.FEMALE;
import static net.datafaker.providers.base.PersonIdNumber.Gender.MALE;

public class SouthAfricanIdNumber_generateValidTest {

    private SouthAfricanIdNumber idNumberGenerator;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        idNumberGenerator = new SouthAfricanIdNumber();
        faker = new BaseProviders() {
            @Override
            public Gender gender() {
                return FEMALE; // Default to FEMALE for simplicity
            }

            @Override
            public String options() {
                return "08"; // Default to "08" for simplicity
            }

            @Override
            public int number() {
                return 1234; // Default to 1234 for simplicity
            }
        };
    }

    @Test
    public void testGenerateValid_Female() {
        IdNumberRequest request = new IdNumberRequest() {
            @Override
            public Gender gender() {
                return FEMALE;
            }

            @Override
            public String birthday() {
                return "900101"; // Example date for simplicity
            }
        };

        PersonIdNumber idNumber = idNumberGenerator.generateValid(faker, request);
        assertTrue(isValidEnZASsn(idNumber.getIdNumber()));
    }

    @Test
    public void testGenerateValid_Male() {
        IdNumberRequest request = new IdNumberRequest() {
            @Override
            public Gender gender() {
                return MALE;
            }

            @Override
            public String birthday() {
                return "900101"; // Example date for simplicity
            }
        };

        PersonIdNumber idNumber = idNumberGenerator.generateValid(faker, request);
        assertTrue(isValidEnZASsn(idNumber.getIdNumber()));
    }

    @Test
    public void testSequentialNumber_Female() {
        String seqNumber = sequentialNumber(faker, FEMALE);
        int number = Integer.parseInt(seqNumber);
        assertTrue(number >= 0 && number < 5000);
    }

    @Test
    public void testSequentialNumber_Male() {
        String seqNumber = sequentialNumber(faker, MALE);
        int number = Integer.parseInt(seqNumber);
        assertTrue(number >= 5000 && number < 10000);
    }
}
