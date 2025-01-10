
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

public class VIPLoans_repayTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private VIPLoans vipLoans;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.vipLoans = new VIPLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth(null, null));
    }

    @Test
    public void testRepaySuccess() {
        String path = "/sapi/v1/loan/vip/repay?orderId=123456&amount=100.0";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", 123456L);
        parameters.put("amount", 100.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = vipLoans.repay(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testRepayMissingOrderId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 100.0);

        assertThrows(BinanceConnectorException.class, () -> vipLoans.repay(parameters));
    }

    @Test
    public void testRepayMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", 123456L);

        assertThrows(BinanceConnectorException.class, () -> vipLoans.repay(parameters));
    }
}
