
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class UserData_extendListenKeyTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private UserData userData;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.userData = new UserData(baseUrl, MockData.API_KEY, false, new ProxyAuth(null, null));
    }

    @Test
    public void testExtendListenKeyWithoutParameters() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        assertThrows(BinanceConnectorException.class, () -> userData.extendListenKey(parameters));
    }

    @Test
    public void testExtendListenKeyWithValidParameters() {
        String path = "/api/v3/userDataStream";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("listenKey", "validListenKey");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.PUT, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = userData.extendListenKey(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
