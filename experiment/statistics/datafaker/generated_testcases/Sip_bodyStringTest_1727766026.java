
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sip_bodyStringTest {
    private Sip sip;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {};
        sip = new Sip(faker);
    }

    @Test
    void testBodyString() {
        String body = sip.bodyString();
        assertTrue(body.startsWith("v=0\n"));
        assertTrue(body.contains("o="));
        assertTrue(body.contains("s=-\n"));
        assertTrue(body.contains("c=IN IP4 "));
        assertTrue(body.contains("t=0 0\n"));
        assertTrue(body.contains("m=audio "));
        assertTrue(body.contains("RTP/AVP 0\n"));
        assertTrue(body.contains("a=rtpmap:0 PCMU/8000"));
    }
}
