
package net.datafaker.providers.base;

import net.datafaker.service.FakeValuesService;
import net.datafaker.service.RandomService;
import net.datafaker.service.Context;
import java.util.Locale;
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
                return new FakeValuesService(new Locale("en"), new RandomService());
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
        assertThat(flag).isInstanceOf(String.class);
    }
}
