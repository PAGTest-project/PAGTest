
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.ParameterChecker;

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
        String result = client.createWallet().delegationHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testDelegationHistoryWithoutEmail() {
        String path = "/sapi/v1/asset/custody/transfer-history";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1609459200000L);
        parameters.put("endTime", 1609545600000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createWallet().delegationHistory(parameters));
    }

    @Test
    public void testDelegationHistoryWithoutStartTime() {
        String path = "/sapi/v1/asset/custody/transfer-history";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("endTime", 1609545600000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createWallet().delegationHistory(parameters));
    }

    @Test
    public void testDelegationHistoryWithoutEndTime() {
        String path = "/sapi/v1/asset/custody/transfer-history";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("startTime", 1609459200000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createWallet().delegationHistory(parameters));
    }
}
