
package com.binance.connector.client.impl;

import com.binance.connector.client.impl.spot.Market;
import com.binance.connector.client.utils.ProxyAuth;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpotClientImpl_createMarketTest {
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
        spotClient = new SpotClientImpl(apiKey, "", baseUrl, showLimitUsage);
    }

    @Test
    public void testCreateMarket() {
        Market market = spotClient.createMarket();
        assertNotNull(market);
        assertEquals(baseUrl, market.getBaseUrl());
        assertEquals(apiKey, market.getApiKey());
        assertEquals(showLimitUsage, market.isShowLimitUsage());
        assertEquals(proxy, market.getProxy());
    }
}
