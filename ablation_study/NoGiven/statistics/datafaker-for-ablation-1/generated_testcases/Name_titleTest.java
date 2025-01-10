
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Name_titleTest {
    private Name name;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseFaker();
        name = new Name(baseProviders);
    }

    @Test
    void testTitle() {
        String title = name.title();
        assertThat(title).matches("([\\w']+\\.?( )?){3,}");
    }
}
