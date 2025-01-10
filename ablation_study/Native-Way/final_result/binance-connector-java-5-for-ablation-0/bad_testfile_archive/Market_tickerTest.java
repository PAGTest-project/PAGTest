
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

public class Market_tickerTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testTickerWithSymbol() {
        String path = "/api/v3/ticker?symbol=BNBUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, "apiKey", true, null);
        String result = market.ticker(parameters);
        assertEquals("{\"data\":{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}}", result);
    }

    @Test
    public void testTickerWithSymbols() {
        String path = String.format("/api/v3/ticker?symbols=%s", UrlBuilder.urlEncode("[\"BNBUSDT\",\"BTCUSDT\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, "apiKey", true, null);
        String result = market.ticker(parameters);
        assertEquals("{\"data\":{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}}", result);
    }

    @Test
    public void testTickerWithBothParameters() {
        String path = "/api/v3/ticker";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, "apiKey", true, null);
        assertThrows(BinanceConnectorException.class, () -> market.ticker(parameters));
    }

    @Test
    public void testTickerWithoutParameters() {
        String path = "/api/v3/ticker";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, "apiKey", true, null);
        assertThrows(BinanceConnectorException.class, () -> market.ticker(parameters));
    }
}
