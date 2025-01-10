
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class CryptoLoans_flexibleLoanRepayTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private CryptoLoans cryptoLoans;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.cryptoLoans = new CryptoLoans(baseUrl, "apiKey", "secretKey", false, null);
    }

    @Test
    public void testFlexibleLoanRepayWithoutParameters() {
        String path = "/sapi/v1/loan/flexible/repay";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> cryptoLoans.flexibleLoanRepay(parameters));
    }

    @Test
    public void testFlexibleLoanRepayWithValidParameters() {
        String path = "/sapi/v1/loan/flexible/repay";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralCoin", "ETH");
        parameters.put("repayAmount", 1.0);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = cryptoLoans.flexibleLoanRepay(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
