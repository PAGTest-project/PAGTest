
package com.binance.connector.client.utils.httpclient;

import com.binance.connector.client.utils.ProxyAuth;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.net.Proxy;
import java.net.InetSocketAddress;
import okhttp3.Authenticator;
import okhttp3.Route;

public class HttpClientSingleton_getHttpClientTest {

    private ProxyAuth proxyAuth;
    private ProxyAuth proxyAuthWithAuth;

    @Before
    public void setUp() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
        proxyAuth = new ProxyAuth(proxy, null);
        proxyAuthWithAuth = new ProxyAuth(proxy, new Authenticator() {
            @Override
            public okhttp3.Request authenticate(Route route, okhttp3.Response response) {
                return response.request().newBuilder()
                        .header("Proxy-Authorization", "Basic " + "credentials")
                        .build();
            }
        });
    }

    @Test
    public void testGetHttpClient_InitialCall() {
        OkHttpClient client = HttpClientSingleton.getHttpClient(proxyAuth);
        Assert.assertNotNull(client);
        Assert.assertEquals(proxyAuth.getProxy(), client.proxy());
    }

    @Test
    public void testGetHttpClient_WithAuth() {
        OkHttpClient client = HttpClientSingleton.getHttpClient(proxyAuthWithAuth);
        Assert.assertNotNull(client);
        Assert.assertEquals(proxyAuthWithAuth.getProxy(), client.proxy());
    }

    @Test
    public void testGetHttpClient_VerifyProxyChange() {
        OkHttpClient client = HttpClientSingleton.getHttpClient(proxyAuth);
        Assert.assertNotNull(client);
        Assert.assertEquals(proxyAuth.getProxy(), client.proxy());

        Proxy newProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8081));
        ProxyAuth newProxyAuth = new ProxyAuth(newProxy, null);
        OkHttpClient newClient = HttpClientSingleton.getHttpClient(newProxyAuth);
        Assert.assertNotNull(newClient);
        Assert.assertEquals(newProxyAuth.getProxy(), newClient.proxy());
    }
}
