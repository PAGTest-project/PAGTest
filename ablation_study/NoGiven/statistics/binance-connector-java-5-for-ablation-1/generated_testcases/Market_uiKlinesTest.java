
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

public class Market_uiKlinesTest {
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
    public void testUiKlinesWithValidParameters() {
        String path = "/api/v3/uiKlines?symbol=BNBUSDT&interval=1m";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("interval", "1m");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.uiKlines(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testUiKlinesWithoutSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("interval", "1m");

        assertThrows(BinanceConnectorException.class, () -> market.uiKlines(parameters));
    }

    @Test
    public void testUiKlinesWithoutInterval() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        assertThrows(BinanceConnectorException.class, () -> market.uiKlines(parameters));
    }

    @Test
    public void testUiKlinesWithAdditionalParameters() {
        String path = "/api/v3/uiKlines?symbol=BNBUSDT&interval=1m&startTime=1617600000000&endTime=1617603600000&limit=500";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("interval", "1m");
        parameters.put("startTime", 1617600000000L);
        parameters.put("endTime", 1617603600000L);
        parameters.put("limit", 500);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = market.uiKlines(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }
}
