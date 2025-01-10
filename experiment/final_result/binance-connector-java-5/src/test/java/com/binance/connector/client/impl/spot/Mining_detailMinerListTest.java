
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.RequestHandler;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class Mining_detailMinerListTest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private Mining mining;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
        this.mining = new Mining(baseUrl, MockData.API_KEY, MockData.SECRET_KEY, false, null);
    }

    @Test
    public void testDetailMinerListSuccess() {
        String path = "/sapi/v1/mining/worker/detail";
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");
        parameters.put("userName", "testUser");
        parameters.put("workerName", "testWorker");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        String result = mining.detailMinerList(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testDetailMinerListMissingAlgo() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "testUser");
        parameters.put("workerName", "testWorker");

        assertThrows(BinanceConnectorException.class, () -> mining.detailMinerList(parameters));
    }

    @Test
    public void testDetailMinerListMissingUserName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");
        parameters.put("workerName", "testWorker");

        assertThrows(BinanceConnectorException.class, () -> mining.detailMinerList(parameters));
    }

    @Test
    public void testDetailMinerListMissingWorkerName() {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("algo", "sha256");
        parameters.put("userName", "testUser");

        assertThrows(BinanceConnectorException.class, () -> mining.detailMinerList(parameters));
    }
}
