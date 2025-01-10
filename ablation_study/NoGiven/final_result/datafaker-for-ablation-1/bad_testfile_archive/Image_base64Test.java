
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Image_base64Test {

    private Image image;

    @BeforeEach
    public void setUp() {
        image = new Image(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }
        });
    }

    @Test
    public void testBase64SVG() {
        Image.Base64ImageRuleConfig config = Image.ImageBuilder.builder().type(Image.ImageType.SVG).build();
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/svg+xml;base64,"));
    }

    @Test
    public void testBase64RasterImage() {
        Image.Base64ImageRuleConfig config = Image.ImageBuilder.builder().type(Image.ImageType.PNG).build();
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/png;base64,"));
    }
}
