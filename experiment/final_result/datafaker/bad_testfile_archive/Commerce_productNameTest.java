
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Commerce_productNameTest {

    private Commerce commerce;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        when(faker.getContext()).thenReturn(mock(FakerContext.class));
        commerce = new Commerce(faker);
    }

    @Test
    public void testProductName() {
        when(faker.resolve("commerce.product_name.adjective")).thenReturn("Cool");
        when(faker.resolve("commerce.product_name.material")).thenReturn("Wooden");
        when(faker.resolve("commerce.product_name.product")).thenReturn("Chair");

        String expectedProductName = "Cool Wooden Chair";
        String actualProductName = commerce.productName();

        assertEquals(expectedProductName, actualProductName);
    }
}
