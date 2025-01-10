
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
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class SimpleEarn_subscribeFlexibleProductTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private SimpleEarn simpleEarn;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.simpleEarn = new SimpleEarn(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth());
    }

    @Test
    public void testSubscribeFlexibleProductSuccess() {
        String path = "/sapi/v1/simple-earn/flexible/subscribe?productId=40607&amount=1.1";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "40607");
        parameters.put("amount", 1.1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = simpleEarn.subscribeFlexibleProduct(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSubscribeFlexibleProductMissingProductId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 1.1);

        assertThrows(BinanceConnectorException.class, () -> simpleEarn.subscribeFlexibleProduct(parameters));
    }

    @Test
    public void testSubscribeFlexibleProductMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "40607");

        assertThrows(BinanceConnectorException.class, () -> simpleEarn.subscribeFlexibleProduct(parameters));
    }
}
