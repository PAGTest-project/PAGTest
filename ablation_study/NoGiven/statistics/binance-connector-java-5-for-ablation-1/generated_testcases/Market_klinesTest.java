
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Market_klinesTest {
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
    public void testKlinesSuccess() {
        String path = "/api/v3/klines?symbol=BTCUSDT&interval=1m";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("interval", "1m");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.klines(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testKlinesMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("interval", "1m");

        assertThrows(BinanceConnectorException.class, () -> market.klines(parameters));
    }

    @Test
    public void testKlinesMissingInterval() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");

        assertThrows(BinanceConnectorException.class, () -> market.klines(parameters));
    }

    @Test
    public void testKlinesInvalidParameters() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", 123);
        parameters.put("interval", "1m");

        assertThrows(BinanceConnectorException.class, () -> market.klines(parameters));
    }
}
