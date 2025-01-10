
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
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Futures_futuresTransferHistoryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private static final Long startTime = 1617600000000L;
    private static final Long endTime = 1617686400000L;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testFuturesTransferHistory() {
        String path = String.format("/sapi/v1/futures/transfer?asset=%s&startTime=%s", "USDT", startTime);
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");
        parameters.put("startTime", startTime);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Futures futures = new Futures(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        String result = futures.futuresTransferHistory(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result.replace("{\"data\":\"", "").replace("\"}", ""));
    }

    @Test
    public void testFuturesTransferHistoryMissingStartTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");

        Futures futures = new Futures(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        assertThrows(BinanceConnectorException.class, () -> futures.futuresTransferHistory(parameters));
    }

    @Test
    public void testFuturesTransferHistoryMissingAsset() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", startTime);

        Futures futures = new Futures(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        assertThrows(BinanceConnectorException.class, () -> futures.futuresTransferHistory(parameters));
    }
}
