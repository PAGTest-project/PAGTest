
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

public class AutoInvest_redeemIndexPlanTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testRedeemIndexPlan() {
        String path = "/sapi/v1/lending/auto-invest/redeem?indexId=1234&redemptionPercentage=10";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("indexId", 1234L);
        parameters.put("redemptionPercentage", 10);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createAutoInvest().redeemIndexPlan(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testRedeemIndexPlanWithoutIndexId() {
        String path = "/sapi/v1/lending/auto-invest/redeem?redemptionPercentage=10";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("redemptionPercentage", 10);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createAutoInvest().redeemIndexPlan(parameters));
    }

    @Test
    public void testRedeemIndexPlanWithoutRedemptionPercentage() {
        String path = "/sapi/v1/lending/auto-invest/redeem?indexId=1234";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("indexId", 1234L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createAutoInvest().redeemIndexPlan(parameters));
    }
}
