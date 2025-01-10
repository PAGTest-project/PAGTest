
package com.binance.connector.client.impl.spot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedHashMap;
import java.util.Map;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class AutoInvest_submitOneTimeTransactionTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private AutoInvest autoInvest;

    @Before
    public void setUp() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.autoInvest = new AutoInvest(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
    }

    @Test
    public void testSubmitOneTimeTransactionSuccess() {
        String path = "/sapi/v1/lending/auto-invest/one-off";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("sourceType", "MAIN_SITE");
        parameters.put("subscriptionAmount", 100.0);
        parameters.put("sourceAsset", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = autoInvest.submitOneTimeTransaction(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testSubmitOneTimeTransactionMissingRequiredParameter() {
        String path = "/sapi/v1/lending/auto-invest/one-off";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("sourceType", "MAIN_SITE");
        parameters.put("sourceAsset", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> autoInvest.submitOneTimeTransaction(parameters));
    }

    @Test
    public void testSubmitOneTimeTransactionInvalidParameterType() {
        String path = "/sapi/v1/lending/auto-invest/one-off";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("sourceType", 123); // Invalid type
        parameters.put("subscriptionAmount", 100.0);
        parameters.put("sourceAsset", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> autoInvest.submitOneTimeTransaction(parameters));
    }
}
