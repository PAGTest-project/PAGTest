
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

public class Wallet_apiTradingStatusTest {
    private Wallet wallet;
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
        baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        RequestHandler requestHandler = new RequestHandler(MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), null);
        wallet = new Wallet(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testApiTradingStatus() {
        String path = "/sapi/v1/account/apiTradingStatus";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.apiTradingStatus(parameters);
        String expectedResponse = "{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}";
        assertEquals(expectedResponse, result);
    }
}
