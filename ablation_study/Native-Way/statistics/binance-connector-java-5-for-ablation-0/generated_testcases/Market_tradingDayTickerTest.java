
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

public class Market_tradingDayTickerTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final String path = "/api/v3/ticker/tradingDay";

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testTradingDayTickerWithSymbol() {
        String testPath = String.format("%s?symbol=BNBUSDT", path);
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, testPath, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.tradingDayTicker(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTradingDayTickerWithSymbols() {
        String testPath = String.format("%s?symbols=%s", path, UrlBuilder.urlEncode("[\"BNBUSDT\",\"BTCUSDT\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, testPath, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.tradingDayTicker(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTradingDayTickerWithBothSymbolAndSymbols() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.tradingDayTicker(parameters));
    }

    @Test
    public void testTradingDayTickerWithoutSymbolOrSymbols() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.tradingDayTicker(parameters));
    }
}
