
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

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Margin_nextHourlyInterestRateTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Margin margin;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.margin = new Margin(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
    }

    @Test
    public void testNextHourlyInterestRateWithoutParameters() {
        String path = "/sapi/v1/margin/next-hourly-interest-rate";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> margin.nextHourlyInterestRate(parameters));
    }

    @Test
    public void testNextHourlyInterestRateWithValidParameters() {
        String path = "/sapi/v1/margin/next-hourly-interest-rate?assets=BTC&isIsolated=false";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("assets", "BTC");
        parameters.put("isIsolated", false);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = margin.nextHourlyInterestRate(parameters);
        assertEquals("{\"data\":\"{\\\"key_1\\\": \\\"value_1\\\", \\\"key_2\\\": \\\"value_2\\\"}\"}", result);
    }

    @Test
    public void testNextHourlyInterestRateWithInvalidParameters() {
        String path = "/sapi/v1/margin/next-hourly-interest-rate?assets=INVALID&isIsolated=false";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("assets", "INVALID");
        parameters.put("isIsolated", false);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> margin.nextHourlyInterestRate(parameters));
    }
}
