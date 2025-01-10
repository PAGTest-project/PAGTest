
package org.ice4j.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ChannelData_getDataLengthTest {

    @Test
    public void testGetDataLength_withNullData() {
        ChannelData channelData = new ChannelData();
        assertEquals(0, channelData.getDataLength());
    }

    @Test
    public void testGetDataLength_withNonNullData() {
        ChannelData channelData = new ChannelData();
        byte[] data = new byte[] { 1, 2, 3 };
        channelData.setData(data);
        assertEquals(data.length, channelData.getDataLength());
    }
}
