
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

public class SubAccount_universalTransferTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testUniversalTransferSuccess() {
        String path = String.format("/sapi/v1/sub-account/universalTransfer?fromAccountType=SPOT&toAccountType=SPOT&asset=BNB&amount=0.1");
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("fromAccountType", "SPOT");
        parameters.put("toAccountType", "SPOT");
        parameters.put("asset", "BNB");
        parameters.put("amount", 0.1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = subAccount.universalTransfer(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testUniversalTransferMissingRequiredParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("fromAccountType", "SPOT");
        parameters.put("toAccountType", "SPOT");
        parameters.put("asset", "BNB");

        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> subAccount.universalTransfer(parameters));
    }
}
