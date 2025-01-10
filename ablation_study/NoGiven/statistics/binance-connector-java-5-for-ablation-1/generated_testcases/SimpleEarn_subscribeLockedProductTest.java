
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
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
        this.simpleEarn = new SimpleEarn(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth(null, null));
    }

    @Test
    public void testSubscribeLockedProductSuccess() {
        String path = "/sapi/v1/simple-earn/locked/subscribe";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "testProjectId");
        parameters.put("amount", 100.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = simpleEarn.subscribeLockedProduct(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testSubscribeLockedProductMissingProjectId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 100.0);

        simpleEarn.subscribeLockedProduct(parameters);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testSubscribeLockedProductMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "testProjectId");

        simpleEarn.subscribeLockedProduct(parameters);
    }
}
