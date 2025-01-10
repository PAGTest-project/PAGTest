
package org.jsoup.internal;

import org.jsoup.Progress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllableInputStream_onProgressTest {

    @Test
    void testOnProgress() {
        // Given
        ControllableInputStream cis = new ControllableInputStream(null, 0);
        Progress<Object> progressCallback = new Progress<>() {
            @Override
            public void onProgress(int read, int total, float percent, Object context) {
                // Mock implementation
            }
        };
        Object context = new Object();

        // When
        ControllableInputStream result = cis.onProgress(100, progressCallback, context);

        // Then
        assertEquals(100, cis.contentLength);
        assertEquals(progressCallback, cis.progress);
        assertEquals(context, cis.progressContext);
        assertEquals(cis, result);
    }
}
