
package com.binance.connector.client.impl.spot;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.ProxyAuth;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class Pay_transactionsTest {

    private Pay pay;
    private RequestHandler mockRequestHandler;

    @Before
    public void setUp() {
        String baseUrl = "https://api.binance.com";
        String apiKey = "testApiKey";
        String secretKey = "testSecretKey";
        boolean showLimitUsage = true;
        ProxyAuth proxy = Mockito.mock(ProxyAuth.class);

        mockRequestHandler = Mockito.mock(RequestHandler.class);
        pay = new Pay(baseUrl, apiKey, new HmacSignatureGenerator(secretKey), showLimitUsage, proxy);
    }

    @Test
    public void testTransactions() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", 1609459200000L);
        parameters.put("endTime", 1609545600000L);

        String expectedResponse = "{\"success\":true}";
        when(mockRequestHandler.sendSignedRequest(Mockito.anyString(), Mockito.eq("/sapi/v1/pay/transactions"), Mockito.eq(parameters), Mockito.eq(HttpMethod.GET), Mockito.eq(true)))
                .thenReturn(expectedResponse);

        String actualResponse = pay.transactions(parameters);
        assertEquals(expectedResponse, actualResponse);
    }
}
