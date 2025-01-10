
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
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Blvt_subscribeTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Blvt blvt;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.blvt = new Blvt(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testSubscribeSuccess() {
        String path = "/sapi/v1/blvt/subscribe?tokenName=BTCUP&cost=100";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("tokenName", "BTCUP");
        parameters.put("cost", 100);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = blvt.subscribe(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testSubscribeMissingTokenName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("cost", 100);

        assertThrows(BinanceConnectorException.class, () -> {
            blvt.subscribe(parameters);
        });
    }

    @Test
    public void testSubscribeMissingCost() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("tokenName", "BTCUP");

        assertThrows(BinanceConnectorException.class, () -> {
            blvt.subscribe(parameters);
        });
    }
}
