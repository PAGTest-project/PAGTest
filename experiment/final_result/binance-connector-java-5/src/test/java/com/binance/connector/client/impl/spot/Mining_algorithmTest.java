
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.RequestHandler;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Mining_algorithmTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Mining mining;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.mining = new Mining(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
    }

    @Test
    public void testAlgorithm() {
        String path = "/sapi/v1/mining/pub/algoList";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.algorithm(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testAlgorithmWithParameters() {
        String path = "/sapi/v1/mining/pub/algoList?recvWindow=5000";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("recvWindow", 5000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.algorithm(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testAlgorithmWithInvalidParameters() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("invalidParam", "invalidValue");

        assertThrows(BinanceConnectorException.class, () -> mining.algorithm(parameters));
    }
}
