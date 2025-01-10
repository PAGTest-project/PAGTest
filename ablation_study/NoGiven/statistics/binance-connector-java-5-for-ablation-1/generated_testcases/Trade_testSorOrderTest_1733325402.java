
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

@RunWith(MockitoJUnitRunner.class)
public class Trade_testSorOrderTest {

    private Trade trade;
    private Map<String, Object> parameters;

    @Mock
    private RequestHandler requestHandler;

    @Before
    public void setUp() {
        trade = new Trade("https://api.binance.com", "apiKey", "secretKey", false, null);
        parameters = new HashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("quantity", 1.0);
    }

    @Test
    public void testSorOrder() {
        // Given
        when(requestHandler.sendSignedRequest(anyString(), anyString(), anyMap(), eq(HttpMethod.POST), eq(false)))
            .thenReturn("success");

        // When
        String result = trade.testSorOrder(parameters);

        // Then
        assertEquals("success", result);
        verify(requestHandler).sendSignedRequest(eq("https://api.binance.com"), eq("/sapi/v1/sub-account/virtualSubAccount"), eq(parameters), eq(HttpMethod.POST), eq(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSorOrderMissingQuantity() {
        // Given
        parameters.remove("quantity");

        // When
        trade.testSorOrder(parameters);

        // Then (exception is expected)
    }
}
