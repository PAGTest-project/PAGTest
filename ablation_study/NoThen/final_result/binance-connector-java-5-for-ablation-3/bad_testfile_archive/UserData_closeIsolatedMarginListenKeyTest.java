
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

public class UserData_closeIsolatedMarginListenKeyTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private UserData userData;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.userData = new UserData(baseUrl, MockData.API_KEY, true, new ProxyAuth(null, null));
    }

    @Test
    public void testCloseIsolatedMarginListenKeyWithoutParameters() {
        String path = "/sapi/v1/userDataStream/isolated";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> userData.closeIsolatedMarginListenKey(parameters));
    }

    @Test
    public void testCloseIsolatedMarginListenKeyWithValidParameters() {
        String path = "/sapi/v1/userDataStream/isolated";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("listenKey", "validListenKey");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String response = userData.closeIsolatedMarginListenKey(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", response);
    }

    @Test
    public void testCloseIsolatedMarginListenKeyWithInvalidSymbol() {
        String path = "/sapi/v1/userDataStream/isolated";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", 12345); // Invalid type for symbol
        parameters.put("listenKey", "validListenKey");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> userData.closeIsolatedMarginListenKey(parameters));
    }

    @Test
    public void testCloseIsolatedMarginListenKeyWithInvalidListenKey() {
        String path = "/sapi/v1/userDataStream/isolated";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("listenKey", 12345); // Invalid type for listenKey

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> userData.closeIsolatedMarginListenKey(parameters));
    }
}
