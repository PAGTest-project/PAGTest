
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

public class Futures_futuresTransferHistoryTest {
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
    public void testFuturesTransferHistory() {
        String path = String.format("/sapi/v1/futures/transfer?asset=%s&startTime=%s", "USDT", 1620000000000L);
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");
        parameters.put("startTime", 1620000000000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = futures.futuresTransferHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFuturesTransferHistoryMissingAsset() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1620000000000L);

        assertThrows(BinanceConnectorException.class, () -> {
            futures.futuresTransferHistory(parameters);
        });
    }

    @Test
    public void testFuturesTransferHistoryMissingStartTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");

        assertThrows(BinanceConnectorException.class, () -> {
            futures.futuresTransferHistory(parameters);
        });
    }
}
