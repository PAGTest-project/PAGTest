
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;

public class RsaSignatureGenerator_getSignatureTest {

    @Test
    public void testGetSignature_Success() throws Exception {
        // Given
        String privateKeyPem = "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDZ\n-----END PRIVATE KEY-----";
        String data = "testData";
        RSAPrivateKey mockPrivateKey = mock(RSAPrivateKey.class);
        Signature mockSignature = mock(Signature.class);
        byte[] mockSignatureBytes = "mockSignature".getBytes();

        RsaSignatureGenerator generator = spy(new RsaSignatureGenerator(privateKeyPem));
        doReturn(mockPrivateKey).when(generator).parsePrivateKey(anyString(), anyString());
        doReturn(mockSignature).when(mockSignature).getInstance(RsaSignatureGenerator.RSA_SHA256);
        doNothing().when(mockSignature).initSign(mockPrivateKey);
        doNothing().when(mockSignature).update(data.getBytes());
        doReturn(mockSignatureBytes).when(mockSignature).sign();

        // When
        String result = generator.getSignature(data);

        // Then
        assertEquals(Base64.getEncoder().encodeToString(mockSignatureBytes), result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        // Given
        String privateKeyPem = "invalidKey";
        String data = "testData";
        RSAPrivateKey mockPrivateKey = mock(RSAPrivateKey.class);
        Signature mockSignature = mock(Signature.class);

        RsaSignatureGenerator generator = spy(new RsaSignatureGenerator(privateKeyPem));
        doReturn(mockPrivateKey).when(generator).parsePrivateKey(anyString(), anyString());
        doReturn(mockSignature).when(mockSignature).getInstance(RsaSignatureGenerator.RSA_SHA256);
        doThrow(new Exception("Mock Exception")).when(mockSignature).initSign(mockPrivateKey);

        // When
        generator.getSignature(data);

        // Then
        // Expecting RuntimeException
    }
}
