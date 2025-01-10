
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class GiftCard_createDualTokensCodeTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private GiftCard giftCard;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.giftCard = new GiftCard(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
    }

    @Test
    public void testCreateDualTokensCodeSuccess() {
        String path = "/sapi/v1/giftcard/buyCode";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("baseToken", "BUSD");
        parameters.put("faceToken", "BNB");
        parameters.put("baseTokenAmount", 1.002);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = giftCard.createDualTokensCode(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testCreateDualTokensCodeMissingBaseToken() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("faceToken", "BNB");
        parameters.put("baseTokenAmount", 1.002);

        assertThrows(BinanceConnectorException.class, () -> giftCard.createDualTokensCode(parameters));
    }

    @Test
    public void testCreateDualTokensCodeMissingFaceToken() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("baseToken", "BUSD");
        parameters.put("baseTokenAmount", 1.002);

        assertThrows(BinanceConnectorException.class, () -> giftCard.createDualTokensCode(parameters));
    }

    @Test
    public void testCreateDualTokensCodeMissingBaseTokenAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("baseToken", "BUSD");
        parameters.put("faceToken", "BNB");

        assertThrows(BinanceConnectorException.class, () -> giftCard.createDualTokensCode(parameters));
    }
}
