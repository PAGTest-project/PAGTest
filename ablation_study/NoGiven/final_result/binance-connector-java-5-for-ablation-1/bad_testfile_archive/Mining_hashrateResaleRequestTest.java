
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Mining_hashrateResaleRequestTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Mining mining;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.mining = new Mining(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth(null, null));
    }

    @Test
    public void testHashrateResaleRequestSuccess() {
        String path = "/sapi/v1/mining/hash-transfer/config";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "testUser");
        parameters.put("algo", "sha256");
        parameters.put("endDate", 1672531200000L);
        parameters.put("startDate", 1672444800000L);
        parameters.put("toPoolUser", "poolUser");
        parameters.put("hashRate", 500000000000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.hashrateResaleRequest(parameters);
        assertEquals("{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", result);
    }

    @Test
    public void testHashrateResaleRequestMissingParameters() {
        String path = "/sapi/v1/mining/hash-transfer/config";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "testUser");
        parameters.put("algo", "sha256");
        parameters.put("endDate", 1672531200000L);
        parameters.put("startDate", 1672444800000L);
        parameters.put("toPoolUser", "poolUser");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> mining.hashrateResaleRequest(parameters));
    }
}
