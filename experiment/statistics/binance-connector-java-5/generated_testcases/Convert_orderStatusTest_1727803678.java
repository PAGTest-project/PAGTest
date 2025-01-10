
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
import com.binance.connector.client.utils.RequestHandler;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Convert_orderStatusTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Convert convert;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.convert = new Convert(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
    }

    @Test
    public void testOrderStatusWithoutParameters() {
        String path = "/sapi/v1/convert/orderStatus";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = convert.orderStatus(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testOrderStatusWithParameters() {
        String path = "/sapi/v1/convert/orderStatus";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("quoteId", "12415572564");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = convert.orderStatus(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testOrderStatusWithInvalidParameters() {
        String path = "/sapi/v1/convert/orderStatus";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("invalidParam", "invalidValue");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> {
            try {
                convert.orderStatus(parameters);
            } catch (BinanceConnectorException e) {
                throw e;
            }
        });
    }
}
