
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.MockData;
import com.binance.connector.client.utils.MockWebServerDispatcher;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

public class SubAccount_spotAccountSummaryTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testSpotAccountSummary() {
        String path = "/sapi/v1/sub-account/spotSummary";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClientImpl client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        SubAccount subAccount = new SubAccount(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, true, null);
        String result = subAccount.spotAccountSummary(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
