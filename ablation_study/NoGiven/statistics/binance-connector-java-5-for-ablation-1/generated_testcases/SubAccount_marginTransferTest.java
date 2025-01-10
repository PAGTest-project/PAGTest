
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
import com.binance.connector.client.utils.UrlBuilder;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class SubAccount_marginTransferTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testMarginTransferSuccess() {
        String path = String.format("/sapi/v1/sub-account/margin/transfer?email=%s&asset=BTC&amount=1&type=1",
                UrlBuilder.urlEncode("alice@test.com"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("asset", "BTC");
        parameters.put("amount", 1);
        parameters.put("type", 1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSubAccount().marginTransfer(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testMarginTransferMissingRequiredParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("asset", "BTC");
        parameters.put("type", 1);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createSubAccount().marginTransfer(parameters));
    }

    @Test
    public void testMarginTransferInvalidType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("asset", "BTC");
        parameters.put("amount", 1);
        parameters.put("type", "invalidType");

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createSubAccount().marginTransfer(parameters));
    }
}
