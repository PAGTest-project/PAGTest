
package org.ice4j.pseudotcp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PseudoTCPBase_segToStrTest {

    @Test
    void testSegToStr() {
        Segment seg = new Segment();
        seg.conv = 1234567890L;
        seg.flags = 0x02;
        seg.seq = 987654321L;
        seg.len = 10;
        seg.ack = 1122334455L;
        seg.wnd = 5000;
        seg.tsval = 1234567890L;
        seg.tsecr = 987654321L;
        seg.data = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        String expected = "<CONV=1234567890><FLG=2><SEQ=987654321:987654331><ACK=1122334455><WND=5000><TS=1234567890><TSR=987654321><LEN=10> data: 12345678910";
        String result = PseudoTCPBase.segToStr(seg);

        assertEquals(expected, result);
    }
}
