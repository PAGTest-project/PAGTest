
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static net.datafaker.providers.base.Image.ImageType.SVG;
import static net.datafaker.providers.base.Image.ImageType.PNG;

public class Image_base64Test {

    @Test
    public void testBase64_SVG() {
        Image image = new Image(null);
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(SVG, 256, 256);
        String result = image.base64(config);
        assertNotNull(result);
    }

    @Test
    public void testBase64_PNG() {
        Image image = new Image(null);
        Image.Base64ImageRuleConfig config = new Image.Base64ImageRuleConfig(PNG, 256, 256);
        String result = image.base64(config);
        assertNotNull(result);
    }
}
