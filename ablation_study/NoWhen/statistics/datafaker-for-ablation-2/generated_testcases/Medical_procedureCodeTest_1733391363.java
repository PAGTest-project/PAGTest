
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;

public class Medical_procedureCodeTest {
    private Medical medical;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker(new Locale("en", "au"));
        medical = new Medical(faker);
    }

    @RepeatedTest(100)
    void testProcedureCodeAU() {
        String actual = medical.procedureCode();
        assertThat(actual).matches("[A-Z][0-9]{1,2}\\.[0-9]{1,2}");
    }
}
