
package com.binance.connector.client.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpotClientImpl_setShowLimitUsageTest {
    private SpotClientImpl spotClient;

    @Before
    public void setUp() {
        spotClient = new SpotClientImpl();
    }

    @Test
    public void testSetShowLimitUsage() {
        // Given
        boolean showLimitUsage = true;

        // When
        spotClient.setShowLimitUsage(showLimitUsage);

        // Then
        assertEquals(showLimitUsage, spotClient.showLimitUsage);
    }
}
