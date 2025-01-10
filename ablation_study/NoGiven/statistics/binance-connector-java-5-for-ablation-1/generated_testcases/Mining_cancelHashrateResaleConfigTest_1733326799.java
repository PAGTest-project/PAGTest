
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

public class Mining_cancelHashrateResaleConfigTest {
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
    public void testCancelHashrateResaleConfigSuccess() {
        String path = "/sapi/v1/mining/hash-transfer/config/cancel?configId=123&userName=test";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);
        parameters.put("userName", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.cancelHashrateResaleConfig(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testCancelHashrateResaleConfigMissingConfigId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "test");

        assertThrows(BinanceConnectorException.class, () -> {
            mining.cancelHashrateResaleConfig(parameters);
        });
    }

    @Test
    public void testCancelHashrateResaleConfigMissingUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);

        assertThrows(BinanceConnectorException.class, () -> {
            mining.cancelHashrateResaleConfig(parameters);
        });
    }
}
