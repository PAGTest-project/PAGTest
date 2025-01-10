
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

public class PortfolioMargin_assetCollectionTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private PortfolioMargin portfolioMargin;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.portfolioMargin = new PortfolioMargin(baseUrl, MockData.API_KEY, new HmacSignatureGenerator(MockData.SECRET_KEY), true, new ProxyAuth(null, null));
    }

    @Test
    public void testAssetCollectionSuccess() {
        String path = "/sapi/v1/portfolio/asset-collection?asset=BTC";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "BTC");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, "{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = portfolioMargin.assetCollection(parameters);
        assertEquals("{\"key_1\": \"value_1\", \"key_2\": \"value_2\"}", result);
    }

    @Test
    public void testAssetCollectionMissingAsset() {
        Map<String, Object> parameters = new LinkedHashMap<>();

        assertThrows(BinanceConnectorException.class, () -> {
            portfolioMargin.assetCollection(parameters);
        });
    }
}
