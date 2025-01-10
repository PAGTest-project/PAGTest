
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Name_titleTest {
    private Name name;

    @BeforeEach
    void setUp() {
        BaseFaker faker = new BaseFaker();
        name = faker.name();
    }

    @Test
    void testTitle() {
        String title = name.title();
        assertThat(title).matches("([\\w']+\\.?( )?){3,}");
    }
}
