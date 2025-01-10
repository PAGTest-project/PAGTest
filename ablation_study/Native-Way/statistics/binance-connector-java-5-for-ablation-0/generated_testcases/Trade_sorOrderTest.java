
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Trade_sorOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testSorOrder() {
        String path = "/api/v3/sor/order?symbol=BNBUSDT&side=BUY&type=MARKET&quantity=1";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "MARKET");
        parameters.put("quantity", 1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClientImpl client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        Trade trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
        String result = trade.sorOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSorOrderWithoutParameters() {
        String path = "/api/v3/sor/order";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClientImpl client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        Trade trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
        assertThrows(BinanceConnectorException.class, () -> trade.sorOrder(parameters));
    }
}
