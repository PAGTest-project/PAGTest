
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

public class AutoInvest_indexPlanRedeemHistoryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private AutoInvest autoInvest;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.autoInvest = new AutoInvest(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth());
    }

    @Test
    public void testIndexPlanRedeemHistoryWithoutRequestId() {
        String path = "/sapi/v1/lending/auto-invest/redeem/history";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> autoInvest.indexPlanRedeemHistory(parameters));
    }

    @Test
    public void testIndexPlanRedeemHistoryWithValidParameters() {
        String path = "/sapi/v1/lending/auto-invest/redeem/history";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("requestId", 12345L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = autoInvest.indexPlanRedeemHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
