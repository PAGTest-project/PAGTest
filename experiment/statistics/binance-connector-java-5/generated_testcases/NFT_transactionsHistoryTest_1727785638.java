
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
public class NFT_transactionsHistoryTest {

    private NFT nft;
    private String baseUrl = "http://test.url";
    private boolean showLimitUsage = true;

    @Mock
    private RequestHandler requestHandler;

    @Before
    public void setUp() {
        nft = new NFT(baseUrl, "apiKey", "secretKey", showLimitUsage, null);
        nft.requestHandler = requestHandler;
    }

    @Test
    public void testTransactionsHistorySuccess() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderType", 0);

        when(requestHandler.sendSignedRequest(baseUrl, "/sapi/v1/nft/history/transactions", parameters, HttpMethod.GET, showLimitUsage))
            .thenReturn("success");

        String result = nft.transactionsHistory(parameters);
        assertEquals("success", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransactionsHistoryMissingOrderType() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        nft.transactionsHistory(parameters);
    }
}
