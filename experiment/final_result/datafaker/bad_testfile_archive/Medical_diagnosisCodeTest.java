
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Medical_diagnosisCodeTest {

    private net.datafaker.providers.healthcare.Disease disease;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders();
        disease = new net.datafaker.providers.healthcare.Disease(faker);
    }

    @Test
    void testDiagnosisCode() {
        String diagnosisCode = disease.icd10();
        assertThat(diagnosisCode).matches("^[a-hj-np-zA-HJ-NP-Z0-9]{7}$");
    }
}
