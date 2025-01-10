
package com.binance.connector.client.impl;

import com.binance.connector.client.utils.ProxyAuth;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpotClientImpl_setProxyTest {
    private SpotClientImpl spotClient;

    @Before
    public void setUp() {
        spotClient = new SpotClientImpl();
    }

    @Test
    public void testSetProxy() {
        ProxyAuth proxy = new ProxyAuth(new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress("localhost", 8080)), null);
        spotClient.setProxy(proxy);
        assertEquals(proxy, spotClient.proxy);
    }

    @Test
    public void testUnsetProxy() {
        ProxyAuth proxy = new ProxyAuth(new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress("localhost", 8080)), null);
        spotClient.setProxy(proxy);
        spotClient.unsetProxy();
        assertNull(spotClient.proxy);
    }
}
