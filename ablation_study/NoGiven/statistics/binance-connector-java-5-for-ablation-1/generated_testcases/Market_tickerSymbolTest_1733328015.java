
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

public class Market_tickerSymbolTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Market market;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.market = new Market(baseUrl, "apiKey", true, null);
    }

    @Test
    public void testTickerSymbolWithSymbol() {
        String path = "/api/v3/ticker/price?symbol=BTCUSDT";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.tickerSymbol(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTickerSymbolWithSymbols() {
        String path = String.format("/api/v3/ticker/price?symbols=%s",
                UrlBuilder.urlEncode("[\"BNBUSDT\",\"BTCUSDT\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.tickerSymbol(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTickerSymbolWithBothSymbolAndSymbols() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        assertThrows(BinanceConnectorException.class, () -> market.tickerSymbol(parameters));
    }

    @Test
    public void testTickerSymbolWithInvalidSymbolsType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        String[] symbols = {"BNBUSDT", "BTCUSDT"};
        parameters.put("symbols", symbols);

        assertThrows(BinanceConnectorException.class, () -> market.tickerSymbol(parameters));
    }
}
