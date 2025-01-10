
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PseudoTCPBase_segToStrTest {

    @Test
    void testSegToStr() {
        // Given
        Segment seg = new Segment();
        seg.conv = 1234567890L;
        seg.flags = 0x02;
        seg.seq = 100;
        seg.len = 50;
        seg.ack = 200;
        seg.wnd = 300;
        seg.tsval = 400;
        seg.tsecr = 500;
        seg.data = new byte[]{1, 2, 3, 4, 5};

        // When
        String result = PseudoTCPBase.segToStr(seg);

        // Then
        String expected = "<CONV=1234567890><FLG=2><SEQ=100:150><ACK=200><WND=300><TS=400><TSR=500><LEN=50> data: 12345";
        assertEquals(expected, result);
    }
}
