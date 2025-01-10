
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RsaSignatureGenerator_getSignatureTest {

    private RsaSignatureGenerator rsaSignatureGenerator;

    @Mock
    private RSAPrivateKey mockPrivateKey;

    @Mock
    private Signature mockSignature;

    @Before
    public void setUp() throws Exception {
        rsaSignatureGenerator = new RsaSignatureGenerator("dummyPrivateKey") {
            @Override
            protected RSAPrivateKey parsePrivateKey(String privateKeyPem, String password) throws Exception {
                return mockPrivateKey;
            }
        };
    }

    @Test
    public void testGetSignature_Success() throws Exception {
        String data = "testData";
        byte[] signatureBytes = "signature".getBytes();
        String expectedSignature = Base64.getEncoder().encodeToString(signatureBytes);

        when(Signature.getInstance(RsaSignatureGenerator.RSA_SHA256)).thenReturn(mockSignature);
        when(mockSignature.sign()).thenReturn(signatureBytes);

        String result = rsaSignatureGenerator.getSignature(data);

        verify(mockSignature).initSign(mockPrivateKey);
        verify(mockSignature).update(data.getBytes());
        assertEquals(expectedSignature, result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        String data = "testData";

        when(Signature.getInstance(RsaSignatureGenerator.RSA_SHA256)).thenReturn(mockSignature);
        doThrow(new Exception("Mock Exception")).when(mockSignature).initSign(mockPrivateKey);

        rsaSignatureGenerator.getSignature(data);
    }
}
