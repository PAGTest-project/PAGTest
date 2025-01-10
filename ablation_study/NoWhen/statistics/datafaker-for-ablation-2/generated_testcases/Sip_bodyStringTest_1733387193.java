
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Sip_bodyStringTest {
    private Sip sip;

    @BeforeEach
    public void setUp() {
        sip = new Sip(new BaseProviders());
    }

    @Test
    void bodyString_returnValidSdpBodyString() {
        String body = sip.bodyString();

        assertThat(body).startsWith("v=0\n");
        assertThat(body).containsPattern("o=\\w+ \\w+ IN IP4 \\w+\\.\\w+\\.\\w+\\.\\w+");
        assertThat(body).contains("s=-\n");
        assertThat(body).containsPattern("c=IN IP4 \\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\n");
        assertThat(body).contains("t=0 0\n");
        assertThat(body).containsPattern("m=audio \\d{5} RTP/AVP 0\n");
        assertThat(body).contains("a=rtpmap:0 PCMU/8000");
    }

    @Test
    void bodyBytes_returnValidSdpBodyBytes() {
        byte[] bodyBytes = sip.bodyBytes();
        String bodyString = new String(bodyBytes, java.nio.charset.StandardCharsets.UTF_8);

        assertThat(bodyString).startsWith("v=0\n");
        assertThat(bodyString).containsPattern("o=\\w+ \\w+ IN IP4 \\w+\\.\\w+\\.\\w+\\.\\w+");
        assertThat(bodyString).contains("s=-\n");
        assertThat(bodyString).containsPattern("c=IN IP4 \\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\n");
        assertThat(bodyString).contains("t=0 0\n");
        assertThat(bodyString).containsPattern("m=audio \\d{5} RTP/AVP 0\n");
        assertThat(bodyString).contains("a=rtpmap:0 PCMU/8000");
    }

    @Test
    void nameAddress_returnValidNameAddressString() {
        String[] sut = sip.nameAddress().split("@");

        assertThat(sut[0].split(":")[1]).matches("\\w+");
        assertThat(sut[1].split(":")[0]).matches("^\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}$");
    }
}
