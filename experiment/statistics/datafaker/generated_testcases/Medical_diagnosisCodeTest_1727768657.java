
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Medical_diagnosisCodeTest {

    private Medical medical;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseProviders();
        medical = new Medical(faker);
    }

    @Test
    void testDiagnosisCode() {
        String diagnosisCode = medical.diagnosisCode();
        assertThat(diagnosisCode).matches("^[a-hj-np-zA-HJ-NP-Z0-9]{7}$");
    }
}
