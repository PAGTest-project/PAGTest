
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class RsaSignatureGenerator_getSignatureTest {

    private RsaSignatureGenerator generator;
    private RSAPrivateKey mockPrivateKey;

    @Before
    public void setUp() throws Exception {
        mockPrivateKey = mock(RSAPrivateKey.class);
        generator = new RsaSignatureGenerator("dummyPrivateKey") {
            @Override
            protected RSAPrivateKey parsePrivateKey(String privateKeyPem, String password) throws Exception {
                return mockPrivateKey;
            }
        };
    }

    @Test
    public void testGetSignature_Success() throws Exception {
        String data = "testData";
        byte[] mockSignatureBytes = "mockSignature".getBytes();
        String expectedSignature = Base64.getEncoder().encodeToString(mockSignatureBytes);

        Signature mockSignature = mock(Signature.class);
        when(mockSignature.sign()).thenReturn(mockSignatureBytes);

        try (MockedStatic<Signature> mockInstance = Mockito.mockStatic(Signature.class)) {
            mockInstance.when(() -> Signature.getInstance(RsaSignatureGenerator.RSA_SHA256)).thenReturn(mockSignature);

            String result = generator.getSignature(data);

            assertEquals(expectedSignature, result);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        String data = "testData";

        Signature mockSignature = mock(Signature.class);
        when(mockSignature.sign()).thenThrow(new Exception("Mock Exception"));

        try (MockedStatic<Signature> mockInstance = Mockito.mockStatic(Signature.class)) {
            mockInstance.when(() -> Signature.getInstance(RsaSignatureGenerator.RSA_SHA256)).thenReturn(mockSignature);

            generator.getSignature(data);
        }
    }
}
