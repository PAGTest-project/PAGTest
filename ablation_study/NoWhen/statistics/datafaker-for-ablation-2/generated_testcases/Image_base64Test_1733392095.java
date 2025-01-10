
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static net.datafaker.providers.base.Image.ImageType.*;

public class Image_base64Test {

    private Image image;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {};
        image = new Image(faker);
    }

    @Test
    void testBase64SVG() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(SVG, 256, 256);
        assertThat(image.base64(config)).startsWith("data:image/svg+xml;base64,");
    }

    @Test
    void testBase64PNG() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(PNG, 256, 256);
        assertThat(image.base64(config)).startsWith("data:image/png;base64,");
    }

    @Test
    void testBase64JPEG() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(JPEG, 256, 256);
        assertThat(image.base64(config)).startsWith("data:image/jpeg;base64,");
    }

    @Test
    void testBase64GIF() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(GIF, 256, 256);
        assertThat(image.base64(config)).startsWith("data:image/gif;base64,");
    }

    @Test
    void testBase64BMP() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(BMP, 256, 256);
        assertThat(image.base64(config)).startsWith("data:image/bmp;base64,");
    }

    @Test
    void testBase64TIFF() {
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(TIFF, 256, 256);
        assertThat(image.base64(config)).startsWith("data:image/tiff;base64,");
    }
}
