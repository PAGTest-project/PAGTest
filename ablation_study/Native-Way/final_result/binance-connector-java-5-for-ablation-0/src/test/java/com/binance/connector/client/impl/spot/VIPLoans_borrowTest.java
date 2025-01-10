
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.ProxyAuth;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class VIPLoans_borrowTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
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

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createVIPLoans().borrow(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testBorrowMissingLoanAccountId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("loanAmount", 1.0);
        parameters.put("collateralAccountId", "789012");
        parameters.put("collateralCoin", "ETH");
        parameters.put("isFlexibleRate", true);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createVIPLoans().borrow(parameters));
    }

    @Test
    public void testBorrowMissingLoanCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", 123456L);
        parameters.put("loanAmount", 1.0);
        parameters.put("collateralAccountId", "789012");
        parameters.put("collateralCoin", "ETH");
        parameters.put("isFlexibleRate", true);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createVIPLoans().borrow(parameters));
    }

    @Test
    public void testBorrowMissingLoanAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", 123456L);
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralAccountId", "789012");
        parameters.put("collateralCoin", "ETH");
        parameters.put("isFlexibleRate", true);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createVIPLoans().borrow(parameters));
    }

    @Test
    public void testBorrowMissingCollateralAccountId() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", 123456L);
        parameters.put("loanCoin", "BTC");
        parameters.put("loanAmount", 1.0);
        parameters.put("collateralCoin", "ETH");
        parameters.put("isFlexibleRate", true);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createVIPLoans().borrow(parameters));
    }

    @Test
    public void testBorrowMissingCollateralCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", 123456L);
        parameters.put("loanCoin", "BTC");
        parameters.put("loanAmount", 1.0);
        parameters.put("collateralAccountId", "789012");
        parameters.put("isFlexibleRate", true);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createVIPLoans().borrow(parameters));
    }

    @Test
    public void testBorrowMissingIsFlexibleRate() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanAccountId", 123456L);
        parameters.put("loanCoin", "BTC");
        parameters.put("loanAmount", 1.0);
        parameters.put("collateralAccountId", "789012");
        parameters.put("collateralCoin", "ETH");

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createVIPLoans().borrow(parameters));
    }
}
