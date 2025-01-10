
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

public class VIPLoans_borrowTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private VIPLoans vipLoans;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.vipLoans = new VIPLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, new ProxyAuth());
    }

    @Test
    public void testBorrowSuccess() {
        String path = "/sapi/v1/loan/vip/borrow";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", 123456L);
        parameters.put("loanCoin", "BTC");
        parameters.put("loanAmount", 1.0);
        parameters.put("collateralAccountId", "789012");
        parameters.put("collateralCoin", "ETH");
        parameters.put("isFlexibleRate", true);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = vipLoans.borrow(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testBorrowMissingLoanAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", 123456L);
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralAccountId", "789012");
        parameters.put("collateralCoin", "ETH");
        parameters.put("isFlexibleRate", true);

        assertThrows(BinanceConnectorException.class, () -> {
            vipLoans.borrow(parameters);
        });
    }

    @Test
    public void testBorrowInvalidLoanAccountId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", "invalid");
        parameters.put("loanCoin", "BTC");
        parameters.put("loanAmount", 1.0);
        parameters.put("collateralAccountId", "789012");
        parameters.put("collateralCoin", "ETH");
        parameters.put("isFlexibleRate", true);

        assertThrows(BinanceConnectorException.class, () -> {
            vipLoans.borrow(parameters);
        });
    }
}
