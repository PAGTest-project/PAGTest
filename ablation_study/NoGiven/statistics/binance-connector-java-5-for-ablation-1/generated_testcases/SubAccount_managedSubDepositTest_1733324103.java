
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

public class SubAccount_managedSubDepositTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testManagedSubDepositSuccess() {
        String path = String.format("/sapi/v1/managed-subaccount/deposit?toEmail=%s&asset=%s&amount=%s",
                UrlBuilder.urlEncode("alice@test.com"), UrlBuilder.urlEncode("BTC"), UrlBuilder.urlEncode("1.0"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "alice@test.com");
        parameters.put("asset", "BTC");
        parameters.put("amount", "1.0");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = subAccount.managedSubDeposit(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testManagedSubDepositMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "alice@test.com");
        parameters.put("asset", "BTC");

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.managedSubDeposit(parameters));
    }

    @Test
    public void testManagedSubDepositInvalidEmail() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", 12345); // Invalid email type
        parameters.put("asset", "BTC");
        parameters.put("amount", "1.0");

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.managedSubDeposit(parameters));
    }

    @Test
    public void testManagedSubDepositInvalidAsset() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "alice@test.com");
        parameters.put("asset", 12345); // Invalid asset type
        parameters.put("amount", "1.0");

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.managedSubDeposit(parameters));
    }
}
