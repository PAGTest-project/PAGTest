
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Locale;

public class Medical_diagnosisCodeTest {
    private Medical medical;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders(Locale.ENGLISH);
        medical = new Medical(faker);
    }

    @RepeatedTest(100)
    void testDiagnosisCode() {
        // Assuming the regex for diagnosis code is similar to procedure code
        String diagnosisCode = medical.diagnosisCode();
        assertThat(diagnosisCode).matches("^[a-hj-np-zA-HJ-NP-Z0-9]{7}$");
    }
}
