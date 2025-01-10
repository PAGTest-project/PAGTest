
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

public class Mining_statsticsListTest {
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
    public void testStatsticsList() {
        String path = "/sapi/v1/mining/statistics/user/status?algo=sha256&userName=test";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");
        parameters.put("userName", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.statsticsList(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testStatsticsListMissingAlgo() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "test");

        assertThrows(BinanceConnectorException.class, () -> {
            mining.statsticsList(parameters);
        });
    }

    @Test
    public void testStatsticsListMissingUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");

        assertThrows(BinanceConnectorException.class, () -> {
            mining.statsticsList(parameters);
        });
    }
}
