
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
        String data = "testData";
        RSAPrivateKey mockPrivateKey = mock(RSAPrivateKey.class);
        Signature mockSignature = mock(Signature.class);
        byte[] mockSignedBytes = "mockSignature".getBytes();
        String expectedSignature = Base64.getEncoder().encodeToString(mockSignedBytes);

        RsaSignatureGenerator generator = spy(new RsaSignatureGenerator("dummyPrivateKey"));
        doReturn(mockPrivateKey).when(generator).parsePrivateKey(anyString(), anyString());

        // When
        when(Signature.getInstance("SHA256withRSA")).thenReturn(mockSignature);
        when(mockSignature.sign()).thenReturn(mockSignedBytes);

        String result = generator.getSignature(data);

        // Then
        assertEquals(expectedSignature, result);
        verify(mockSignature).initSign(mockPrivateKey);
        verify(mockSignature).update(data.getBytes());
        verify(mockSignature).sign();
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        // Given
        String data = "testData";
        RSAPrivateKey mockPrivateKey = mock(RSAPrivateKey.class);
        Signature mockSignature = mock(Signature.class);

        RsaSignatureGenerator generator = spy(new RsaSignatureGenerator("dummyPrivateKey"));
        doReturn(mockPrivateKey).when(generator).parsePrivateKey(anyString(), anyString());

        // When
        when(Signature.getInstance("SHA256withRSA")).thenReturn(mockSignature);
        doThrow(new Exception("Mock Exception")).when(mockSignature).initSign(mockPrivateKey);

        generator.getSignature(data);

        // Then
        // Expecting RuntimeException to be thrown
    }
}
