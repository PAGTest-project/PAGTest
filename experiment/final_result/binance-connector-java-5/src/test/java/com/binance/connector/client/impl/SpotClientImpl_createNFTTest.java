
package com.binance.connector.client.impl;

import com.binance.connector.client.enums.DefaultUrls;
import com.binance.connector.client.impl.spot.NFT;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpotClientImpl_createNFTTest {
    private SpotClientImpl spotClient;
    private String baseUrl;
    private String apiKey;
    private SignatureGenerator signatureGenerator;
    private boolean showLimitUsage;
    private ProxyAuth proxy;

    @Before
    public void setUp() {
        baseUrl = DefaultUrls.PROD_URL;
        apiKey = "testApiKey";
        signatureGenerator = new HmacSignatureGenerator("testSecretKey");
        showLimitUsage = true;
        proxy = null;
        spotClient = new SpotClientImpl(apiKey, signatureGenerator, baseUrl);
    }

    @Test
    public void testCreateNFT() {
        NFT nft = spotClient.createNFT();
        assertNotNull(nft);
    }
}
