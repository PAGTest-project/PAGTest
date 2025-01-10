
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class UrlBuilder_buildStreamUrlTest {

    @Test
    public void testBuildStreamUrlWithStreams() {
        String baseUrl = "https://example.com";
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        streams.add("stream2");
        String expected = "https://example.com/stream?streams=stream1/stream2";
        assertEquals(expected, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithoutStreams() {
        String baseUrl = "https://example.com";
        ArrayList<String> streams = new ArrayList<>();
        String expected = "https://example.com/stream";
        assertEquals(expected, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithNullStreams() {
        String baseUrl = "https://example.com";
        String expected = "https://example.com/stream";
        assertEquals(expected, UrlBuilder.buildStreamUrl(baseUrl, null));
    }
}
