
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Name_titleTest {
    private Name name;

    @BeforeEach
    public void setUp() {
        BaseFaker faker = new BaseFaker();
        name = new Name(faker);
    }

    @Test
    public void testTitle() {
        String title = name.title();
        assertThat(title).matches("[a-zA-Z\\s]+ [a-zA-Z\\s]+ [a-zA-Z\\s]+");
    }
}
