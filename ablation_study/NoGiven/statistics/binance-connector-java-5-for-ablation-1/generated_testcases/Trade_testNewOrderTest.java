
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceClientException;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Trade_testNewOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testTestNewOrderSuccess() {
        String path = "/api/v3/order/test?symbol=BNBUSDT&side=BUY&type=MARKET";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "MARKET");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Trade trade = new Trade(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
        String result = trade.testNewOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTestNewOrderMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("side", "BUY");
        parameters.put("type", "MARKET");

        Trade trade = new Trade(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
        assertThrows(BinanceConnectorException.class, () -> trade.testNewOrder(parameters));
    }

    @Test
    public void testTestNewOrderMissingSide() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("type", "MARKET");

        Trade trade = new Trade(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
        assertThrows(BinanceConnectorException.class, () -> trade.testNewOrder(parameters));
    }

    @Test
    public void testTestNewOrderMissingType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");

        Trade trade = new Trade(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
        assertThrows(BinanceConnectorException.class, () -> trade.testNewOrder(parameters));
    }
}
