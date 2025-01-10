
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
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
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
        this.market = new Market(baseUrl, "apiKey", true, new ProxyAuth());
    }

    @Test
    public void testExchangeInfoSymbolAndSymbolsTogether() {
        String path = "/api/v3/exchangeInfo";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("symbols", new ArrayList<String>() {{ add("BTCUSDT"); }});

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoSymbolAndPermissionsTogether() {
        String path = "/api/v3/exchangeInfo";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("permissions", new ArrayList<String>() {{ add("SPOT"); }});

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoSymbolsAndPermissionsTogether() {
        String path = "/api/v3/exchangeInfo";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbols", new ArrayList<String>() {{ add("BTCUSDT"); }});
        parameters.put("permissions", new ArrayList<String>() {{ add("SPOT"); }});

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> market.exchangeInfo(parameters));
    }

    @Test
    public void testExchangeInfoValidSymbols() {
        String path = "/api/v3/exchangeInfo?symbols=[\"BTCUSDT\",\"BNBUSDT\"]";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbols", new ArrayList<String>() {{ add("BTCUSDT"); add("BNBUSDT"); }});

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.exchangeInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testExchangeInfoValidPermissions() {
        String path = "/api/v3/exchangeInfo?permissions=[\"SPOT\",\"MARGIN\"]";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("permissions", new ArrayList<String>() {{ add("SPOT"); add("MARGIN"); }});

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.exchangeInfo(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
