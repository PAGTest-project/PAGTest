
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
    void bodyString_returnsValidSdpString() {
        String sut = sip.bodyString();
        assertThat(sut).startsWith("v=0\n");
        assertThat(sut).contains("o=");
        assertThat(sut).contains("s=-\n");
        assertThat(sut).contains("c=IN IP4 ");
        assertThat(sut).contains("t=0 0\n");
        assertThat(sut).contains("m=audio ");
        assertThat(sut).contains("a=rtpmap:0 PCMU/8000");
    }

    @Test
    void bodyBytes_returnsValidSdpByteArray() {
        byte[] sut = sip.bodyBytes();
        String bodyString = new String(sut, java.nio.charset.StandardCharsets.UTF_8);
        assertThat(bodyString).startsWith("v=0\n");
        assertThat(bodyString).contains("o=");
        assertThat(bodyString).contains("s=-\n");
        assertThat(bodyString).contains("c=IN IP4 ");
        assertThat(bodyString).contains("t=0 0\n");
        assertThat(bodyString).contains("m=audio ");
        assertThat(bodyString).contains("a=rtpmap:0 PCMU/8000");
    }

    @Test
    void nameAddress_returnsValidNameAddressString() {
        String sut = sip.nameAddress();
        assertThat(sut).startsWith("<sip:");
        assertThat(sut).contains("@");
        assertThat(sut).endsWith(">");
    }

    @Test
    void rtpPort_returnPositiveEvenInt() {
        int sut = sip.rtpPort();
        assertThat(sut).isGreaterThanOrEqualTo(2);
        assertThat(sut % 2).isZero();
    }

    @Test
    void messagingPort_returnValidPortNumber() {
        int sut = sip.messagingPort();
        assertThat(sut).isBetween(1000, 9999);
    }
}
