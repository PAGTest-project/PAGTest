
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Commerce_promotionCodeTest {

    private Commerce commerce;
    private BaseProviders faker;

    @BeforeEach
    public void setupEnvironment() {
        faker = Mockito.mock(BaseProviders.class);
        when(faker.getContext()).thenReturn(Mockito.mock(FakerContext.class));
        commerce = new Commerce(faker);
    }

    @Test
    public void testPromotionCode() {
        // Given
        when(faker.resolve("commerce.promotion_code.adjective")).thenReturn("Super");
        when(faker.resolve("commerce.promotion_code.noun")).thenReturn("Sale");
        when(faker.number().digits(6)).thenReturn("123456");

        // When
        String promotionCode = commerce.promotionCode(6);

        // Then
        assertEquals("SuperSale123456", promotionCode);
    }
}
