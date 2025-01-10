
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
import com.binance.connector.client.utils.MockWebServerDispatcher;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class Market_tickerSymbolTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url("/").toString();
    }

    @Test
    public void testTickerSymbolWithSymbol() {
        String path = "/api/v3/ticker/price?symbol=BNBUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher("/", path, "{\"symbol\":\"BNBUSDT\",\"price\":\"200\"}", HttpMethod.GET, 200);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, "apiKey", true, null);
        String result = market.tickerSymbol(parameters);
        assertEquals("{\"symbol\":\"BNBUSDT\",\"price\":\"200\"}", result);
    }

    @Test
    public void testTickerSymbolWithSymbols() {
        String path = "/api/v3/ticker/price?symbols=%5B%22BNBUSDT%22,%22BTCUSDT%22%5D";
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher("/", path, "[{\"symbol\":\"BNBUSDT\",\"price\":\"200\"},{\"symbol\":\"BTCUSDT\",\"price\":\"50000\"}]", HttpMethod.GET, 200);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, "apiKey", true, null);
        String result = market.tickerSymbol(parameters);
        assertEquals("[{\"symbol\":\"BNBUSDT\",\"price\":\"200\"},{\"symbol\":\"BTCUSDT\",\"price\":\"50000\"}]", result);
    }

    @Test
    public void testTickerSymbolWithBothSymbolAndSymbols() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Market market = new Market(baseUrl, "apiKey", true, null);
        assertThrows(BinanceConnectorException.class, () -> market.tickerSymbol(parameters));
    }

    @Test
    public void testTickerSymbolWithInvalidSymbols() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbols", "invalid");

        Market market = new Market(baseUrl, "apiKey", true, null);
        assertThrows(BinanceConnectorException.class, () -> market.tickerSymbol(parameters));
    }
}
