
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;

public class Medical_procedureCodeTest {
    private Medical medical;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker(Locale.US);
        medical = new Medical(faker);
    }

    @Test
    void testProcedureCodeUS() {
        // will use icd-10-pcs - https://www.johndcook.com/blog/2019/05/05/regex_icd_codes/
        for (int i = 0; i < 100; i++) { // Loading the US data is slow.
            String actual = medical.procedureCode();
            assertThat(actual).matches("[0-9A-HJ-NP-RT-Z][0-9A-HJ-NP-RT-Z][0-9A-HJ-NP-RT-Z][0-9A-HJ-NP-RT-Z][0-9A-HJ-NP-RT-Z][0-9A-HJ-NP-RT-Z][0-9A-HJ-NP-RT-Z]");
        }
    }
}
