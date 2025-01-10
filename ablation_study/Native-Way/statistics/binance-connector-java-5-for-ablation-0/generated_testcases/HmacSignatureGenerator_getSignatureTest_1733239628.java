
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacSignatureGenerator_getSignatureTest {

    @Test
    public void testGetSignature_Success() throws Exception {
        HmacSignatureGenerator generator = new HmacSignatureGenerator("secret");
        String data = "testData";
        String expectedSignature = Hex.encodeHexString(Mac.getInstance("HmacSHA256").doFinal(data.getBytes()));
        assertEquals(expectedSignature, generator.getSignature(data));
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        HmacSignatureGenerator generator = new HmacSignatureGenerator("secret");
        String data = "testData";
        Mac mockMac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec("secret".getBytes(), "HmacSHA256");
        mockMac.init(secretKeySpec);
        // Simulate an exception by overriding the doFinal method
        Mac spyMac = spy(mockMac);
        doThrow(new InvalidKeyException("Simulated exception")).when(spyMac).doFinal(any(byte[].class));
        // Replace the Mac instance with the mocked one
        HmacSignatureGenerator spyGenerator = spy(generator);
        doReturn(spyMac).when(spyGenerator).getMacInstance();
        spyGenerator.getSignature(data);
    }

    // Helper method to mock the Mac instance
    private Mac getMacInstance() throws NoSuchAlgorithmException {
        return Mac.getInstance("HmacSHA256");
    }
}
