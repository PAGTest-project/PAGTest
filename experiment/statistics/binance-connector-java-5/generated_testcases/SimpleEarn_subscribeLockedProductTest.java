
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

public class SimpleEarn_subscribeLockedProductTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private SimpleEarn simpleEarn;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.simpleEarn = new SimpleEarn(baseUrl, "apiKey", "secretKey", false, new ProxyAuth(null, null));
    }

    @Test
    public void testSubscribeLockedProductSuccess() {
        String path = "/sapi/v1/simple-earn/locked/subscribe";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "project123");
        parameters.put("amount", 100.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = simpleEarn.subscribeLockedProduct(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSubscribeLockedProductMissingProjectId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 100.0);

        assertThrows(BinanceConnectorException.class, () -> simpleEarn.subscribeLockedProduct(parameters));
    }

    @Test
    public void testSubscribeLockedProductMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "project123");

        assertThrows(BinanceConnectorException.class, () -> simpleEarn.subscribeLockedProduct(parameters));
    }
}
