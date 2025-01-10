
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

public class Market_exchangeInfoTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testExchangeInfoWithSymbol() {
        String path = "/api/v3/exchangeInfo?symbol=BNBUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.exchangeInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testExchangeInfoWithSymbols() {
        String path = String.format("/api/v3/exchangeInfo?symbols=%s",
                UrlBuilder.urlEncode("[\"BNBUSDT\",\"BTCUSDT\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.exchangeInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testExchangeInfoWithPermissions() {
        String path = String.format("/api/v3/exchangeInfo?permissions=%s",
                UrlBuilder.urlEncode("[\"SPOT\",\"MARGIN\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("SPOT");
        permissions.add("MARGIN");
        parameters.put("permissions", permissions);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Market market = new Market(baseUrl, null, false, null);
        String result = market.exchangeInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testExchangeInfoWithSymbolAndSymbols() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("symbols", new ArrayList<String>());

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoWithSymbolAndPermissions() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("permissions", new ArrayList<String>());

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoWithSymbolsAndPermissions() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbols", new ArrayList<String>());
        parameters.put("permissions", new ArrayList<String>());

        Market market = new Market(baseUrl, null, false, null);
        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }
}
