
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Ed25519SignatureGenerator_getSignatureTest {

    @Mock
    private Ed25519PrivateKeyParameters mockPrivateKey;

    @Mock
    private Ed25519Signer mockSigner;

    private Ed25519SignatureGenerator signatureGenerator;

    @Before
    public void setUp() throws Exception {
        signatureGenerator = new Ed25519SignatureGenerator("dummyPrivateKeyPath") {
            @Override
            public String getSignature(String data) {
                byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
                Ed25519Signer signer = mockSigner;
                signer.init(true, mockPrivateKey);
                signer.update(dataBytes, 0, dataBytes.length);
                byte[] signatureBytes = new byte[]{1, 2, 3}; // Mock signature bytes
                when(mockSigner.generateSignature()).thenReturn(signatureBytes);
                return Base64.getEncoder().encodeToString(signatureBytes);
            }
        };
    }

    @Test
    public void testGetSignature() {
        String data = "testData";
        String expectedSignature = Base64.getEncoder().encodeToString(new byte[]{1, 2, 3});
        String actualSignature = signatureGenerator.getSignature(data);
        assertEquals(expectedSignature, actualSignature);
    }
}
