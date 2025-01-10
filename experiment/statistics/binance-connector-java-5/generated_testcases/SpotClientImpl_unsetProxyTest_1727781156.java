
package com.binance.connector.client.impl;

import com.binance.connector.client.utils.ProxyAuth;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpotClientImpl_unsetProxyTest {
    private SpotClientImpl spotClient;
    private ProxyAuth proxy;

    @Before
    public void setUp() {
        spotClient = new SpotClientImpl();
        proxy = new ProxyAuth(null, null); // Dummy proxy for setting up the test
    }

    @Test
    public void testUnsetProxy() {
        // Given: A proxy is set
        spotClient.setProxy(proxy);

        // When: unsetProxy is called
        spotClient.unsetProxy();

        // Then: The proxy should be null
        assertNull(spotClient.getProxy());
    }
}
