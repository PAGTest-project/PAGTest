
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
    private Market market;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.market = new Market(baseUrl, "apiKey", true, null);
    }

    @Test
    public void testExchangeInfoSymbolAndSymbolsTogether() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("symbols", new ArrayList<>());

        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoSymbolAndPermissionsTogether() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("permissions", new ArrayList<>());

        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoSymbolsAndPermissionsTogether() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbols", new ArrayList<>());
        parameters.put("permissions", new ArrayList<>());

        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoValidSymbols() {
        String path = String.format("/api/v3/exchangeInfo?symbols=%s",
                UrlBuilder.urlEncode("[\"BNBUSDT\",\"BTCUSDT\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BNBUSDT");
        symbols.add("BTCUSDT");
        parameters.put("symbols", symbols);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.exchangeInfo(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testExchangeInfoValidPermissions() {
        String path = String.format("/api/v3/exchangeInfo?permissions=%s",
                UrlBuilder.urlEncode("[\"SPOT\",\"MARGIN\"]"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("SPOT");
        permissions.add("MARGIN");
        parameters.put("permissions", permissions);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.exchangeInfo(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }
}
