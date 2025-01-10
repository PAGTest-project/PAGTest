
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.SpotClientImpl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Margin_smallLiabilityExchangeHistoryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testSmallLiabilityExchangeHistory() {
        String path = "/sapi/v1/margin/exchange-small-liability-history?current=1&size=10";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("current", "1");
        parameters.put("size", "10");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createMargin().smallLiabilityExchangeHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testSmallLiabilityExchangeHistoryMissingCurrent() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("size", "10");

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        client.createMargin().smallLiabilityExchangeHistory(parameters);
    }

    @Test(expected = com.binance.connector.client.exceptions.BinanceConnectorException.class)
    public void testSmallLiabilityExchangeHistoryMissingSize() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("current", "1");

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        client.createMargin().smallLiabilityExchangeHistory(parameters);
    }
}
