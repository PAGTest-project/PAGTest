
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Sip_bodyStringTest {
    private Sip sip;

    @BeforeEach
    public void setUp() {
        sip = new Sip(new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "";
            }
        });
    }

    @Test
    void bodyString_returnValidSDPBodyString() {
        String body = sip.bodyString();

        assertThat(body).startsWith("v=0\n");
        assertThat(body).containsPattern("o=\\w+ \\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12} IN IP4 [\\w.]+");
        assertThat(body).contains("s=-\n");
        assertThat(body).containsPattern("c=IN IP4 \\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}");
        assertThat(body).contains("t=0 0\n");
        assertThat(body).containsPattern("m=audio \\d{5} RTP/AVP 0");
        assertThat(body).contains("a=rtpmap:0 PCMU/8000");
    }
}
