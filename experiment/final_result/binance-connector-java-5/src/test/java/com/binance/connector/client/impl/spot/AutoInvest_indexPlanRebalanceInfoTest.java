
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

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class AutoInvest_indexPlanRebalanceInfoTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testIndexPlanRebalanceInfo() {
        String path = "/sapi/v1/lending/auto-invest/rebalance/history";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createAutoInvest().indexPlanRebalanceInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testIndexPlanRebalanceInfoWithParameters() {
        String path = "/sapi/v1/lending/auto-invest/rebalance/history?startTime=1620000000000&endTime=1620100000000&current=1&size=10&recvWindow=5000";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1620000000000L);
        parameters.put("endTime", 1620100000000L);
        parameters.put("current", 1L);
        parameters.put("size", 10L);
        parameters.put("recvWindow", 5000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createAutoInvest().indexPlanRebalanceInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testIndexPlanRebalanceInfoWithoutParameters() {
        String path = "/sapi/v1/lending/auto-invest/rebalance/history";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createAutoInvest().indexPlanRebalanceInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
