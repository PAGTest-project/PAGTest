
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

public class SubAccount_getFuturesInternalTransferTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testGetFuturesInternalTransfer() {
        String path = String.format("/sapi/v1/sub-account/futures/internalTransfer?email=%s&futuresType=1",
                UrlBuilder.urlEncode("alice@test.com"));
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("futuresType", 1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = subAccount.getFuturesInternalTransfer(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testGetFuturesInternalTransferMissingEmail() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("futuresType", 1);

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.getFuturesInternalTransfer(parameters));
    }

    @Test
    public void testGetFuturesInternalTransferMissingFuturesType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.getFuturesInternalTransfer(parameters));
    }
}
