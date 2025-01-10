
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
        this.futures = new Futures(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testFuturesTransferHistoryWithoutAsset() {
        String path = "/sapi/v1/futures/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", System.currentTimeMillis());

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresTransferHistory(parameters));
    }

    @Test
    public void testFuturesTransferHistoryWithoutStartTime() {
        String path = "/sapi/v1/futures/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresTransferHistory(parameters));
    }

    @Test
    public void testFuturesTransferHistorySuccess() {
        String path = "/sapi/v1/futures/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");
        parameters.put("startTime", System.currentTimeMillis());

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = futures.futuresTransferHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
