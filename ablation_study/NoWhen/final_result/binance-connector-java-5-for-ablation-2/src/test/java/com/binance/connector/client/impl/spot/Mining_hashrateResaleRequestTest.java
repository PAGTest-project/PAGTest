
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Mining_hashrateResaleRequestTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testHashrateResaleRequestWithoutParameters() {
        String path = "/sapi/v1/mining/hash-transfer/config";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createMining().hashrateResaleRequest(parameters));
    }

    @Test
    public void testHashrateResaleRequestWithValidParameters() {
        String path = "/sapi/v1/mining/hash-transfer/config?userName=testUser&algo=sha256&endDate=1672531200000&startDate=1672444800000&toPoolUser=testPoolUser&hashRate=500000000000";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "testUser");
        parameters.put("algo", "sha256");
        parameters.put("endDate", 1672531200000L);
        parameters.put("startDate", 1672444800000L);
        parameters.put("toPoolUser", "testPoolUser");
        parameters.put("hashRate", 500000000000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createMining().hashrateResaleRequest(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
