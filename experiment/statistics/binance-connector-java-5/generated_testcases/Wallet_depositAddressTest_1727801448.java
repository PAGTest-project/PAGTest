
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

public class Wallet_depositAddressTest {
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
    public void testDepositAddress() {
        String path = "/sapi/v1/capital/deposit/address?coin=BNB";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BNB");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.depositAddress(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testDepositAddressWithoutCoinParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        assertThrows(BinanceConnectorException.class, () -> wallet.depositAddress(parameters));
    }
}
