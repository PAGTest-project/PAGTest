
package com.binance.connector.client.impl.spot;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SubAccount_getIpRestrictionTest {

    private SubAccount subAccount;
    private RequestHandler requestHandler;
    private final String baseUrl = "https://api.binance.com";
    private final boolean showLimitUsage = true;
    private final String IP_RESTRICTION = "/sapi/v1/sub-account/subAccountApi/ipRestriction";

    @Before
    public void setUp() {
        requestHandler = mock(RequestHandler.class);
        subAccount = new SubAccount(baseUrl, "apiKey", "secretKey", showLimitUsage, null) {
            @Override
            public String getIpRestriction(Map<String, Object> parameters) {
                return requestHandler.sendSignedRequest(baseUrl, IP_RESTRICTION, parameters, HttpMethod.GET, showLimitUsage);
            }
        };
    }

    @Test
    public void testGetIpRestriction() {
        // Given
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("subAccountApiKey", "subAccountApiKey");

        String expectedResponse = "{\"ipRestriction\":\"192.168.1.1\"}";

        // When
        when(requestHandler.sendSignedRequest(baseUrl, IP_RESTRICTION, parameters, HttpMethod.GET, showLimitUsage))
                .thenReturn(expectedResponse);

        String actualResponse = subAccount.getIpRestriction(parameters);

        // Then
        assertEquals(expectedResponse, actualResponse);
        verify(requestHandler, times(1)).sendSignedRequest(baseUrl, IP_RESTRICTION, parameters, HttpMethod.GET, showLimitUsage);
    }
}
