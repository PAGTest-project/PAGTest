
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class UrlBuilder_joinStreamUrlsTest {

    @Test
    public void testJoinStreamUrlsWithEmptyStreams() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> streams = new ArrayList<>();
        assertEquals(sb.toString(), UrlBuilder.joinStreamUrls(sb, streams).toString());
    }

    @Test
    public void testJoinStreamUrlsWithSingleStream() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        assertEquals("stream1", UrlBuilder.joinStreamUrls(sb, streams).toString());
    }

    @Test
    public void testJoinStreamUrlsWithMultipleStreams() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> streams = new ArrayList<>();
        streams.add("stream1");
        streams.add("stream2");
        streams.add("stream3");
        assertEquals("stream1/stream2/stream3", UrlBuilder.joinStreamUrls(sb, streams).toString());
    }

    @Test
    public void testJoinStreamUrlsWithNullStreams() {
        StringBuilder sb = new StringBuilder();
        assertEquals(sb.toString(), UrlBuilder.joinStreamUrls(sb, null).toString());
    }
}
