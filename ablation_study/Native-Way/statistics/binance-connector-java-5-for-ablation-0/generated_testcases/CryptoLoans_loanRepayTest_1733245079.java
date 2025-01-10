
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

public class CryptoLoans_loanRepayTest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private static final long ORDER_ID = 100000001L;
    private static final double AMOUNT = 10.1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLoanRepay() {
        String path = "/sapi/v1/loan/repay?orderId=100000001&amount=10.1";

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", ORDER_ID);
        parameters.put("amount", AMOUNT);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        CryptoLoans cryptoLoans = new CryptoLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth());
        String result = cryptoLoans.loanRepay(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testLoanRepayWithoutOrderId() {
        String path = "/sapi/v1/loan/repay?amount=10.1";

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", AMOUNT);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        CryptoLoans cryptoLoans = new CryptoLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth());
        assertThrows(BinanceConnectorException.class, () -> cryptoLoans.loanRepay(parameters));
    }

    @Test
    public void testLoanRepayWithoutAmount() {
        String path = "/sapi/v1/loan/repay?orderId=100000001";

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", ORDER_ID);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        SpotClient client = new SpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        CryptoLoans cryptoLoans = new CryptoLoans(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth());
        assertThrows(BinanceConnectorException.class, () -> cryptoLoans.loanRepay(parameters));
    }
}
