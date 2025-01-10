
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Commerce_promotionCodeTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        commerce = new Commerce(new BaseProviders());
    }

    @Test
    void testPromotionCodeDefaultDigits() {
        String promotionCode = commerce.promotionCode();
        assertTrue(promotionCode.matches("[a-zA-Z]+\\s[a-zA-Z]+\\s\\d{6}"));
    }

    @Test
    void testPromotionCodeCustomDigits() {
        int customDigits = 8;
        String promotionCode = commerce.promotionCode(customDigits);
        assertTrue(promotionCode.matches("[a-zA-Z]+\\s[a-zA-Z]+\\s\\d{8}"));
    }

    @Test
    void testPromotionCodeNoDigits() {
        int noDigits = 0;
        String promotionCode = commerce.promotionCode(noDigits);
        assertTrue(promotionCode.matches("[a-zA-Z]+\\s[a-zA-Z]+\\s"));
    }
}
