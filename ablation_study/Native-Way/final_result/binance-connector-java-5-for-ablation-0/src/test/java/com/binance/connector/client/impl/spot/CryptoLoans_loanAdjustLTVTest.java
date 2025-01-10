
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

public class CryptoLoans_loanAdjustLTVTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private static final long orderId = 123456789L;
    private static final double amount = 100.0;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLoanAdjustLTVWithoutParameters() {
        String path = "/sapi/v1/loan/adjust/ltv";
        Map<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        CryptoLoans cryptoLoans = new CryptoLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, new ProxyAuth(null, null));
        assertThrows(BinanceConnectorException.class, () -> cryptoLoans.loanAdjustLTV(parameters));
    }

    @Test
    public void testLoanAdjustLTVWithParameters() {
        String path = "/sapi/v1/loan/adjust/ltv";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", orderId);
        parameters.put("amount", amount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        CryptoLoans cryptoLoans = new CryptoLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), false, new ProxyAuth(null, null));
        String result = cryptoLoans.loanAdjustLTV(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
