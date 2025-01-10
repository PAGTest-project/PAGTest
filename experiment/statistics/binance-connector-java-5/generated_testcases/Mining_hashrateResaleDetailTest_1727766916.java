
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
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
        this.mining = new Mining(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testHashrateResaleDetail() {
        String path = "/sapi/v1/mining/hash-transfer/profit/details?configId=123&userName=testUser";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);
        parameters.put("userName", "testUser");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.hashrateResaleDetail(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testHashrateResaleDetailMissingConfigId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "testUser");

        assertThrows(BinanceConnectorException.class, () -> mining.hashrateResaleDetail(parameters));
    }

    @Test
    public void testHashrateResaleDetailMissingUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 123);

        assertThrows(BinanceConnectorException.class, () -> mining.hashrateResaleDetail(parameters));
    }
}
