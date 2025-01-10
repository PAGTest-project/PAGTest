
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

public class Trade_cancelReplaceTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCancelReplaceSuccess() {
        String path = "/api/v3/order/cancelReplace?symbol=BNBUSDT&side=BUY&type=LIMIT&cancelReplaceMode=STOP_ON_FAILURE";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("cancelReplaceMode", "STOP_ON_FAILURE");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Trade trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
        String result = trade.cancelReplace(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testCancelReplaceMissingParameters() {
        String path = "/api/v3/order/cancelReplace";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Trade trade = new Trade(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
        assertThrows(BinanceConnectorException.class, () -> trade.cancelReplace(parameters));
    }
}
