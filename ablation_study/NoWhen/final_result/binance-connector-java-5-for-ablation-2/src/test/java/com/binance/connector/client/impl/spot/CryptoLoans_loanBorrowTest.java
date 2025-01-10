
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
import com.binance.connector.client.utils.ParameterChecker;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class CryptoLoans_loanBorrowTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLoanBorrow() {
        String path = "/sapi/v1/loan/borrow?loanCoin=USDT&collateralCoin=BTC&loanTerm=30";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BTC");
        parameters.put("loanTerm", 30);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createCryptoLoans().loanBorrow(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testLoanBorrowMissingLoanCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("collateralCoin", "BTC");
        parameters.put("loanTerm", 30);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }

    @Test
    public void testLoanBorrowMissingCollateralCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("loanTerm", 30);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }

    @Test
    public void testLoanBorrowMissingLoanTerm() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BTC");

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }
}
