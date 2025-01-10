
package com.binance.connector.client.utils.signaturegenerator;

import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Ed25519SignatureGenerator_getSignatureTest {

    private Ed25519SignatureGenerator signatureGenerator;
    private Ed25519Signer mockSigner;

    @Before
    public void setUp() throws Exception {
        mockSigner = mock(Ed25519Signer.class);
        signatureGenerator = Mockito.spy(new Ed25519SignatureGenerator("testPrivateKey"));
        doReturn(mockSigner).when(signatureGenerator).createSigner();
    }

    @Test
    public void testGetSignature() {
        String data = "testData";
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = "signature".getBytes(StandardCharsets.UTF_8);
        String expectedSignature = Base64.getEncoder().encodeToString(signatureBytes);

        when(mockSigner.generateSignature()).thenReturn(signatureBytes);

        String actualSignature = signatureGenerator.getSignature(data);

        verify(mockSigner).init(true, any());
        verify(mockSigner).update(dataBytes, 0, dataBytes.length);
        assertEquals(expectedSignature, actualSignature);
    }

    private Ed25519Signer createSigner() {
        return new Ed25519Signer();
    }
}
