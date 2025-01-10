
package com.binance.connector.client.impl;

import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpotClientImpl_createSubAccountTest {
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
    public void testCreateSubAccount() {
        SubAccount subAccount = spotClient.createSubAccount();
        assertNotNull(subAccount);
        assertEquals(baseUrl, subAccount.getBaseUrl());
        assertEquals(apiKey, subAccount.getApiKey());
        assertEquals(signatureGenerator, subAccount.getSignatureGenerator());
        assertEquals(showLimitUsage, subAccount.isShowLimitUsage());
        assertEquals(proxy, subAccount.getProxy());
    }
}
