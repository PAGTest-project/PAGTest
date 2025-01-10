
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

public class SpotAlgo_twapOrderTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private SpotAlgo spotAlgo;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.spotAlgo = new SpotAlgo(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testTwapOrderSuccess() {
        String path = "/sapi/v1/algo/spot/newOrderTwap";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 1.0);
        parameters.put("duration", 3600L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = spotAlgo.twapOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testTwapOrderMissingRequiredParameter() {
        String path = "/sapi/v1/algo/spot/newOrderTwap";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("duration", 3600L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_BAD_REQUEST);
        mockWebServer.setDispatcher(dispatcher);

        try {
            String result = spotAlgo.twapOrder(parameters);
            assertEquals(MockData.MOCK_RESPONSE, result);
        } catch (Exception e) {
            assertEquals("Parameter 'quantity' is required.", e.getMessage());
        }
    }
}
