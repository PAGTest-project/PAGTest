
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

public class Mining_accountListTest {
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
    public void testAccountList() {
        String path = "/sapi/v1/mining/statistics/user/list?algo=sha256&userName=test";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");
        parameters.put("userName", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.accountList(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testAccountListMissingAlgo() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "test");

        assertThrows(BinanceConnectorException.class, () -> {
            mining.accountList(parameters);
        });
    }

    @Test
    public void testAccountListMissingUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");

        assertThrows(BinanceConnectorException.class, () -> {
            mining.accountList(parameters);
        });
    }
}
