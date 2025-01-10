
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_universalTransferTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testUniversalTransfer() {
        String path = "/sapi/v1/asset/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_C2C");
        parameters.put("asset", "BTC");
        parameters.put("amount", "0.1");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createWallet().universalTransfer(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testUniversalTransferMissingRequiredParameter() {
        String path = "/sapi/v1/asset/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_C2C");
        parameters.put("asset", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        client.createWallet().universalTransfer(parameters);
    }
}
