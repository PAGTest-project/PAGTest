
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

public class Wallet_universalTransferTest {
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
    public void testUniversalTransferSuccess() {
        String path = "/sapi/v1/asset/transfer?type=MAIN_C2C&asset=BTC&amount=0.01";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_C2C");
        parameters.put("asset", "BTC");
        parameters.put("amount", 0.01);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String response = wallet.universalTransfer(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", response);
    }

    @Test
    public void testUniversalTransferWithoutAmount() {
        String path = "/sapi/v1/asset/transfer?type=MAIN_C2C&asset=BTC";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_C2C");
        parameters.put("asset", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> wallet.universalTransfer(parameters));
    }

    @Test
    public void testUniversalTransferWithoutAsset() {
        String path = "/sapi/v1/asset/transfer?type=MAIN_C2C&amount=0.01";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_C2C");
        parameters.put("amount", 0.01);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> wallet.universalTransfer(parameters));
    }

    @Test
    public void testUniversalTransferWithoutType() {
        String path = "/sapi/v1/asset/transfer?asset=BTC&amount=0.01";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "BTC");
        parameters.put("amount", 0.01);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> wallet.universalTransfer(parameters));
    }
}
