
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

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCancelHashrateResaleConfig() {
        String path = "/sapi/v1/mining/hash-transfer/config/cancel?configId=123&userName=test";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);
        parameters.put("userName", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        Mining mining = new Mining(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        String result = mining.cancelHashrateResaleConfig(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testCancelHashrateResaleConfigMissingConfigId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "test");

        Mining mining = new Mining(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        assertThrows(BinanceConnectorException.class, () -> mining.cancelHashrateResaleConfig(parameters));
    }

    @Test
    public void testCancelHashrateResaleConfigMissingUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);

        Mining mining = new Mining(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        assertThrows(BinanceConnectorException.class, () -> mining.cancelHashrateResaleConfig(parameters));
    }
}
