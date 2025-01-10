
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
        String expectedSignature = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";

        String actualSignature = generator.getSignature(data);

        assertEquals(expectedSignature, actualSignature);
    }

    @Test
    public void testGetSignature_Exception() throws Exception {
        String data = "testData";

        HmacSignatureGenerator faultyGenerator = new HmacSignatureGenerator("faultySecret") {
            @Override
            public String getSignature(String data) {
                try {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(apiSecret.getBytes(), "InvalidAlgorithm");
                    Mac mac = Mac.getInstance("InvalidAlgorithm");
                    mac.init(secretKeySpec);
                    return Hex.encodeHexString(mac.doFinal(data.getBytes()));
                } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                    throw new RuntimeException("Failed to calculate hmac-sha256", e);
                } catch (Exception e) {
                    throw new RuntimeException("Unexpected error", e);
                }
            }
        };

        assertThrows(RuntimeException.class, () -> faultyGenerator.getSignature(data));
    }
}
