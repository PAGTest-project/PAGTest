
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
import com.binance.connector.client.exceptions.BinanceConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class GiftCard_createCodeTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private GiftCard giftCard;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.giftCard = new GiftCard(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testCreateCodeSuccess() {
        String path = "/sapi/v1/giftcard/createCode?token=BTC&amount=1.0";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("token", "BTC");
        parameters.put("amount", 1.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = giftCard.createCode(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testCreateCodeMissingToken() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 1.0);

        Exception exception = assertThrows(BinanceConnectorException.class, () -> {
            giftCard.createCode(parameters);
        });

        assertEquals("Parameter 'token' is required.", exception.getMessage());
    }

    @Test
    public void testCreateCodeMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("token", "BTC");

        Exception exception = assertThrows(BinanceConnectorException.class, () -> {
            giftCard.createCode(parameters);
        });

        assertEquals("Parameter 'amount' is required.", exception.getMessage());
    }
}
