
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

public class AutoInvest_userIndexPlansTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testUserIndexPlans() {
        String path = "/sapi/v1/lending/auto-invest/index/user-summary?indexId=1234";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("indexId", 1234L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        AutoInvest autoInvest = new AutoInvest(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        String result = autoInvest.userIndexPlans(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testUserIndexPlansWithoutIndexId() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        AutoInvest autoInvest = new AutoInvest(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
        assertThrows(BinanceConnectorException.class, () -> autoInvest.userIndexPlans(parameters));
    }
}
