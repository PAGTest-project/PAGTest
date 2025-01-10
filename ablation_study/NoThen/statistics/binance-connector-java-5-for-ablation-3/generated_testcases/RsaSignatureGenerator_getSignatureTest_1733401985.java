
package com.binance.connector.client.utils.signaturegenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RsaSignatureGenerator_getSignatureTest {

    @Mock
    private RSAPrivateKey mockPrivateKey;

    @Mock
    private Signature mockSignature;

    private RsaSignatureGenerator rsaSignatureGenerator;

    @Before
    public void setUp() throws Exception {
        rsaSignatureGenerator = new RsaSignatureGenerator("dummyPrivateKey") {
            @Override
            RSAPrivateKey parsePrivateKey(String privateKeyPem, String password) {
                return mockPrivateKey;
            }
        };
    }

    @Test
    public void testGetSignature_Success() throws Exception {
        String data = "testData";
        byte[] mockSignatureBytes = "mockSignature".getBytes();
        String expectedSignature = Base64.getEncoder().encodeToString(mockSignatureBytes);

        Signature mockSignatureInstance = Mockito.mock(Signature.class);
        when(mockSignatureInstance.sign()).thenReturn(mockSignatureBytes);

        when(Signature.getInstance(RsaSignatureGenerator.RSA_SHA256)).thenReturn(mockSignatureInstance);
        when(mockSignatureInstance.sign()).thenReturn(mockSignatureBytes);

        String result = rsaSignatureGenerator.getSignature(data);

        assertEquals(expectedSignature, result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetSignature_Exception() throws Exception {
        String data = "testData";

        when(Signature.getInstance(RsaSignatureGenerator.RSA_SHA256)).thenThrow(new Exception("Mock Exception"));

        rsaSignatureGenerator.getSignature(data);
    }
}
