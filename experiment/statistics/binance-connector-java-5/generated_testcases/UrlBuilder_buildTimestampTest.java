
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class UrlBuilder_buildTimestampTest {

    @Test
    public void testBuildTimestamp() {
        String timestamp = UrlBuilder.buildTimestamp();
        assertTrue(timestamp.matches("\\d+"));
    }
}
