
package com.binance.connector.client.impl;

import com.binance.connector.client.impl.spot.UserData;
import com.binance.connector.client.utils.ProxyAuth;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpotClientImpl_createUserDataTest {
    private SpotClientImpl spotClient;
    private String baseUrl;
    private String apiKey;
    private boolean showLimitUsage;
    private ProxyAuth proxy;

    @Before
    public void setUp() {
        baseUrl = "https://api.binance.com";
        apiKey = "testApiKey";
        showLimitUsage = true;
        proxy = null;
        spotClient = new SpotClientImpl(apiKey, (SignatureGenerator) null, baseUrl);
        spotClient.setShowLimitUsage(showLimitUsage);
        spotClient.setProxy(proxy);
    }

    @Test
    public void testCreateUserData() {
        UserData userData = spotClient.createUserData();
        assertNotNull(userData);
        assertEquals(baseUrl, userData.getBaseUrl());
        assertEquals(apiKey, userData.getApiKey());
        assertEquals(showLimitUsage, userData.isShowLimitUsage());
        assertEquals(proxy, userData.getProxy());
    }
}
