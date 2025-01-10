
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Medical_diagnosisCodeTest {
    private Medical medical;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        medical = new Medical(faker);
    }

    @RepeatedTest(100)
    void testDiagnosisCode() {
        // will use icd-10 - https://regex101.com/library/nJ1wC4
        String diagnosisCode = medical.diagnosisCode();
        assertThat(diagnosisCode).matches("^[A-Z][0-9][0-9AB]\\.?[0-9A-TV-Z]{0,4}$");
    }
}
