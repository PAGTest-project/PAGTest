
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class UrlBuilder_buildStreamUrlTest {

    @Test
    public void testBuildStreamUrlWithoutStreams() {
        String baseUrl = "https://api.binance.com";
        assertEquals("https://api.binance.com/stream", UrlBuilder.buildStreamUrl(baseUrl, null));
    }

    @Test
    public void testBuildStreamUrlWithStreams() {
        String baseUrl = "https://api.binance.com";
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        streams.add("stream2");
        assertEquals("https://api.binance.com/stream?streams=stream1/stream2", UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithEmptyStreams() {
        String baseUrl = "https://api.binance.com";
        ArrayList<String> streams = new ArrayList<>();
        assertEquals("https://api.binance.com/stream", UrlBuilder.buildStreamUrl(baseUrl, streams));
    }
}
