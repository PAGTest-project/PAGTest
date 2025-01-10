
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.RequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class NFT_getAssetTest {

    private NFT nft;

    @Mock
    private RequestHandler requestHandler;

    private final String baseUrl = "http://test.url";
    private final boolean showLimitUsage = true;

    @Before
    public void setUp() {
        nft = new NFT(baseUrl, "apiKey", "secretKey", showLimitUsage, null);
        nft.requestHandler = requestHandler;
    }

    @Test
    public void testGetAsset() {
        String expectedResponse = "{\"status\":\"success\"}";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("limit", 50);
        parameters.put("page", 1);

        when(requestHandler.sendSignedRequest(baseUrl, NFT.GET_ASSET, parameters, HttpMethod.GET, showLimitUsage))
            .thenReturn(expectedResponse);

        String result = nft.getAsset(parameters);
        assertEquals(expectedResponse, result);
    }
}
