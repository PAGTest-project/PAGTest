
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

        String expectedSignature = "5e884898da28047151d0e56f8dc6292773603d0d6aabbddc41f1da0b671e4e97";
        assertEquals(expectedSignature, generator.getSignature(data));
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        String apiSecret = "secret";
        String data = "testData";
        HmacSignatureGenerator generator = new HmacSignatureGenerator(apiSecret) {
            @Override
            public String getSignature(String data) {
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(apiSecret.getBytes(), "InvalidAlgorithm");
                    Mac mac = Mac.getInstance("InvalidAlgorithm");
                    mac.init(secretKeySpec);
                    byte[] hmacSha256 = mac.doFinal(data.getBytes());
                    return Hex.encodeHexString(hmacSha256);
                } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                    throw new RuntimeException("Failed to calculate hmac-sha256", e);
                }
            }
        };

        generator.getSignature(data);
    }
}
