
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
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class SimpleEarn_subscribeFlexibleProductTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private SpotClient client;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
    }

    @Test
    public void testSubscribeFlexibleProductSuccess() {
        String path = "/sapi/v1/simple-earn/flexible/subscribe";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "testProductId");
        parameters.put("amount", 100.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = client.createSimpleEarn().subscribeFlexibleProduct(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSubscribeFlexibleProductMissingProductId() {
        String path = "/sapi/v1/simple-earn/flexible/subscribe";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 100.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> client.createSimpleEarn().subscribeFlexibleProduct(parameters));
    }

    @Test
    public void testSubscribeFlexibleProductMissingAmount() {
        String path = "/sapi/v1/simple-earn/flexible/subscribe";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "testProductId");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> client.createSimpleEarn().subscribeFlexibleProduct(parameters));
    }
}
