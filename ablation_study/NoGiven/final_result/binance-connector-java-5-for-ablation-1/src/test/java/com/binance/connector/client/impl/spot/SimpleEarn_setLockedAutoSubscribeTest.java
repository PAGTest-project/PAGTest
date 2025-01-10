
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

public class SimpleEarn_setLockedAutoSubscribeTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testSetLockedAutoSubscribe() {
        String path = "/sapi/v1/simple-earn/locked/setAutoSubscribe?positionId=1234&autoSubscribe=true";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("positionId", "1234");
        parameters.put("autoSubscribe", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSimpleEarn().setLockedAutoSubscribe(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSetLockedAutoSubscribeWithoutPositionId() {
        String path = "/sapi/v1/simple-earn/locked/setAutoSubscribe?autoSubscribe=true";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("autoSubscribe", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createSimpleEarn().setLockedAutoSubscribe(parameters));
    }

    @Test
    public void testSetLockedAutoSubscribeWithoutAutoSubscribe() {
        String path = "/sapi/v1/simple-earn/locked/setAutoSubscribe?positionId=1234";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("positionId", "1234");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createSimpleEarn().setLockedAutoSubscribe(parameters));
    }
}
