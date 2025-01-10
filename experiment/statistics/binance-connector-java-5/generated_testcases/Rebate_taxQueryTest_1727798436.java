
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

public class Rebate_taxQueryTest {

    private Rebate rebate;
    private RequestHandler mockRequestHandler;

    @Before
    public void setUp() {
        String baseUrl = "https://api.binance.com";
        String apiKey = "testApiKey";
        String secretKey = "testSecretKey";
        boolean showLimitUsage = true;
        ProxyAuth proxy = null;

        mockRequestHandler = Mockito.mock(RequestHandler.class);
        rebate = new Rebate(baseUrl, apiKey, new HmacSignatureGenerator(secretKey), showLimitUsage, proxy) {
            @Override
            public RequestHandler getRequestHandler() {
                return mockRequestHandler;
            }
        };
    }

    @Test
    public void testTaxQuery() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startTime", 1609459200000L);
        parameters.put("endTime", 1609545600000L);

        String expectedResponse = "{\"success\":true}";
        when(mockRequestHandler.sendSignedRequest(Mockito.anyString(), Mockito.eq("/sapi/v1/rebate/taxQuery"), Mockito.eq(parameters), Mockito.eq(HttpMethod.GET), Mockito.eq(true))).thenReturn(expectedResponse);

        String actualResponse = rebate.taxQuery(parameters);
        assertEquals(expectedResponse, actualResponse);
    }
}
