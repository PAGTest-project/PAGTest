
package com.binance.connector.client.impl.spot;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SubAccount_subAccountToMasterTest {

    private SubAccount subAccount;
    private RequestHandler requestHandler;
    private final String baseUrl = "https://api.binance.com";
    private final boolean showLimitUsage = true;

    @Before
    public void setUp() {
        requestHandler = mock(RequestHandler.class);
        subAccount = new SubAccount(baseUrl, "apiKey", "secretKey", showLimitUsage, null);
        subAccount.requestHandler = requestHandler;
    }

    @Test
    public void testSubAccountToMaster() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("asset", "BTC");
        parameters.put("amount", "1.0");

        String expectedResponse = "success";
        when(requestHandler.sendSignedRequest(baseUrl, SubAccount.SUB_TO_MASTER, parameters, HttpMethod.POST, showLimitUsage))
                .thenReturn(expectedResponse);

        String actualResponse = subAccount.subAccountToMaster(parameters);
        assertEquals(expectedResponse, actualResponse);

        verify(requestHandler, times(1)).sendSignedRequest(baseUrl, SubAccount.SUB_TO_MASTER, parameters, HttpMethod.POST, showLimitUsage);
    }
}
