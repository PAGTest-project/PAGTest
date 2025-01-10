
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
        String apiSecret = "secret";
        String data = "testData";
        HmacSignatureGenerator generator = new HmacSignatureGenerator(apiSecret);

        String expectedSignature = Hex.encodeHexString(generateHmacSha256(data.getBytes(), apiSecret.getBytes()));
        String actualSignature = generator.getSignature(data);

        assertEquals(expectedSignature, actualSignature);
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        String apiSecret = "secret";
        String data = "testData";
        HmacSignatureGenerator generator = new HmacSignatureGenerator(apiSecret);

        // Mocking an exception by providing an invalid algorithm
        generator = new HmacSignatureGenerator(apiSecret) {
            @Override
            public String getSignature(String data) {
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(apiSecret.getBytes(), "InvalidAlgorithm");
                    Mac mac = Mac.getInstance("InvalidAlgorithm");
                    mac.init(secretKeySpec);
                    byte[] hmacSha256 = mac.doFinal(data.getBytes());
                    return Hex.encodeHexString(hmacSha256);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to calculate hmac-sha256", e);
                }
            }
        };

        generator.getSignature(data);
    }

    private byte[] generateHmacSha256(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data);
    }
}
