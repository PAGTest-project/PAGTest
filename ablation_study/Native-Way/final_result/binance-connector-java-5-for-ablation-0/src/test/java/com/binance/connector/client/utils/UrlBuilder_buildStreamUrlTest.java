
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class UrlBuilder_buildStreamUrlTest {

    @Test
    public void testBuildStreamUrlWithEmptyStreams() {
        String baseUrl = "https://example.com";
        ArrayList<String> streams = new ArrayList<>();
        String expected = "https://example.com/stream";
        assertEquals(expected, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithSingleStream() {
        String baseUrl = "https://example.com";
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        String expected = "https://example.com/stream?streams=stream1";
        assertEquals(expected, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithMultipleStreams() {
        String baseUrl = "https://example.com";
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        streams.add("stream2");
        streams.add("stream3");
        String expected = "https://example.com/stream?streams=stream1/stream2/stream3";
        assertEquals(expected, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithNullStreams() {
        String baseUrl = "https://example.com";
        ArrayList<String> streams = null;
        String expected = "https://example.com/stream";
        assertEquals(expected, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }
}
