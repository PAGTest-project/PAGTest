
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class UserData_createMarginListenKeyTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private UserData userData;

    @Before
    public void setUp() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.userData = new UserData(baseUrl, MockData.API_KEY, true, new ProxyAuth(null, null));
    }

    @Test
    public void testCreateMarginListenKey() {
        String path = "/sapi/v1/userDataStream";

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = userData.createMarginListenKey();
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
