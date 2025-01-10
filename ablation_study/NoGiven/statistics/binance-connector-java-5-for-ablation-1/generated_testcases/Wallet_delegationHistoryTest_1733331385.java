
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_delegationHistoryTest {
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
    public void testDelegationHistory() {
        String path = "/sapi/v1/asset/custody/transfer-history";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("startTime", 1609459200000L);
        parameters.put("endTime", 1609545600000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.delegationHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testDelegationHistoryMissingEmail() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1609459200000L);
        parameters.put("endTime", 1609545600000L);

        String result = wallet.delegationHistory(parameters);
        assertEquals("Parameter validation failed", result);
    }

    @Test
    public void testDelegationHistoryMissingStartTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("endTime", 1609545600000L);

        String result = wallet.delegationHistory(parameters);
        assertEquals("Parameter validation failed", result);
    }

    @Test
    public void testDelegationHistoryMissingEndTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("startTime", 1609459200000L);

        String result = wallet.delegationHistory(parameters);
        assertEquals("Parameter validation failed", result);
    }
}
