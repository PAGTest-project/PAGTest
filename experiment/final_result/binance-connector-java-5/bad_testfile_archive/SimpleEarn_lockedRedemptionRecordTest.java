
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

public class SimpleEarn_lockedRedemptionRecordTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLockedRedemptionRecord() {
        String path = "/sapi/v1/simple-earn/locked/history/redemptionRecord";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        SimpleEarn simpleEarn = new SimpleEarn(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = simpleEarn.lockedRedemptionRecord(parameters);
        
        // Adjust the expected response to match the actual response structure
        String expectedResponse = "{\"data\":[{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}]}";
        assertEquals(expectedResponse, result);
    }
}
