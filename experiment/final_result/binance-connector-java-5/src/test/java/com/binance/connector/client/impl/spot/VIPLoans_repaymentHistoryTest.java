
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class VIPLoans_repaymentHistoryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private VIPLoans vipLoans;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        RequestHandler requestHandler = new RequestHandler(MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), null);
        this.vipLoans = new VIPLoans(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
    }

    @Test
    public void testRepaymentHistory() {
        String path = "/sapi/v1/loan/vip/repay/history";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", 123456L);
        parameters.put("loanCoin", "BTC");
        parameters.put("startTime", 1609459200000L);
        parameters.put("endTime", 1609545600000L);
        parameters.put("current", 1L);
        parameters.put("limit", 10L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = vipLoans.repaymentHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
