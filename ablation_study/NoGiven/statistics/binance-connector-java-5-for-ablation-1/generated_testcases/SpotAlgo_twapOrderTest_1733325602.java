
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
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
        parameters.put("duration", 300L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = spotAlgo.twapOrder(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTwapOrderMissingSymbol() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("side", "BUY");
        parameters.put("quantity", 1.0);
        parameters.put("duration", 300L);

        spotAlgo.twapOrder(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTwapOrderMissingSide() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("quantity", 1.0);
        parameters.put("duration", 300L);

        spotAlgo.twapOrder(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTwapOrderMissingQuantity() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("duration", 300L);

        spotAlgo.twapOrder(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTwapOrderMissingDuration() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("quantity", 1.0);

        spotAlgo.twapOrder(parameters);
    }
}
