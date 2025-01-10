
package com.binance.connector.client.impl;

import com.binance.connector.client.impl.spot.GiftCard;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpotClientImpl_createGiftCardTest {
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
    public void testCreateGiftCard() {
        GiftCard giftCard = spotClient.createGiftCard();
        assertNotNull(giftCard);
        assertEquals(baseUrl, giftCard.getBaseUrl());
        assertEquals(apiKey, giftCard.getApiKey());
        assertEquals(signatureGenerator, giftCard.getSignatureGenerator());
        assertEquals(showLimitUsage, giftCard.isShowLimitUsage());
        assertEquals(proxy, giftCard.getProxy());
    }
}
