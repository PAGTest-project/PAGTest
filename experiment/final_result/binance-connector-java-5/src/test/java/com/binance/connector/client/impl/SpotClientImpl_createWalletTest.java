
package com.binance.connector.client.impl;

import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import com.binance.connector.client.impl.spot.Wallet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class SpotClientImpl_createWalletTest {
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
    }

    @Test
    public void testCreateWallet() {
        Wallet wallet = spotClient.createWallet();
        assertNotNull(wallet);
    }
}
