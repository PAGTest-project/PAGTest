
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

public class SubAccount_subAccountToSubAccountTest {

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
    public void testSubAccountToSubAccount() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("toEmail", "test@example.com");
        parameters.put("asset", "BTC");
        parameters.put("amount", "1.0");

        when(requestHandler.sendSignedRequest(baseUrl, subAccount.SUB_TO_SUB, parameters, HttpMethod.POST, showLimitUsage))
                .thenReturn("success");

        String result = subAccount.subAccountToSubAccount(parameters);
        assertEquals("success", result);

        verify(requestHandler).sendSignedRequest(baseUrl, subAccount.SUB_TO_SUB, parameters, HttpMethod.POST, showLimitUsage);
    }
}
