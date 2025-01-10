
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class UrlBuilder_buildStreamUrlTest {

    @Test
    public void testBuildStreamUrlWithStreams() {
        String baseUrl = "https://test.com";
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        streams.add("stream2");
        String expectedUrl = "https://test.com/stream?streams=stream1/stream2";
        assertEquals(expectedUrl, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithoutStreams() {
        String baseUrl = "https://test.com";
        ArrayList<String> streams = new ArrayList<>();
        String expectedUrl = "https://test.com/stream";
        assertEquals(expectedUrl, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithNullStreams() {
        String baseUrl = "https://test.com";
        String expectedUrl = "https://test.com/stream";
        assertEquals(expectedUrl, UrlBuilder.buildStreamUrl(baseUrl, null));
    }
}
