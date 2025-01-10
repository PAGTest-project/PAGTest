
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;

public class Medical_procedureCodeTest {

    private net.datafaker.providers.healthcare.MedicalProcedure medicalProcedure;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker(new Locale("en", "au"));
        medicalProcedure = faker.medicalProcedure();
    }

    @Test
    void testProcedureCode() {
        String actual = medicalProcedure.icd10();
        assertThat(actual).matches("[A-Z][0-9]{1,2}\\.[0-9]{1,2}");
    }
}
