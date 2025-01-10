
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

public class Convert_tradeFlowTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Convert convert;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.convert = new Convert(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testTradeFlowSuccess() {
        String path = "/sapi/v1/convert/tradeFlow?startTime=1620000000000&endTime=1620003600000";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1620000000000L);
        parameters.put("endTime", 1620003600000L);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = convert.tradeFlow(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testTradeFlowMissingStartTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("endTime", 1620003600000L);

        assertThrows(BinanceConnectorException.class, () -> {
            convert.tradeFlow(parameters);
        });
    }

    @Test
    public void testTradeFlowMissingEndTime() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("startTime", 1620000000000L);

        assertThrows(BinanceConnectorException.class, () -> {
            convert.tradeFlow(parameters);
        });
    }
}
