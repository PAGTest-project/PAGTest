
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;

public class UserData_createListenKeyTest {

    private UserData userData;

    @Mock
    private RequestHandler requestHandler;

    private final String baseUrl = "https://api.binance.com";
    private final String apiKey = "testApiKey";
    private final boolean showLimitUsage = false;
    private final ProxyAuth proxy = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userData = new UserData(baseUrl, apiKey, showLimitUsage, proxy);
        userData.setRequestHandler(requestHandler);
    }

    @Test
    public void testCreateListenKey() {
        String expectedListenKey = "testListenKey";
        when(requestHandler.sendApiRequest(baseUrl, "/api/v3/userDataStream", null, HttpMethod.POST, showLimitUsage))
            .thenReturn(expectedListenKey);

        String result = userData.createListenKey();
        assertEquals(expectedListenKey, result);
    }
}
