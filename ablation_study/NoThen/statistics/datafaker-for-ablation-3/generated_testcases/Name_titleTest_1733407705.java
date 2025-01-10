
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class Name_titleTest {
    private BaseFaker faker;
    private Name name;

    @BeforeEach
    public void setUp() {
        faker = new BaseFaker(new Random(10L));
        name = faker.name();
    }

    @Test
    public void testTitle() {
        String title = name.title();
        assertThat(title).matches(".+ .+ .+");
    }

    @Test
    public void testTitleComponents() {
        String descriptor = name.resolve("name.title.descriptor");
        String level = name.resolve("name.title.level");
        String job = name.resolve("name.title.job");

        String title = name.title();
        assertThat(title).contains(descriptor);
        assertThat(title).contains(level);
        assertThat(title).contains(job);
    }
}
