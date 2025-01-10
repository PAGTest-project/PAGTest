
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.SpotClientImpl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_withdrawTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testWithdrawSuccess() {
        String path = "/sapi/v1/capital/withdraw/apply";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        parameters.put("amount", 1.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createWallet().withdraw(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testWithdrawMissingRequiredParameter() {
        String path = "/sapi/v1/capital/withdraw/apply";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BTC");
        parameters.put("address", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_BAD_REQUEST);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        try {
            String result = client.createWallet().withdraw(parameters);
            assertEquals(MockData.MOCK_RESPONSE, result);
        } catch (Exception e) {
            assertEquals("Parameter 'amount' is required.", e.getMessage());
        }
    }
}
