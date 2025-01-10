
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Before;
import org.junit.Test;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Ed25519SignatureGenerator_getSignatureTest {

    private Ed25519SignatureGenerator generator;
    private Ed25519PrivateKeyParameters mockPrivateKey;

    @Before
    public void setUp() throws IOException {
        Security.addProvider(new BouncyCastleProvider());
        mockPrivateKey = mock(Ed25519PrivateKeyParameters.class);
        generator = spy(new Ed25519SignatureGenerator("path/to/privateKey"));
        doReturn(mockPrivateKey).when(generator).initializePrivateKey(anyString());
    }

    @Test
    public void testGetSignature() {
        String data = "testData";
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        Ed25519Signer mockSigner = mock(Ed25519Signer.class);
        byte[] mockSignatureBytes = "mockSignature".getBytes(StandardCharsets.UTF_8);
        String expectedSignature = Base64.getEncoder().encodeToString(mockSignatureBytes);

        when(mockSigner.generateSignature()).thenReturn(mockSignatureBytes);

        String actualSignature = generator.getSignature(data);

        assertEquals(expectedSignature, actualSignature);
        verify(mockSigner).init(true, mockPrivateKey);
        verify(mockSigner).update(dataBytes, 0, dataBytes.length);
    }
}
