
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
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class CryptoLoans_flexibleLoanAdjustLtvTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private static final String apiKey = "apiKey";
    private static final String secretKey = "secretKey";
    private static final boolean showLimitUsage = false;
    private static final ProxyAuth proxy = null;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testFlexibleLoanAdjustLtv() {
        String path = "/sapi/v1/loan/flexible/adjust/ltv?loanCoin=BTC&collateralCoin=ETH&adjustmentAmount=10.1&direction=ADDITIONAL";

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BTC");
        parameters.put("collateralCoin", "ETH");
        parameters.put("adjustmentAmount", 10.1);
        parameters.put("direction", "ADDITIONAL");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(apiKey, new HmacSignatureGenerator(secretKey), baseUrl, showLimitUsage, proxy);
        String result = client.createCryptoLoans().flexibleLoanAdjustLtv(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testFlexibleLoanAdjustLtvWithoutParameters() {
        String path = "/sapi/v1/loan/flexible/adjust/ltv";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(apiKey, new HmacSignatureGenerator(secretKey), baseUrl, showLimitUsage, proxy);
        assertThrows(BinanceConnectorException.class, () -> client.createCryptoLoans().flexibleLoanAdjustLtv(parameters));
    }
}
