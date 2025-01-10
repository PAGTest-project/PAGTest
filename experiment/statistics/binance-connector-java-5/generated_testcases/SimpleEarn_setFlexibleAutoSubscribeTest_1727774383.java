
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

public class SimpleEarn_setFlexibleAutoSubscribeTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private SimpleEarn simpleEarn;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.simpleEarn = new SimpleEarn(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth(null, null));
    }

    @Test
    public void testSetFlexibleAutoSubscribe() {
        String path = "/sapi/v1/simple-earn/flexible/setAutoSubscribe?productId=BUSD001&autoSubscribe=true";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "BUSD001");
        parameters.put("autoSubscribe", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = simpleEarn.setFlexibleAutoSubscribe(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSetFlexibleAutoSubscribeMissingProductId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("autoSubscribe", true);

        assertThrows(BinanceConnectorException.class, () -> simpleEarn.setFlexibleAutoSubscribe(parameters));
    }

    @Test
    public void testSetFlexibleAutoSubscribeMissingAutoSubscribe() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "BUSD001");

        assertThrows(BinanceConnectorException.class, () -> simpleEarn.setFlexibleAutoSubscribe(parameters));
    }
}
