
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
import com.binance.connector.client.utils.ProxyAuth;

public class SubAccount_universalTransferTest {
    private SubAccount subAccount;
    private String baseUrl;
    private RequestHandler requestHandler;
    private boolean showLimitUsage;

    @Before
    public void setUp() {
        baseUrl = "https://api.binance.com";
        String apiKey = "testApiKey";
        String secretKey = "testSecretKey";
        showLimitUsage = true;
        ProxyAuth proxy = null;
        requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
        subAccount = new SubAccount(baseUrl, apiKey, secretKey, showLimitUsage, proxy);
    }

    @Test
    public void testUniversalTransferSuccess() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fromAccountType", "SPOT");
        parameters.put("toAccountType", "MARGIN");
        parameters.put("asset", "BTC");
        parameters.put("amount", "1.0");

        String result = subAccount.universalTransfer(parameters);
        assertNotNull(result);
    }

    @Test
    public void testUniversalTransferMissingRequiredParameter() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fromAccountType", "SPOT");
        parameters.put("toAccountType", "MARGIN");
        parameters.put("asset", "BTC");

        String result = subAccount.universalTransfer(parameters);
        assertEquals("Parameter validation failed", result);
    }

    @Test
    public void testUniversalTransferInvalidParameterType() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fromAccountType", 123); // Invalid type
        parameters.put("toAccountType", "MARGIN");
        parameters.put("asset", "BTC");
        parameters.put("amount", "1.0");

        String result = subAccount.universalTransfer(parameters);
        assertEquals("Parameter validation failed", result);
    }
}
