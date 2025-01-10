
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.UrlBuilder;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Market_bookTickerTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testBookTickerWithoutSymbol() {
        String path = "/api/v3/ticker/bookTicker";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.bookTicker(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testBookTickerWithSymbol() {
        String path = "/api/v3/ticker/bookTicker?symbol=BTCUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.bookTicker(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testBookTickerWithSymbols() {
        String path = String.format("/api/v3/ticker/bookTicker?symbols=%s",
                UrlBuilder.urlEncode("[\"BNBUSDT\",\"BTCUSDT\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.bookTicker(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testBookTickerWithSymbolAndSymbols() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        parameters.put("symbols", symbols);

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.bookTicker(parameters));
    }
}
