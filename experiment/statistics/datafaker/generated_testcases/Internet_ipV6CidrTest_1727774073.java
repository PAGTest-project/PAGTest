
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Internet_ipV6CidrTest {

    @Test
    public void testIpV6Cidr() {
        Internet internet = new Internet(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public int nextInt(int bound) {
                        return 1; // Ensure predictable output for testing
                    }
                };
            }

            @Override
            public Name name() {
                return new Name() {
                    @Override
                    public String firstName() {
                        return "test";
                    }

                    @Override
                    public String lastName() {
                        return "user";
                    }
                };
            }

            @Override
            public String resolve(String key) {
                return "example.com";
            }

            @Override
            public Context getContext() {
                return new Context() {
                    @Override
                    public java.util.Locale getLocale() {
                        return java.util.Locale.getDefault();
                    }
                };
            }
        });

        String result = internet.ipV6Cidr();
        assertTrue(result.matches("([0-9a-f]{1,4}:){7}[0-9a-f]{1,4}/\\d{1,3}"));
    }
}
