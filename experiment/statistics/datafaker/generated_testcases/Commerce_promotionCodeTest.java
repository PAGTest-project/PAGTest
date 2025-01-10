
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Commerce_promotionCodeTest {
    private Commerce commerce;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        when(faker.getContext()).thenReturn(mock(FakerContext.class));
        commerce = new Commerce(faker);
    }

    @Test
    public void testPromotionCode() {
        when(faker.resolve("commerce.promotion_code.adjective")).thenReturn("Super");
        when(faker.resolve("commerce.promotion_code.noun")).thenReturn("Sale");
        when(faker.number().digits(6)).thenReturn("123456");

        String result = commerce.promotionCode(6);
        assertEquals("SuperSale123456", result);
    }
}
