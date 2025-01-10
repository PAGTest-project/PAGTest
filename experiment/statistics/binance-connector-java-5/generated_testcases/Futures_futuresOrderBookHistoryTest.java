
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
        this.futures = new Futures(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
    }

    @Test
    public void testFuturesOrderBookHistory() {
        String path = "/sapi/v1/futures/histDataLink?symbol=BTCUSDT&dataType=T_DEPTH&startTime=12345678&endTime=12345679";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("dataType", "T_DEPTH");
        parameters.put("startTime", 12345678L);
        parameters.put("endTime", 12345679L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = futures.futuresOrderBookHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFuturesOrderBookHistoryMissingParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("dataType", "T_DEPTH");
        parameters.put("startTime", 12345678L);

        assertThrows(BinanceConnectorException.class, () -> {
            futures.futuresOrderBookHistory(parameters);
        });
    }
}
