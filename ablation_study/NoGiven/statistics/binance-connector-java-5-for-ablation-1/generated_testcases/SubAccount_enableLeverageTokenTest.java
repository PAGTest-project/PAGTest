
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

public class SubAccount_enableLeverageTokenTest {
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
    public void testEnableLeverageTokenSuccess() {
        String path = "/sapi/v1/sub-account/blvt/enable";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");
        parameters.put("enableBlvt", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String response = subAccount.enableLeverageToken(parameters);
        assertEquals(MockData.MOCK_RESPONSE, response);
    }

    @Test
    public void testEnableLeverageTokenMissingEmail() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("enableBlvt", true);

        assertThrows(BinanceConnectorException.class, () -> subAccount.enableLeverageToken(parameters));
    }

    @Test
    public void testEnableLeverageTokenMissingEnableBlvt() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "test@example.com");

        assertThrows(BinanceConnectorException.class, () -> subAccount.enableLeverageToken(parameters));
    }
}
