
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

public class Mining_hashrateResaleDetailTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Mining mining;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.mining = new Mining(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, ProxyAuth.NO_PROXY);
    }

    @Test
    public void testHashrateResaleDetail() {
        String path = "/sapi/v1/mining/hash-transfer/profit/details?configId=123&userName=test";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);
        parameters.put("userName", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.hashrateResaleDetail(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testHashrateResaleDetailInvalidConfigId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", "invalid");
        parameters.put("userName", "test");

        try {
            mining.hashrateResaleDetail(parameters);
        } catch (IllegalArgumentException e) {
            assertEquals("configId must be of type Integer", e.getMessage());
        }
    }

    @Test
    public void testHashrateResaleDetailInvalidUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);
        parameters.put("userName", 12345);

        try {
            mining.hashrateResaleDetail(parameters);
        } catch (IllegalArgumentException e) {
            assertEquals("userName must be of type String", e.getMessage());
        }
    }
}
