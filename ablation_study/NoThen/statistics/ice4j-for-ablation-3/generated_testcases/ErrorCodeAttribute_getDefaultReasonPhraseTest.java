
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getDefaultReasonPhraseTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetDefaultReasonPhrase_TryAlternate() {
        String expected = "(Try Alternate): The server would like the client to use the server specified in the ALTERNATE-SERVER attribute instead.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.TRY_ALTERNATE);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_BadRequest() {
        String expected = "(Bad Request): The request was malformed. The client should not retry the request without modification from the previous attempt.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.BAD_REQUEST);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_Unauthorized() {
        String expected = "(Unauthorized): The Binding Request did not contain a MESSAGE-INTEGRITY attribute.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.UNAUTHORIZED);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_Forbidden() {
        String expected = "(Forbidden): The request was valid but cannot be performed due to administrative or similar restrictions.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.FORBIDDEN);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_UnknownAttribute() {
        String expected = "(Unknown Attribute): The server did not understand a mandatory attribute in the request.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.UNKNOWN_ATTRIBUTE);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_StaleCredentials() {
        String expected = "(Stale Credentials): The Binding Request did contain a MESSAGE-INTEGRITY attribute, but it used a shared secret that has expired.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.STALE_CREDENTIALS);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_IntegrityCheckFailure() {
        String expected = "(Integrity Check Failure): The Binding Request contained a MESSAGE-INTEGRITY attribute, but the HMAC failed verification.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.INTEGRITY_CHECK_FAILURE);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_MissingUsername() {
        String expected = "(Missing Username): The Binding Request contained a MESSAGE-INTEGRITY attribute, but not a USERNAME attribute.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.MISSING_USERNAME);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_UseTLS() {
        String expected = "(Use TLS): The Shared Secret request has to be sent over TLS, but was not received over TLS.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.USE_TLS);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_AllocationMismatch() {
        String expected = "(Allocation Mismatch): A request was received by the server that requires an allocation to be in place, but no allocation exists, or a request was received that requires no allocation, but an allocation exists.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.ALLOCATION_MISMATCH);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_StaleNonce() {
        String expected = "(Stale Nonce): See the procedures for the long-term credential mechanism.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.STALE_NONCE);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_AddressFamilyNotSupported() {
        String expected = "(Address Family not Supported): The server does not support the address family requested by the client.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.ADDRESS_FAMILY_NOT_SUPPORTED);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_WrongCredentials() {
        String expected = "(Wrong Credentials): The credentials in the (non-Allocate) request do not match those used to create the allocation.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.WRONG_CREDENTIALS);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_UnsupportedTransportProtocol() {
        String expected = "(Unsupported Transport Protocol): The Allocate request asked the server to use a transport protocol between the server and the peer that the server does not support.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.UNSUPPORTED_TRANSPORT_PROTOCOL);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_PeerAddressFamilyMismatch() {
        String expected = "(Peer Address Family Mismatch): A peer address was of a different address family than that of the relayed transport address of the allocation.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.PEER_ADDRESS_FAMILY_MISMATCH);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_ConnectionAlreadyExists() {
        String expected = "Connection Already Exists";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.CONNECTION_ALREADY_EXISTS);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_ConnectionTimeoutOrFailure() {
        String expected = "Connection Timeout or Failure";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.CONNECTION_TIMEOUT_OR_FAILURE);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_AllocationQuotaReached() {
        String expected = "(Allocation Quota Reached): No more allocations using this username can be created at the present time.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.ALLOCATION_QUOTA_REACHED);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_ServerError() {
        String expected = "(Server Error): The server has suffered a temporary error. The client should try again.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.SERVER_ERROR);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_InsufficientCapacity() {
        String expected = "(Insufficient Capacity): The server is unable to carry out the request due to some capacity limit being reached.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.INSUFFICIENT_CAPACITY);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_GlobalFailure() {
        String expected = "(Global Failure): The server is refusing to fulfill the request. The client should not retry.";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.GLOBAL_FAILURE);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultReasonPhrase_UnknownError() {
        String expected = "Unknown Error";
        String actual = ErrorCodeAttribute.getDefaultReasonPhrase((char) 999);
        assertEquals(expected, actual);
    }
}
