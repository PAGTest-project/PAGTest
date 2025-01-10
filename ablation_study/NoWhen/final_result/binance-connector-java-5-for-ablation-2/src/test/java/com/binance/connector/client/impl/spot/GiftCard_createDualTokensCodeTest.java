
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
import com.binance.connector.client.utils.ProxyAuth;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class GiftCard_createDualTokensCodeTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCreateDualTokensCode() {
        String path = "/sapi/v1/giftcard/buyCode?baseToken=BUSD&faceToken=BNB&baseTokenAmount=1.002";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("baseToken", "BUSD");
        parameters.put("faceToken", "BNB");
        parameters.put("baseTokenAmount", 1.002);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createGiftCard().createDualTokensCode(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testCreateDualTokensCodeWithoutBaseToken() {
        String path = "/sapi/v1/giftcard/buyCode?faceToken=BNB&baseTokenAmount=1.002";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("faceToken", "BNB");
        parameters.put("baseTokenAmount", 1.002);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createGiftCard().createDualTokensCode(parameters));
    }

    @Test
    public void testCreateDualTokensCodeWithoutFaceToken() {
        String path = "/sapi/v1/giftcard/buyCode?baseToken=BUSD&baseTokenAmount=1.002";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("baseToken", "BUSD");
        parameters.put("baseTokenAmount", 1.002);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createGiftCard().createDualTokensCode(parameters));
    }

    @Test
    public void testCreateDualTokensCodeWithoutBaseTokenAmount() {
        String path = "/sapi/v1/giftcard/buyCode?baseToken=BUSD&faceToken=BNB";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("baseToken", "BUSD");
        parameters.put("faceToken", "BNB");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createGiftCard().createDualTokensCode(parameters));
    }
}
