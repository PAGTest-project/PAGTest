
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class HmacSignatureGenerator_getSignatureTest {

    private HmacSignatureGenerator generator;
    private String apiSecret;

    @Before
    public void setUp() {
        apiSecret = "testSecret";
        generator = new HmacSignatureGenerator(apiSecret);
    }

    @Test
    public void testGetSignature_Success() throws Exception {
        String data = "testData";
        byte[] expectedHmac = {0x01, 0x02, 0x03}; // Mocked HMAC result
        String expectedHex = Hex.encodeHexString(expectedHmac);

        Mac mockMac = mock(Mac.class);
        when(mockMac.doFinal(data.getBytes())).thenReturn(expectedHmac);

        SecretKeySpec mockSecretKeySpec = mock(SecretKeySpec.class);
        whenNew(SecretKeySpec.class).withArguments(apiSecret.getBytes(), HmacSignatureGenerator.HMAC_SHA256).thenReturn(mockSecretKeySpec);

        Mac mockMacInstance = mock(Mac.class);
        when(mockMacInstance.getInstance(HmacSignatureGenerator.HMAC_SHA256)).thenReturn(mockMac);

        String result = generator.getSignature(data);
        assertEquals(expectedHex, result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        String data = "testData";

        Mac mockMac = mock(Mac.class);
        when(mockMac.doFinal(data.getBytes())).thenThrow(new Exception("Mocked exception"));

        SecretKeySpec mockSecretKeySpec = mock(SecretKeySpec.class);
        whenNew(SecretKeySpec.class).withArguments(apiSecret.getBytes(), HmacSignatureGenerator.HMAC_SHA256).thenReturn(mockSecretKeySpec);

        Mac mockMacInstance = mock(Mac.class);
        when(mockMacInstance.getInstance(HmacSignatureGenerator.HMAC_SHA256)).thenReturn(mockMac);

        generator.getSignature(data);
    }
}
