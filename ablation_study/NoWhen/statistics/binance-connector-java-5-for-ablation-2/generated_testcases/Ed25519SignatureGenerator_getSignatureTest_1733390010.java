
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
        signatureGenerator = Mockito.spy(new Ed25519SignatureGenerator("dummyPrivateKeyPath"));
        doReturn(mockPrivateKey).when(signatureGenerator).getPrivateKey();
    }

    @Test
    public void testGetSignature() {
        String data = "testData";
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        Ed25519Signer mockSigner = mock(Ed25519Signer.class);
        byte[] mockSignatureBytes = "mockSignature".getBytes(StandardCharsets.UTF_8);
        when(mockSigner.generateSignature()).thenReturn(mockSignatureBytes);

        doAnswer(invocation -> {
            Ed25519Signer signer = invocation.getArgument(0);
            signer.init(true, mockPrivateKey);
            signer.update(dataBytes, 0, dataBytes.length);
            return null;
        }).when(signatureGenerator).signData(any(Ed25519Signer.class), eq(dataBytes));

        String expectedSignature = Base64.getEncoder().encodeToString(mockSignatureBytes);

        String actualSignature = signatureGenerator.getSignature(data);

        assertEquals(expectedSignature, actualSignature);
    }
}
