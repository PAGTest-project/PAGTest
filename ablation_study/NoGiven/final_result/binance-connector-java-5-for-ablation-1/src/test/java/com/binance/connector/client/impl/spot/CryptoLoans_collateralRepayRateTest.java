
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

public class CryptoLoans_collateralRepayRateTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCollateralRepayRateWithoutParameters() {
        String path = "/sapi/v1/loan/repay/collateral/rate";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().collateralRepayRate(parameters));
    }

    @Test
    public void testCollateralRepayRateWithValidParameters() {
        String path = "/sapi/v1/loan/repay/collateral/rate?loanCoin=BUSD&collateralCoin=BNB&repayAmount=100.5";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BUSD");
        parameters.put("collateralCoin", "BNB");
        parameters.put("repayAmount", 100.5);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createCryptoLoans().collateralRepayRate(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testCollateralRepayRateWithMissingLoanCoin() {
        String path = "/sapi/v1/loan/repay/collateral/rate?collateralCoin=BNB&repayAmount=100.5";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("collateralCoin", "BNB");
        parameters.put("repayAmount", 100.5);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().collateralRepayRate(parameters));
    }

    @Test
    public void testCollateralRepayRateWithMissingCollateralCoin() {
        String path = "/sapi/v1/loan/repay/collateral/rate?loanCoin=BUSD&repayAmount=100.5";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BUSD");
        parameters.put("repayAmount", 100.5);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().collateralRepayRate(parameters));
    }

    @Test
    public void testCollateralRepayRateWithMissingRepayAmount() {
        String path = "/sapi/v1/loan/repay/collateral/rate?loanCoin=BUSD&collateralCoin=BNB";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BUSD");
        parameters.put("collateralCoin", "BNB");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().collateralRepayRate(parameters));
    }
}
