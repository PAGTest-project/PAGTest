
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
    public void testLoanBorrowWithoutParameters() {
        String path = "/sapi/v1/loan/borrow";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }

    @Test
    public void testLoanBorrowWithValidParameters() {
        String path = "/sapi/v1/loan/borrow?loanCoin=BTC&collateralCoin=ETH&loanTerm=30";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralCoin", "ETH");
        parameters.put("loanTerm", 30);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createCryptoLoans().loanBorrow(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testLoanBorrowWithMissingLoanCoin() {
        String path = "/sapi/v1/loan/borrow?collateralCoin=ETH&loanTerm=30";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("collateralCoin", "ETH");
        parameters.put("loanTerm", 30);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }

    @Test
    public void testLoanBorrowWithMissingCollateralCoin() {
        String path = "/sapi/v1/loan/borrow?loanCoin=BTC&loanTerm=30";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("loanTerm", 30);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }

    @Test
    public void testLoanBorrowWithMissingLoanTerm() {
        String path = "/sapi/v1/loan/borrow?loanCoin=BTC&collateralCoin=ETH";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralCoin", "ETH");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }
}
