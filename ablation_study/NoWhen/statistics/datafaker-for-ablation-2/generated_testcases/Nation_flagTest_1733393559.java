
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Nation_flagTest {
    private Nation nation;

    @BeforeEach
    public void setUp() {
        nation = new Nation(new BaseProviders() {
            @Override
            public FakeValuesService fakeValuesService() {
                return new FakeValuesService();
            }

            @Override
            public Context getContext() {
                return new Context();
            }

            @Override
            public RandomService random() {
                return new RandomService();
            }
        });
    }

    @Test
    void testFlag() {
        String flag = nation.flag();
        assertThat(flag).isNotBlank();
    }

    @Test
    void testNationality() {
        String nationality = nation.nationality();
        assertThat(nationality).isNotBlank();
    }

    @Test
    void testLanguage() {
        String language = nation.language();
        assertThat(language).isNotBlank();
    }

    @Test
    void testIsoLanguage() {
        String isoLanguage = nation.isoLanguage();
        assertThat(isoLanguage).matches("[a-z]{2}");
    }

    @Test
    void testIsoCountry() {
        String isoCountry = nation.isoCountry();
        assertThat(isoCountry).matches("[A-Z]{2}");
    }
}
