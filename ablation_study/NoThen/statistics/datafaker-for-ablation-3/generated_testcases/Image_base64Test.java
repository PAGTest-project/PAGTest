
package net.datafaker.providers.base;

import net.datafaker.providers.base.Image.ImageType;
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
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(ImageType.SVG, 256, 256);
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/svg+xml;base64,"));
    }

    @Test
    public void testBase64PNG() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(ImageType.PNG, 256, 256);
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/png;base64,"));
    }

    @Test
    public void testBase64JPEG() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(ImageType.JPEG, 256, 256);
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/jpeg;base64,"));
    }

    @Test
    public void testBase64GIF() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(ImageType.GIF, 256, 256);
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/gif;base64,"));
    }

    @Test
    public void testBase64BMP() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(ImageType.BMP, 256, 256);
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/bmp;base64,"));
    }

    @Test
    public void testBase64TIFF() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(ImageType.TIFF, 256, 256);
        String result = image.base64(config);
        assertTrue(result.startsWith("data:image/tiff;base64,"));
    }
}
