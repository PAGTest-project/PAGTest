
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_withdrawTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Wallet wallet;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        RequestHandler requestHandler = new RequestHandler(MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), null);
        this.wallet = new Wallet(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testWithdrawSuccess() {
        String path = "/sapi/v1/capital/withdraw/apply";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        parameters.put("amount", "0.01");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.withdraw(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testWithdrawMissingCoin() {
        String path = "/sapi/v1/capital/withdraw/apply";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        parameters.put("amount", "0.01");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.withdraw(parameters);
        assertEquals("Parameter 'coin' is required", result);
    }

    @Test
    public void testWithdrawMissingAddress() {
        String path = "/sapi/v1/capital/withdraw/apply";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("amount", "0.01");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.withdraw(parameters);
        assertEquals("Parameter 'address' is required", result);
    }

    @Test
    public void testWithdrawMissingAmount() {
        String path = "/sapi/v1/capital/withdraw/apply";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.withdraw(parameters);
        assertEquals("Parameter 'amount' is required", result);
    }
}
