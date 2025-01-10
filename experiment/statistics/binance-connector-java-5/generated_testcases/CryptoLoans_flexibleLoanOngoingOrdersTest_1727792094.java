
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

public class CryptoLoans_flexibleLoanOngoingOrdersTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private CryptoLoans cryptoLoans;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.cryptoLoans = new CryptoLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, null);
    }

    @Test
    public void testFlexibleLoanOngoingOrdersWithValidParameters() {
        String path = "/sapi/v1/loan/flexible/ongoing/orders?loanCoin=USDT&collateralCoin=BTC";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = cryptoLoans.flexibleLoanOngoingOrders(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFlexibleLoanOngoingOrdersWithoutParameters() {
        String path = "/sapi/v1/loan/flexible/ongoing/orders";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = cryptoLoans.flexibleLoanOngoingOrders(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFlexibleLoanOngoingOrdersWithInvalidParameters() {
        String path = "/sapi/v1/loan/flexible/ongoing/orders?loanCoin=INVALID&collateralCoin=INVALID";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "INVALID");
        parameters.put("collateralCoin", "INVALID");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        assertThrows(BinanceConnectorException.class, () -> cryptoLoans.flexibleLoanOngoingOrders(parameters));
    }
}
