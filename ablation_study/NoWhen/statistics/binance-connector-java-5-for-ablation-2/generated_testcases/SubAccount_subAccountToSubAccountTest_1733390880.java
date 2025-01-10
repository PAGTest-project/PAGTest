
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.UrlBuilder;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class SubAccount_subAccountToSubAccountTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testSubAccountToSubAccountSuccess() {
        String path = "/sapi/v1/sub-account/transfer/subToSub?toEmail=alice@test.com&asset=BNB&amount=0.1";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "alice@test.com");
        parameters.put("asset", "BNB");
        parameters.put("amount", 0.1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = subAccount.subAccountToSubAccount(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSubAccountToSubAccountMissingToEmail() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "BNB");
        parameters.put("amount", 0.1);

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.subAccountToSubAccount(parameters));
    }

    @Test
    public void testSubAccountToSubAccountMissingAsset() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "alice@test.com");
        parameters.put("amount", 0.1);

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.subAccountToSubAccount(parameters));
    }

    @Test
    public void testSubAccountToSubAccountMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "alice@test.com");
        parameters.put("asset", "BNB");

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.subAccountToSubAccount(parameters));
    }
}
