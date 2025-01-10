
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Convert_exchangeInfoTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Convert convert;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.convert = new Convert(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth(null, null));
    }

    @Test
    public void testExchangeInfoWithoutParameters() {
        String path = "/sapi/v1/convert/exchangeInfo";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = convert.exchangeInfo(parameters);
        String expectedResponse = "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}";
        assertEquals(expectedResponse, result);
    }
}
