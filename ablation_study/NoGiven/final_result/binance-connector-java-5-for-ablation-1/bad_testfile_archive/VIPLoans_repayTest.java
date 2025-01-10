
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
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
        String path = "/sapi/v1/loan/vip/repay";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", 123456L);
        parameters.put("amount", 100.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = vipLoans.repay(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepayMissingOrderId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 100.0);

        vipLoans.repay(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepayMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", 123456L);

        vipLoans.repay(parameters);
    }
}
