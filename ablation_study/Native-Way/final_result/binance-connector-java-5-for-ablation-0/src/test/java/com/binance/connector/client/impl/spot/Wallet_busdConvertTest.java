
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
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

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
        parameters.put("clientTranId", "123456789");
        parameters.put("asset", "BUSD");
        parameters.put("amount", "100.0");
        parameters.put("targetAsset", "USDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createWallet().busdConvert(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testBusdConvertWithoutClientTranId() {
        String path = "/sapi/v1/asset/convert-transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "BUSD");
        parameters.put("amount", "100.0");
        parameters.put("targetAsset", "USDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createWallet().busdConvert(parameters));
    }

    @Test
    public void testBusdConvertWithoutAsset() {
        String path = "/sapi/v1/asset/convert-transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", "123456789");
        parameters.put("amount", "100.0");
        parameters.put("targetAsset", "USDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createWallet().busdConvert(parameters));
    }

    @Test
    public void testBusdConvertWithoutAmount() {
        String path = "/sapi/v1/asset/convert-transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", "123456789");
        parameters.put("asset", "BUSD");
        parameters.put("targetAsset", "USDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createWallet().busdConvert(parameters));
    }

    @Test
    public void testBusdConvertWithoutTargetAsset() {
        String path = "/sapi/v1/asset/convert-transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("clientTranId", "123456789");
        parameters.put("asset", "BUSD");
        parameters.put("amount", "100.0");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createWallet().busdConvert(parameters));
    }
}
