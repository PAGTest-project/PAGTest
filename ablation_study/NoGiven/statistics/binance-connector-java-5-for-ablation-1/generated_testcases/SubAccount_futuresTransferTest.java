
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

public class SubAccount_futuresTransferTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private SubAccount subAccount;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.subAccount = new SubAccount(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testFuturesTransferSuccess() {
        String path = "/sapi/v1/sub-account/futures/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("asset", "BTC");
        parameters.put("amount", 1.0);
        parameters.put("type", 1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = subAccount.futuresTransfer(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testFuturesTransferMissingEmail() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "BTC");
        parameters.put("amount", 1.0);
        parameters.put("type", 1);

        assertThrows(BinanceConnectorException.class, () -> subAccount.futuresTransfer(parameters));
    }

    @Test
    public void testFuturesTransferMissingAsset() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("amount", 1.0);
        parameters.put("type", 1);

        assertThrows(BinanceConnectorException.class, () -> subAccount.futuresTransfer(parameters));
    }

    @Test
    public void testFuturesTransferMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("asset", "BTC");
        parameters.put("type", 1);

        assertThrows(BinanceConnectorException.class, () -> subAccount.futuresTransfer(parameters));
    }

    @Test
    public void testFuturesTransferMissingType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");
        parameters.put("asset", "BTC");
        parameters.put("amount", 1.0);

        assertThrows(BinanceConnectorException.class, () -> subAccount.futuresTransfer(parameters));
    }
}
