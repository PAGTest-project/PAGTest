
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_usernameTest {
    private Internet internet;
    private BaseFaker faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseFaker();
        internet = new Internet(faker);
    }

    @Test
    public void testUsername() {
        String username = internet.username();
        assertTrue(username.matches("[a-z.]+"));
    }

    @Test
    public void testUsernameWithWhitespace() {
        Name name = new Name(faker) {
            @Override
            public String firstName() {
                return "first name";
            }

            @Override
            public String lastName() {
                return "last name";
            }
        };
        faker.name = name;
        String username = internet.username();
        assertEquals("first.name.last.name", username);
    }

    @Test
    public void testUsernameWithApostrophe() {
        Name name = new Name(faker) {
            @Override
            public String firstName() {
                return "first'name";
            }

            @Override
            public String lastName() {
                return "last'name";
            }
        };
        faker.name = name;
        String username = internet.username();
        assertEquals("firstname.lastname", username);
    }
}
