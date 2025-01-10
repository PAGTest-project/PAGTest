
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
            RequestHandler getRequestHandler() {
                return requestHandler;
            }
        };
    }

    @Test
    public void testGetIpRestriction_Success() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("subAccountApiKey", "apiKey123");

        when(requestHandler.sendSignedRequest(baseUrl, IP_RESTRICTION, parameters, HttpMethod.GET, showLimitUsage))
                .thenReturn("successResponse");

        String result = subAccount.getIpRestriction(parameters);
        assertEquals("successResponse", result);

        verify(requestHandler).sendSignedRequest(baseUrl, IP_RESTRICTION, parameters, HttpMethod.GET, showLimitUsage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIpRestriction_MissingEmail() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("subAccountApiKey", "apiKey123");

        subAccount.getIpRestriction(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIpRestriction_MissingSubAccountApiKey() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", "test@example.com");

        subAccount.getIpRestriction(parameters);
    }
}
