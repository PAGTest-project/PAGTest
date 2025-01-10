
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Internet_urlTest {

    @Test
    public void testUrl() {
        Internet internet = new Internet(new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public byte[] nextRandomBytes(int count) {
                        byte[] bytes = new byte[count];
                        for (int i = 0; i < count; i++) {
                            bytes[i] = (byte) (i % 2 == 0 ? 0 : 1);
                        }
                        return bytes;
                    }
                };
            }

            @Override
            public Name name() {
                return new Name() {
                    @Override
                    public String firstName() {
                        return "first";
                    }

                    @Override
                    public String lastName() {
                        return "last";
                    }
                };
            }

            @Override
            public Lorem lorem() {
                return new Lorem() {
                    @Override
                    public List<String> words(int num) {
                        return Arrays.asList("word1", "word2");
                    }
                };
            }

            @Override
            public String resolve(String key) {
                return "example.com";
            }

            @Override
            public Locale getContext() {
                return Locale.getDefault();
            }
        });

        String url = internet.url();
        assertNotNull(url);
    }
}
