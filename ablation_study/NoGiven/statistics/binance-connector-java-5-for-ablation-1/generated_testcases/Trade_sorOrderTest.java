
package com.binance.connector.client.impl.spot;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class Trade_sorOrderTest {
    private Trade trade;
    private String baseUrl;
    private String apiKey;
    private String secretKey;
    private boolean showLimitUsage;

    @Before
    public void setUp() {
        baseUrl = "https://testnet.binance.vision";
        apiKey = "testApiKey";
        secretKey = "testSecretKey";
        showLimitUsage = true;
        trade = new Trade(baseUrl, apiKey, new HmacSignatureGenerator(secretKey), showLimitUsage, null);
    }

    @Test
    public void testSorOrderSuccess() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("quantity", 1.0);
        parameters.put("price", 300.0);

        String response = trade.sorOrder(parameters);
        assertNotNull(response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSorOrderMissingQuantity() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("price", 300.0);

        trade.sorOrder(parameters);
    }

    @Test
    public void testSorOrderInvalidSymbol() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "INVALIDSYMBOL");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("quantity", 1.0);
        parameters.put("price", 300.0);

        String response = trade.sorOrder(parameters);
        assertNotNull(response);
    }
}
