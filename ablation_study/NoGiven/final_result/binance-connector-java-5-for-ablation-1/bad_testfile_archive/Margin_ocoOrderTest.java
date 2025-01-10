
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Margin_ocoOrderTest {
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
    public void testOcoOrderSuccess() {
        String path = "/sapi/v1/margin/order/oco?symbol=BNBUSDT&side=BUY&quantity=10&price=500&stopPrice=450";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 10);
        parameters.put("price", 500);
        parameters.put("stopPrice", 450);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = margin.ocoOrder(parameters);
        assertEquals("{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", result);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testOcoOrderMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("side", "BUY");
        parameters.put("quantity", 10);
        parameters.put("price", 500);
        parameters.put("stopPrice", 450);

        margin.ocoOrder(parameters);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testOcoOrderMissingSide() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("quantity", 10);
        parameters.put("price", 500);
        parameters.put("stopPrice", 450);

        margin.ocoOrder(parameters);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testOcoOrderMissingQuantity() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("price", 500);
        parameters.put("stopPrice", 450);

        margin.ocoOrder(parameters);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testOcoOrderMissingPrice() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 10);
        parameters.put("stopPrice", 450);

        margin.ocoOrder(parameters);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testOcoOrderMissingStopPrice() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 10);
        parameters.put("price", 500);

        margin.ocoOrder(parameters);
    }
}
