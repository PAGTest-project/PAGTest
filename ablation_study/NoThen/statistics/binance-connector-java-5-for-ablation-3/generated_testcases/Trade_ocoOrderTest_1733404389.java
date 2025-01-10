
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.MockWebServerDispatcher;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class Trade_ocoOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testOcoOrderWithoutParameters() {
        String path = "/api/v3/order/oco";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Trade trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
        assertThrows(BinanceConnectorException.class, () -> trade.ocoOrder(parameters));
    }

    @Test
    public void testOcoOrderWithValidParameters() {
        String path = "/api/v3/order/oco?symbol=BNBUSDT&side=BUY&quantity=1&price=100&stopPrice=90";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 1);
        parameters.put("price", 100);
        parameters.put("stopPrice", 90);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Trade trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
        String result = trade.ocoOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testOcoOrderWithMissingRequiredParameter() {
        String path = "/api/v3/order/oco?symbol=BNBUSDT&side=BUY&quantity=1&price=100";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 1);
        parameters.put("price", 100);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Trade trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
        assertThrows(BinanceConnectorException.class, () -> trade.ocoOrder(parameters));
    }
}
