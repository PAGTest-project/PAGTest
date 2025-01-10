
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Futures_futuresTransferTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Futures futures;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.futures = new Futures(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
    }

    @Test
    public void testFuturesTransferSuccess() {
        String path = "/sapi/v1/futures/transfer";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");
        parameters.put("amount", 100.0);
        parameters.put("type", 1);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String response = futures.futuresTransfer(parameters);
        assertEquals(MockData.MOCK_RESPONSE, response);
    }

    @Test
    public void testFuturesTransferMissingAsset() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 100.0);
        parameters.put("type", 1);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresTransfer(parameters));
    }

    @Test
    public void testFuturesTransferMissingAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");
        parameters.put("type", 1);

        assertThrows(BinanceConnectorException.class, () -> futures.futuresTransfer(parameters));
    }

    @Test
    public void testFuturesTransferInvalidType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");
        parameters.put("amount", 100.0);
        parameters.put("type", "invalid");

        assertThrows(BinanceConnectorException.class, () -> futures.futuresTransfer(parameters));
    }
}
