
package net.datafaker.idnumbers;

import net.datafaker.providers.base.BaseProviders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SwedenIdNumber_generateInvalidTest {

    private SwedenIdNumber swedenIdNumber;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        swedenIdNumber = new SwedenIdNumber();
        faker = Mockito.mock(BaseProviders.class);
    }

    @Test
    void testGenerateInvalid() {
        // Given
        when(faker.numerify(Mockito.anyString())).thenReturn("121212-1213");
        when(faker.options()).thenReturn(new BaseProviders.Options() {
            @Override
            public String option(String[] options) {
                return options[0];
            }
        });

        // When
        String invalidSsn = swedenIdNumber.generateInvalid(faker);

        // Then
        assertThat(SwedenIdNumber.isValidSwedishSsn(invalidSsn)).isFalse();
    }
}
