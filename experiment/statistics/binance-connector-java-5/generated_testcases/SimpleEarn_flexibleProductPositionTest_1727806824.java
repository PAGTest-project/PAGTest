
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class SimpleEarn_flexibleProductPositionTest {
    private SimpleEarn simpleEarn;
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void setUp() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.simpleEarn = new SimpleEarn(baseUrl, "apiKey", "secretKey", false, new ProxyAuth());
    }

    @Test
    public void testFlexibleProductPosition() {
        String path = "/sapi/v1/simple-earn/flexible/position";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = simpleEarn.flexibleProductPosition(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
