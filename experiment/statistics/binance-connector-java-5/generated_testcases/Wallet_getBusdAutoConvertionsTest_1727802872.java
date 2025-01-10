
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.RequestHandler;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_getBusdAutoConvertionsTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testGetBusdAutoConvertions() {
        String path = "/sapi/v1/capital/contract/convertible-coins";

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        Wallet wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = wallet.getBusdAutoConvertions();
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testGetBusdAutoConvertionsException() {
        String path = "/sapi/v1/capital/contract/convertible-coins";

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_INTERNAL_SERVER_ERROR);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        Wallet wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> wallet.getBusdAutoConvertions());
    }
}
