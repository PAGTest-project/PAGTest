
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

public class SubAccount_futuresInternalTransferTest {
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
    public void testFuturesInternalTransferSuccess() {
        String path = "/sapi/v1/sub-account/futures/internalTransfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("fromEmail", "alice@test.com");
        parameters.put("toEmail", "bob@test.com");
        parameters.put("futuresType", 1);
        parameters.put("asset", "BTC");
        parameters.put("amount", 1.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = subAccount.futuresInternalTransfer(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFuturesInternalTransferMissingRequiredParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("fromEmail", "alice@test.com");
        parameters.put("toEmail", "bob@test.com");
        parameters.put("futuresType", 1);
        parameters.put("asset", "BTC");

        assertThrows(BinanceConnectorException.class, () -> subAccount.futuresInternalTransfer(parameters));
    }
}
