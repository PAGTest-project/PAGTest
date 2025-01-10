
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Futures_futuresOrderBookHistoryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Futures futures;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.futures = new Futures(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testFuturesOrderBookHistoryWithoutParameters() {
        String path = "/sapi/v1/futures/histDataLink";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresOrderBookHistory(parameters));
    }

    @Test
    public void testFuturesOrderBookHistoryWithValidParameters() {
        String path = "/sapi/v1/futures/histDataLink";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("dataType", "T_DEPTH");
        parameters.put("startTime", System.currentTimeMillis() - 1000000);
        parameters.put("endTime", System.currentTimeMillis());

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = futures.futuresOrderBookHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFuturesOrderBookHistoryWithMissingSymbol() {
        String path = "/sapi/v1/futures/histDataLink";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("dataType", "T_DEPTH");
        parameters.put("startTime", System.currentTimeMillis() - 1000000);
        parameters.put("endTime", System.currentTimeMillis());

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresOrderBookHistory(parameters));
    }

    @Test
    public void testFuturesOrderBookHistoryWithMissingDataType() {
        String path = "/sapi/v1/futures/histDataLink";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("startTime", System.currentTimeMillis() - 1000000);
        parameters.put("endTime", System.currentTimeMillis());

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresOrderBookHistory(parameters));
    }

    @Test
    public void testFuturesOrderBookHistoryWithMissingStartTime() {
        String path = "/sapi/v1/futures/histDataLink";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("dataType", "T_DEPTH");
        parameters.put("endTime", System.currentTimeMillis());

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresOrderBookHistory(parameters));
    }

    @Test
    public void testFuturesOrderBookHistoryWithMissingEndTime() {
        String path = "/sapi/v1/futures/histDataLink";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("dataType", "T_DEPTH");
        parameters.put("startTime", System.currentTimeMillis() - 1000000);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresOrderBookHistory(parameters));
    }
}
