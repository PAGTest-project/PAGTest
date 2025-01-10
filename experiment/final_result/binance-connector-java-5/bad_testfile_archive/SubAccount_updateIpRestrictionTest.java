
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

public class SubAccount_updateIpRestrictionTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private SubAccount subAccount;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.subAccount = new SubAccount(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
    }

    @Test
    public void testUpdateIpRestrictionSuccess() {
        String path = String.format("/sapi/v2/sub-account/subAccountApi/ipRestriction?email=%s&subAccountApiKey=%s&status=%s",
                "alice@test.com", "abc", "active");
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("subAccountApiKey", "abc");
        parameters.put("status", "active");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = subAccount.updateIpRestriction(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testUpdateIpRestrictionMissingParameters() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("subAccountApiKey", "abc");

        assertThrows(BinanceConnectorException.class, () -> subAccount.updateIpRestriction(parameters));
    }
}
