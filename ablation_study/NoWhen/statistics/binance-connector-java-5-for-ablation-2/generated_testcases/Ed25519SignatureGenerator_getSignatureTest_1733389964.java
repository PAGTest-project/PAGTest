
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Before;
import org.junit.Test;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Ed25519SignatureGenerator_getSignatureTest {

    private Ed25519SignatureGenerator signatureGenerator;
    private Ed25519PrivateKeyParameters mockPrivateKey;

    @Before
    public void setUp() throws Exception {
        mockPrivateKey = mock(Ed25519PrivateKeyParameters.class);
        signatureGenerator = new Ed25519SignatureGenerator("dummyPrivateKeyPath") {
            @Override
            public Ed25519PrivateKeyParameters getPrivateKey() {
                return mockPrivateKey;
            }
        };
    }

    @Test
    public void testGetSignature() {
        String data = "testData";
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        Ed25519Signer mockSigner = mock(Ed25519Signer.class);
        byte[] mockSignatureBytes = "mockSignature".getBytes(StandardCharsets.UTF_8);
        when(mockSigner.generateSignature()).thenReturn(mockSignatureBytes);

        String expectedSignature = Base64.getEncoder().encodeToString(mockSignatureBytes);

        String actualSignature = signatureGenerator.getSignature(data);

        assertEquals(expectedSignature, actualSignature);
        verify(mockSigner).init(true, mockPrivateKey);
        verify(mockSigner).update(dataBytes, 0, dataBytes.length);
    }
}
