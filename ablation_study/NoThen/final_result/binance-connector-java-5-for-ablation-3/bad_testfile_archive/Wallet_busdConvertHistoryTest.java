
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

public class Wallet_busdConvertHistoryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Wallet wallet;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
    }

    @Test
    public void testBusdConvertHistory() {
        String path = "/sapi/v1/asset/convert-transfer/queryByPage";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1620000000000L);
        parameters.put("endTime", 1620100000000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.busdConvertHistory(parameters);
        assertEquals("{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", result);
    }

    @Test
    public void testBusdConvertHistoryMissingStartTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("endTime", 1620100000000L);

        assertThrows(BinanceConnectorException.class, () -> wallet.busdConvertHistory(parameters));
    }

    @Test
    public void testBusdConvertHistoryMissingEndTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1620000000000L);

        assertThrows(BinanceConnectorException.class, () -> wallet.busdConvertHistory(parameters));
    }
}
