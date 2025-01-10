
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
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Wallet_switchBusdAutoConversionTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Wallet wallet;
    private RequestHandler requestHandler;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.requestHandler = new RequestHandler(MockData.API_KEY, null, null);
        this.wallet = new Wallet(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, null);
    }

    @Test
    public void testSwitchBusdAutoConversionSuccess() {
        String path = "/sapi/v1/capital/contract/convertible-coins";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BUSD");
        parameters.put("enable", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = wallet.switchBusdAutoConversion(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testSwitchBusdAutoConversionMissingCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("enable", true);

        assertThrows(BinanceConnectorException.class, () -> wallet.switchBusdAutoConversion(parameters));
    }

    @Test
    public void testSwitchBusdAutoConversionMissingEnable() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BUSD");

        assertThrows(BinanceConnectorException.class, () -> wallet.switchBusdAutoConversion(parameters));
    }
}
