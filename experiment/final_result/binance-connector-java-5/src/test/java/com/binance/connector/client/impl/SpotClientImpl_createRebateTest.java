
package com.binance.connector.client.impl;

import com.binance.connector.client.impl.spot.Rebate;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class SpotClientImpl_createRebateTest {

    private SpotClientImpl spotClient;
    private String baseUrl;
    private String apiKey;
    private SignatureGenerator signatureGenerator;
    private boolean showLimitUsage;
    private ProxyAuth proxy;

    @Before
    public void setUp() {
        baseUrl = "https://api.binance.com";
        apiKey = "testApiKey";
        signatureGenerator = new HmacSignatureGenerator("testSecretKey");
        showLimitUsage = true;
        proxy = null;
        spotClient = new SpotClientImpl(apiKey, signatureGenerator, baseUrl);
        spotClient.setShowLimitUsage(showLimitUsage);
        spotClient.setProxy(proxy);
    }

    @Test
    public void testCreateRebate() {
        Rebate rebate = spotClient.createRebate();
        assertNotNull(rebate);
    }
}
