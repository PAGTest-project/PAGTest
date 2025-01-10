
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
import com.binance.connector.client.utils.ParameterChecker;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class SimpleEarn_lockedSubscriptionPreviewTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private static final double amount = 1.1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLockedSubscriptionPreview() {
        String path = "/sapi/v1/simple-earn/locked/subscriptionPreview?projectId=40607&amount=1.1";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "40607");
        parameters.put("amount", amount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSimpleEarn().lockedSubscriptionPreview(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testLockedSubscriptionPreviewWithoutProjectId() {
        String path = "/sapi/v1/simple-earn/locked/subscriptionPreview?amount=1.1";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", amount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createSimpleEarn().lockedSubscriptionPreview(parameters));
    }

    @Test
    public void testLockedSubscriptionPreviewWithoutAmount() {
        String path = "/sapi/v1/simple-earn/locked/subscriptionPreview?projectId=40607";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "40607");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createSimpleEarn().lockedSubscriptionPreview(parameters));
    }
}
