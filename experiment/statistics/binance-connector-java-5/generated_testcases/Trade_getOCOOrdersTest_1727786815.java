
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Trade_getOCOOrdersTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Trade trade;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        RequestHandler requestHandler = new RequestHandler(MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), new ProxyAuth());
        this.trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, new ProxyAuth());
    }

    @Test
    public void testGetOCOOrders() {
        String path = "/api/v3/allOrderList";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("fromId", 123L);
        parameters.put("startTime", 1607639040000L);
        parameters.put("endTime", 1607642640000L);
        parameters.put("limit", 500);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = trade.getOCOOrders(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
