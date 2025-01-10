
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Medical_diagnosisCodeTest {

    @Test
    public void testDiagnosisCode() {
        // Given
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        Medical medical = new Medical(faker);
        String expectedRegex = "A00";
        String expectedCode = "A00";

        when(faker.fakeValuesService().resolve("medical.diagnosis_code.icd10", medical, null)).thenReturn(expectedRegex);
        when(faker.regexify(expectedRegex)).thenReturn(expectedCode);

        // When
        String actualCode = medical.diagnosisCode();

        // Then
        assertEquals(expectedCode, actualCode);
    }
}
