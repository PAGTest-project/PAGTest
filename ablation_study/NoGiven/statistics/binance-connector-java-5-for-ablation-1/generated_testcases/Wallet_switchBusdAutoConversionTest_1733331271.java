
package com.binance.connector.client.impl.spot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_switchBusdAutoConversionTest {
    private Wallet wallet;
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
        baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        wallet = new Wallet(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testSwitchBusdAutoConversionSuccess() {
        String path = "/sapi/v1/capital/contract/convertible-coins";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("coin", "BUSD");
        parameters.put("enable", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.switchBusdAutoConversion(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSwitchBusdAutoConversionMissingCoin() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("enable", true);

        wallet.switchBusdAutoConversion(parameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSwitchBusdAutoConversionMissingEnable() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("coin", "BUSD");

        wallet.switchBusdAutoConversion(parameters);
    }
}
