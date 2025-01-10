
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

public class Market_ticker24HTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testTicker24HWithSymbol() {
        String path = "/api/v3/ticker/24hr?symbol=BNBUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.ticker24H(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTicker24HWithSymbols() {
        String path = String.format("/api/v3/ticker/24hr?symbols=%s",
                UrlBuilder.urlEncode("[\"BNBUSDT\",\"BTCUSDT\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.ticker24H(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTicker24HWithDoubleParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.ticker24H(parameters));
    }

    @Test
    public void testTicker24HWithInvalidSymbolsType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbols", "BNBUSDT");

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.ticker24H(parameters));
    }
}
