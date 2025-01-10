
package com.binance.connector.client.utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class UrlBuilder_joinStreamUrlsTest {
    private StringBuilder sb;

    @Before
    public void setUp() {
        sb = new StringBuilder();
    }

    @Test
    public void testJoinStreamUrlsWithValidStreams() {
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        streams.add("stream2");
        streams.add("stream3");

        StringBuilder result = UrlBuilder.joinStreamUrls(sb, streams);
        assertEquals("stream1/stream2/stream3", result.toString());
    }

    @Test
    public void testJoinStreamUrlsWithEmptyStreams() {
        ArrayList<String> streams = new ArrayList<>();

        StringBuilder result = UrlBuilder.joinStreamUrls(sb, streams);
        assertEquals("", result.toString());
    }

    @Test
    public void testJoinStreamUrlsWithNullStreams() {
        StringBuilder result = UrlBuilder.joinStreamUrls(sb, null);
        assertEquals("", result.toString());
    }
}
