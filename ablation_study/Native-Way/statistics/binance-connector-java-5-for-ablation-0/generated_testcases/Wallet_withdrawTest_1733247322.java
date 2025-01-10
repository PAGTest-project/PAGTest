
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.MockWebServerDispatcher;
import com.binance.connector.client.utils.RequestHandler;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class Wallet_withdrawTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Wallet wallet;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url("/").toString();
        RequestHandler requestHandler = new RequestHandler("apiKey", null, null);
        this.wallet = new Wallet(baseUrl, requestHandler, false);
    }

    @Test
    public void testWithdrawSuccess() {
        String path = "/sapi/v1/capital/withdraw/apply";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        parameters.put("amount", "0.01");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher("/", path, "{\"success\":true}", HttpMethod.POST, 200);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.withdraw(parameters);
        assertEquals("{\"success\":true}", result);
    }

    @Test
    public void testWithdrawMissingCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        parameters.put("amount", "0.01");

        assertThrows(BinanceConnectorException.class, () -> wallet.withdraw(parameters));
    }

    @Test
    public void testWithdrawMissingAddress() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("amount", "0.01");

        assertThrows(BinanceConnectorException.class, () -> wallet.withdraw(parameters));
    }

    @Test
    public void testWithdrawMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");

        assertThrows(BinanceConnectorException.class, () -> wallet.withdraw(parameters));
    }
}
