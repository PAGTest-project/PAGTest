
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.ParameterChecker;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_busdConvertTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testBusdConvertSuccess() {
        String path = "/sapi/v1/asset/convert-transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", "123456");
        parameters.put("asset", "BUSD");
        parameters.put("amount", "100");
        parameters.put("targetAsset", "USDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        Wallet wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = wallet.busdConvert(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testBusdConvertMissingMandatoryParam() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", "123456");
        parameters.put("asset", "BUSD");
        parameters.put("targetAsset", "USDT");

        Wallet wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> wallet.busdConvert(parameters));
    }

    @Test
    public void testBusdConvertInvalidParamType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", 123456); // Invalid type, should be String
        parameters.put("asset", "BUSD");
        parameters.put("amount", "100");
        parameters.put("targetAsset", "USDT");

        Wallet wallet = new Wallet(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        assertThrows(BinanceConnectorException.class, () -> wallet.busdConvert(parameters));
    }
}
