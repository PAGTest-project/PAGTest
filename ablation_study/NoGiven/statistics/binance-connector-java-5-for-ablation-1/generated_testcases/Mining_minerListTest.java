
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.MockWebServerDispatcher;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class Mining_minerListTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url("/").toString();
    }

    @Test
    public void testMinerList() {
        String path = "/sapi/v1/mining/worker/list?algo=sha256&userName=test";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");
        parameters.put("userName", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher("/", path, "mock_response", HttpMethod.GET, 200);
        mockWebServer.setDispatcher(dispatcher);

        Mining mining = new Mining(baseUrl, "api_key", "secret_key", true, null);
        String result = mining.minerList(parameters);
        assertEquals("mock_response", result);
    }

    @Test
    public void testMinerListWithoutParameters() {
        String path = "/sapi/v1/mining/worker/list";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher("/", path, "mock_response", HttpMethod.GET, 200);
        mockWebServer.setDispatcher(dispatcher);

        Mining mining = new Mining(baseUrl, "api_key", "secret_key", true, null);
        assertThrows(BinanceConnectorException.class, () -> mining.minerList(parameters));
    }
}
