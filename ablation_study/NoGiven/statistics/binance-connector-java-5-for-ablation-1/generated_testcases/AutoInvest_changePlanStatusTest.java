
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

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class AutoInvest_changePlanStatusTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testChangePlanStatusSuccess() {
        String path = "/sapi/v1/lending/auto-invest/plan/edit-status?planId=123&status=ONGOING";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("planId", 123L);
        parameters.put("status", "ONGOING");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createAutoInvest().changePlanStatus(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testChangePlanStatusMissingPlanId() {
        String path = "/sapi/v1/lending/auto-invest/plan/edit-status?status=ONGOING";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("status", "ONGOING");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createAutoInvest().changePlanStatus(parameters));
    }

    @Test
    public void testChangePlanStatusMissingStatus() {
        String path = "/sapi/v1/lending/auto-invest/plan/edit-status?planId=123";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("planId", 123L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createAutoInvest().changePlanStatus(parameters));
    }
}
