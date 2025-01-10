
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
    public void testCollateralRepayRate() {
        String path = "/sapi/v1/loan/repay/collateral/rate?loanCoin=BTC&collateralCoin=ETH&repayAmount=1.0";

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralCoin", "ETH");
        parameters.put("repayAmount", "1.0"); // Changed from 1.0 to "1.0"

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createCryptoLoans().collateralRepayRate(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testCollateralRepayRateMissingLoanCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("collateralCoin", "ETH");
        parameters.put("repayAmount", "1.0"); // Changed from 1.0 to "1.0"

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().collateralRepayRate(parameters));
    }

    @Test
    public void testCollateralRepayRateMissingCollateralCoin() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("repayAmount", "1.0"); // Changed from 1.0 to "1.0"

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().collateralRepayRate(parameters));
    }

    @Test
    public void testCollateralRepayRateMissingRepayAmount() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralCoin", "ETH");

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().collateralRepayRate(parameters));
    }
}
