
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.charset.StandardCharsets;

public class Sip_bodyStringTest {
    private Sip sip;

    @BeforeEach
    public void setUp() {
        sip = new Sip(new BaseProviders() {});
    }

    @Test
    void bodyString_returnsValidSdpFormat() {
        String sut = sip.bodyString();
        assertThat(sut).startsWith("v=0\n");
        assertThat(sut).contains("o=");
        assertThat(sut).contains("s=-\n");
        assertThat(sut).contains("c=IN IP4 ");
        assertThat(sut).contains("t=0 0\n");
        assertThat(sut).contains("m=audio ");
        assertThat(sut).contains(" RTP/AVP 0\n");
        assertThat(sut).contains("a=rtpmap:0 PCMU/8000");
    }

    @Test
    void bodyBytes_returnsValidByteArray() {
        byte[] sut = sip.bodyBytes();
        String bodyString = sip.bodyString();
        assertThat(sut).isEqualTo(bodyString.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void nameAddress_returnsValidNameAddressFormat() {
        String sut = sip.nameAddress();
        assertThat(sut).startsWith("<sip:");
        assertThat(sut).contains("@");
        assertThat(sut).contains(":");
        assertThat(sut).endsWith(">");
    }
}
