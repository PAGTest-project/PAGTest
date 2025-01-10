
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Margin_newOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Margin margin;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.margin = new Margin(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
    }

    @Test
    public void testNewOrderSuccess() {
        String path = "/sapi/v1/margin/order";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = margin.newOrder(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result.replace("{\"data\":\"", "").replace("\"}", ""));
    }

    @Test(expected = BinanceConnectorException.class)
    public void testNewOrderMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");

        margin.newOrder(parameters);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testNewOrderMissingSide() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("type", "LIMIT");

        margin.newOrder(parameters);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testNewOrderMissingType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");

        margin.newOrder(parameters);
    }
}
