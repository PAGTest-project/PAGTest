
package org.ice4j.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.ice4j.StunException;

public class ChannelData_decodeTest {

    @Test
    public void testDecode_ValidMessage() throws StunException {
        byte[] binMessage = {0x01, 0x00, 0x00, 0x04, 0x01, 0x02, 0x03, 0x04};
        char offset = 0;

        ChannelData result = ChannelData.decode(binMessage, offset);

        assertEquals(0x0100, result.getChannelNumber());
        assertArrayEquals(new byte[]{0x01, 0x02, 0x03, 0x04}, result.getData());
    }

    @Test
    public void testDecode_InvalidChannelNumber() {
        byte[] binMessage = {0x00, 0x00, 0x00, 0x04, 0x01, 0x02, 0x03, 0x04};
        char offset = 0;

        assertThrows(StunException.class, () -> ChannelData.decode(binMessage, offset));
    }

    @Test
    public void testDecode_SizeTooShort() {
        byte[] binMessage = {0x01, 0x00, 0x00};
        char offset = 0;

        assertThrows(StunException.class, () -> ChannelData.decode(binMessage, offset));
    }

    @Test
    public void testDecode_SizeMismatch() {
        byte[] binMessage = {0x01, 0x00, 0x00, 0x08, 0x01, 0x02, 0x03, 0x04};
        char offset = 0;

        assertThrows(StunException.class, () -> ChannelData.decode(binMessage, offset));
    }
}
