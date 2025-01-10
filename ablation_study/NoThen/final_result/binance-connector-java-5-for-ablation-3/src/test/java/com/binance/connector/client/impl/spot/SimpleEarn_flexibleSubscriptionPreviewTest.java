
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class SimpleEarn_flexibleSubscriptionPreviewTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testFlexibleSubscriptionPreview() {
        String path = "/sapi/v1/simple-earn/flexible/subscriptionPreview?productId=40607&amount=1.1";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "40607");
        parameters.put("amount", 1.1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSimpleEarn().flexibleSubscriptionPreview(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testFlexibleSubscriptionPreviewWithoutProductId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 1.1);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        client.createSimpleEarn().flexibleSubscriptionPreview(parameters);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testFlexibleSubscriptionPreviewWithoutAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "40607");

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        client.createSimpleEarn().flexibleSubscriptionPreview(parameters);
    }
}
