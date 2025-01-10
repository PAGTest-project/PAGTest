
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.ProxyAuth;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_systemStatusTest {
    private Wallet wallet;
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
        baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        wallet = new Wallet(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testSystemStatus() {
        String path = "/sapi/v1/system/status";

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.systemStatus();
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }
}
