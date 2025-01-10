
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.SpotClientImpl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Margin_ocoOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testOcoOrderSuccess() {
        String path = "/sapi/v1/margin/order/oco?symbol=BNBUSDT&side=BUY&quantity=1&price=50000&stopPrice=49000";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 1);
        parameters.put("price", 50000);
        parameters.put("stopPrice", 49000);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        Margin margin = new Margin(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = margin.ocoOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testOcoOrderMissingParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 1);
        parameters.put("price", 50000);

        Margin margin = new Margin(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        try {
            margin.ocoOrder(parameters);
        } catch (BinanceConnectorException e) {
            assertEquals("Missing mandatory parameter: stopPrice", e.getMessage());
        }
    }
}
