
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

public class CryptoLoans_flexibleLoanAdjustLtvTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testFlexibleLoanAdjustLtvSuccess() {
        String path = "/sapi/v1/loan/flexible/adjust/ltv?loanCoin=USDT&collateralCoin=BTC&adjustmentAmount=100&direction=ADDITIONAL";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BTC");
        parameters.put("adjustmentAmount", 100);
        parameters.put("direction", "ADDITIONAL");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createCryptoLoans().flexibleLoanAdjustLtv(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFlexibleLoanAdjustLtvMissingRequiredParameter() {
        String path = "/sapi/v1/loan/flexible/adjust/ltv?loanCoin=USDT&collateralCoin=BTC&direction=ADDITIONAL";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BTC");
        parameters.put("direction", "ADDITIONAL");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().flexibleLoanAdjustLtv(parameters));
    }

    @Test
    public void testFlexibleLoanAdjustLtvInvalidDirection() {
        String path = "/sapi/v1/loan/flexible/adjust/ltv?loanCoin=USDT&collateralCoin=BTC&adjustmentAmount=100&direction=INVALID";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BTC");
        parameters.put("adjustmentAmount", 100);
        parameters.put("direction", "INVALID");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().flexibleLoanAdjustLtv(parameters));
    }
}
