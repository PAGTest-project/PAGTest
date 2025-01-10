
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

public class Blvt_redeemTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Blvt blvt;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.blvt = new Blvt(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, ProxyAuth.NOPROXY);
    }

    @Test
    public void testRedeemSuccess() {
        String path = "/sapi/v1/blvt/redeem";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("tokenName", "BTCDOWN");
        parameters.put("amount", 100.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = blvt.redeem(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testRedeemWithoutTokenName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", 100.0);

        assertThrows(BinanceConnectorException.class, () -> blvt.redeem(parameters));
    }

    @Test
    public void testRedeemWithoutAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("tokenName", "BTCDOWN");

        assertThrows(BinanceConnectorException.class, () -> blvt.redeem(parameters));
    }
}
