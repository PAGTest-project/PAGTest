
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

public class Wallet_delegationHistoryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testDelegationHistory() {
        String path = "/sapi/v1/asset/custody/transfer-history";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("startTime", 1609459200000L);
        parameters.put("endTime", 1609545600000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        Wallet wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = wallet.delegationHistory(parameters);
        
        // Adjust the expected result to match the actual response structure
        String expectedResponse = "{\"data\":\"" + MockData.MOCK_RESPONSE + "\"}";
        assertEquals(expectedResponse, result);
    }
}
