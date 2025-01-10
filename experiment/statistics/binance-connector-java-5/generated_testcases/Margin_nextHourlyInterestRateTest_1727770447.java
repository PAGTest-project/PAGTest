
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
public class Margin_nextHourlyInterestRateTest {

    private Margin margin;

    @Mock
    private RequestHandler requestHandler;

    private final String baseUrl = "http://test.url";
    private final boolean showLimitUsage = true;

    @Before
    public void setUp() {
        margin = new Margin(baseUrl, "apiKey", "secretKey", showLimitUsage, null);
        margin.requestHandler = requestHandler;
    }

    @Test
    public void testNextHourlyInterestRate() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("assets", "BTC");
        parameters.put("isIsolated", true);

        when(requestHandler.sendSignedRequest(baseUrl, Margin.NEXT_HOURLY_INTEREST_RATE, parameters, HttpMethod.GET, showLimitUsage))
            .thenReturn("success");

        String result = margin.nextHourlyInterestRate(parameters);
        assertEquals("success", result);
    }
}
