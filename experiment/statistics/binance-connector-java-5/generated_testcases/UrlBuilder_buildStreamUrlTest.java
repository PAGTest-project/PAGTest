
package com.binance.connector.client.utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class UrlBuilder_buildStreamUrlTest {
    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "https://api.binance.com";
    }

    @Test
    public void testBuildStreamUrlWithStreams() {
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        streams.add("stream2");

        String expectedUrl = "https://api.binance.com/stream?streams=stream1/stream2";
        assertEquals(expectedUrl, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithoutStreams() {
        ArrayList<String> streams = new ArrayList<>();

        String expectedUrl = "https://api.binance.com/stream";
        assertEquals(expectedUrl, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }

    @Test
    public void testBuildStreamUrlWithNullStreams() {
        ArrayList<String> streams = null;

        String expectedUrl = "https://api.binance.com/stream";
        assertEquals(expectedUrl, UrlBuilder.buildStreamUrl(baseUrl, streams));
    }
}
