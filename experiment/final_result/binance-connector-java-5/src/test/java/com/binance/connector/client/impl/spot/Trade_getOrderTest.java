
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

public class Trade_getOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Trade trade;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.trade = new Trade(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, new ProxyAuth(null, null));
    }

    @Test
    public void testGetOrderSuccess() {
        String path = "/api/v3/order?symbol=BNBUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = trade.getOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testGetOrderMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        assertThrows(BinanceConnectorException.class, () -> trade.getOrder(parameters));
    }
}
