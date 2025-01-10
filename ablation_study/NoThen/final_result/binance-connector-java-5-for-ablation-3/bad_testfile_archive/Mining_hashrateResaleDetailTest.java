
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

@RunWith(MockitoJUnitRunner.class)
public class Mining_hashrateResaleDetailTest {
    private Mining mining;
    private String baseUrl;
    private boolean showLimitUsage;

    @Mock
    private RequestHandler requestHandler;

    @Before
    public void setUp() {
        baseUrl = "https://api.binance.com";
        String apiKey = "testApiKey";
        String secretKey = "testSecretKey";
        showLimitUsage = true;
        ProxyAuth proxy = null;
        mining = new Mining(baseUrl, apiKey, secretKey, showLimitUsage, proxy);
        mining.requestHandler = requestHandler; // Change to public or package-private
    }

    @Test
    public void testHashrateResaleDetailSuccess() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 12345);
        parameters.put("userName", "testUser");

        when(requestHandler.sendSignedRequest(anyString(), anyString(), anyMap(), any(HttpMethod.class), anyBoolean())).thenReturn("{\"success\":true}");

        String result = mining.hashrateResaleDetail(parameters);
        assertEquals("{\"success\":true}", result);
    }

    @Test
    public void testHashrateResaleDetailMissingConfigId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "testUser");

        assertThrows(BinanceConnectorException.class, () -> mining.hashrateResaleDetail(parameters));
    }

    @Test
    public void testHashrateResaleDetailMissingUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 12345);

        assertThrows(BinanceConnectorException.class, () -> mining.hashrateResaleDetail(parameters));
    }

    @Test
    public void testHashrateResaleDetailInvalidConfigId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", "invalidConfigId");
        parameters.put("userName", "testUser");

        assertThrows(BinanceConnectorException.class, () -> mining.hashrateResaleDetail(parameters));
    }

    @Test
    public void testHashrateResaleDetailInvalidUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", 12345);
        parameters.put("userName", 12345);

        assertThrows(BinanceConnectorException.class, () -> mining.hashrateResaleDetail(parameters));
    }
}
