
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;

public class SimpleEarn_flexibleRewardsHistoryTest {

    private SimpleEarn simpleEarn;
    private RequestHandler requestHandler;
    private String baseUrl = "http://test.url";
    private String apiKey = "testApiKey";
    private String secretKey = "testSecretKey";
    private boolean showLimitUsage = false;

    @Before
    public void setUp() {
        requestHandler = Mockito.mock(RequestHandler.class);
        simpleEarn = new SimpleEarn(baseUrl, apiKey, new HmacSignatureGenerator(secretKey), showLimitUsage, null);
        simpleEarn.requestHandler = requestHandler;
    }

    @Test
    public void testFlexibleRewardsHistorySuccess() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "BONUS");

        Mockito.when(requestHandler.sendSignedRequest(baseUrl, SimpleEarn.FLEXIBLE_REWARDS_HISTORY, parameters, HttpMethod.GET, showLimitUsage))
               .thenReturn("Success");

        String result = simpleEarn.flexibleRewardsHistory(parameters);
        assertEquals("Success", result);
    }

    @Test
    public void testFlexibleRewardsHistoryMissingType() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        assertThrows(ParameterChecker.ParameterCheckException.class, () -> {
            simpleEarn.flexibleRewardsHistory(parameters);
        });
    }
}
