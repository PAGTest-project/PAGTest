
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Image_base64Test {

    private Image image;

    @BeforeEach
    public void setUp() {
        image = new Image(new BaseProviders() {});
    }

    @Test
    void testBase64SVG() {
        Image.Base64ImageRuleConfig config = Image.ImageBuilder.builder()
                .type(Image.ImageType.SVG)
                .width(256)
                .height(256)
                .build();
        String result = image.base64(config);
        assertThat(result).startsWith("data:image/svg+xml;base64,");
    }

    @Test
    void testBase64RasterImage() {
        Image.Base64ImageRuleConfig config = Image.ImageBuilder.builder()
                .type(Image.ImageType.PNG)
                .width(256)
                .height(256)
                .build();
        String result = image.base64(config);
        assertThat(result).startsWith("data:image/png;base64,");
    }
}
