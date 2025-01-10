
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

public class Wallet_busdConvertTest {
    private Wallet wallet;
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void setUp() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        RequestHandler requestHandler = new RequestHandler(MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY));
        this.wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
    }

    @Test
    public void testBusdConvertSuccess() {
        String path = "/sapi/v1/asset/convert-transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", "123456");
        parameters.put("asset", "BUSD");
        parameters.put("amount", "100");
        parameters.put("targetAsset", "USDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.busdConvert(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testBusdConvertMissingRequiredParameter() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", "123456");
        parameters.put("asset", "BUSD");
        parameters.put("targetAsset", "USDT");

        assertThrows(BinanceConnectorException.class, () -> wallet.busdConvert(parameters));
    }
}
