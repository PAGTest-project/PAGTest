
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_universalTransferTest {
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
    public void testUniversalTransferSuccess() {
        String path = "/sapi/v1/asset/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_C2C");
        parameters.put("asset", "BTC");
        parameters.put("amount", "1.0");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.universalTransfer(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testUniversalTransferMissingRequiredParameter() {
        String path = "/sapi/v1/asset/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_C2C");
        parameters.put("asset", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_BAD_REQUEST);
        mockWebServer.setDispatcher(dispatcher);

        try {
            String result = wallet.universalTransfer(parameters);
            assertEquals(MockData.MOCK_RESPONSE, result);
        } catch (Exception e) {
            assertEquals("Required parameter 'amount' is missing.", e.getMessage());
        }
    }
}
