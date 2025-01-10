
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.ProxyAuth;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class UserData_closeIsolatedMarginListenKeyTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCloseIsolatedMarginListenKey() {
        String path = "/sapi/v1/userDataStream/isolated?symbol=BNBUSDT&listenKey=test";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("listenKey", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        UserData userData = new UserData(baseUrl, MockData.API_KEY, true, null);
        String result = userData.closeIsolatedMarginListenKey(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testCloseIsolatedMarginListenKeyMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("listenKey", "test");

        UserData userData = new UserData(baseUrl, MockData.API_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> userData.closeIsolatedMarginListenKey(parameters));
    }

    @Test
    public void testCloseIsolatedMarginListenKeyMissingListenKey() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        UserData userData = new UserData(baseUrl, MockData.API_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> userData.closeIsolatedMarginListenKey(parameters));
    }
}
