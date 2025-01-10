
package org.ice4j.attribute;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ErrorCodeAttribute_getDefaultReasonPhraseTest {

    @Test
    public void testGetDefaultReasonPhrase_TryAlternate() {
        char errorCode = ErrorCodeAttribute.TRY_ALTERNATE;
        String expected = "(Try Alternate): The server would like the client to"
                + " use the server specified in the ALTERNATE-SERVER"
                + " attribute instead.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_BadRequest() {
        char errorCode = ErrorCodeAttribute.BAD_REQUEST;
        String expected = "(Bad Request): The request was malformed.  The client"
                + " should not retry the request without modification from"
                + " the previous attempt.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_Unauthorized() {
        char errorCode = ErrorCodeAttribute.UNAUTHORIZED;
        String expected = "(Unauthorized): The Binding Request did not contain"
                + " a MESSAGE-INTEGRITY attribute.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_Forbidden() {
        char errorCode = ErrorCodeAttribute.FORBIDDEN;
        String expected = "(Forbidden): The request was valid but cannot be"
                + " performed due to administrative or similar"
                + " restrictions.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_UnknownAttribute() {
        char errorCode = ErrorCodeAttribute.UNKNOWN_ATTRIBUTE;
        String expected = "(Unknown Attribute): The server did not understand"
                + " a mandatory attribute in the request.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_StaleCredentials() {
        char errorCode = ErrorCodeAttribute.STALE_CREDENTIALS;
        String expected = "(Stale Credentials): The Binding Request did contain"
                + " a MESSAGE-INTEGRITY attribute, but it used a shared"
                + " secret that has expired.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_IntegrityCheckFailure() {
        char errorCode = ErrorCodeAttribute.INTEGRITY_CHECK_FAILURE;
        String expected = "(Integrity Check Failure): The Binding Request"
                + " contained a MESSAGE-INTEGRITY attribute, but the HMAC"
                + " failed verification.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_MissingUsername() {
        char errorCode = ErrorCodeAttribute.MISSING_USERNAME;
        String expected = "(Missing Username): The Binding Request contained"
                + " a MESSAGE-INTEGRITY attribute, but not a USERNAME"
                + " attribute.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_UseTLS() {
        char errorCode = ErrorCodeAttribute.USE_TLS;
        String expected = "(Use TLS): The Shared Secret request has to be sent"
                + " over TLS, but was not received over TLS.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_AllocationMismatch() {
        char errorCode = ErrorCodeAttribute.ALLOCATION_MISMATCH;
        String expected = "(Allocation Mismatch): A request was received by the"
                + " server that requires an allocation to be in place,"
                + " but no allocation exists, or a request was received"
                + " that requires no allocation, but an allocation exists.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_StaleNonce() {
        char errorCode = ErrorCodeAttribute.STALE_NONCE;
        String expected = "(Stale Nonce): See the procedures for the long-term"
                + " credential mechanism.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_AddressFamilyNotSupported() {
        char errorCode = ErrorCodeAttribute.ADDRESS_FAMILY_NOT_SUPPORTED;
        String expected = "(Address Family not Supported):  The server does not"
                + " support the address family requested by the client.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_WrongCredentials() {
        char errorCode = ErrorCodeAttribute.WRONG_CREDENTIALS;
        String expected = "(Wrong Credentials): The credentials in the"
                + " (non-Allocate) request do not match those used"
                + " to create the allocation.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_UnsupportedTransportProtocol() {
        char errorCode = ErrorCodeAttribute.UNSUPPORTED_TRANSPORT_PROTOCOL;
        String expected = "(Unsupported Transport Protocol): The Allocate request"
                + " asked the server to use a transport protocol between"
                + " the server and the peer that the server does not"
                + " support.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_PeerAddressFamilyMismatch() {
        char errorCode = ErrorCodeAttribute.PEER_ADDRESS_FAMILY_MISMATCH;
        String expected = "Peer Address Family Mismatch):  A peer address was of"
                + " a different address family than that of the relayed"
                + " transport address of the allocation.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_ConnectionAlreadyExists() {
        char errorCode = ErrorCodeAttribute.CONNECTION_ALREADY_EXISTS;
        String expected = "Connection Already Exists";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_ConnectionTimeoutOrFailure() {
        char errorCode = ErrorCodeAttribute.CONNECTION_TIMEOUT_OR_FAILURE;
        String expected = "Connection Timeout or Failure";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_AllocationQuotaReached() {
        char errorCode = ErrorCodeAttribute.ALLOCATION_QUOTA_REACHED;
        String expected = "(Allocation Quota Reached): No more allocations using"
                + " this username can be created at the present time.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_ServerError() {
        char errorCode = ErrorCodeAttribute.SERVER_ERROR;
        String expected = "(Server Error): The server has suffered a temporary"
                + " error. The client should try again.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_InsufficientCapacity() {
        char errorCode = ErrorCodeAttribute.INSUFFICIENT_CAPACITY;
        String expected = "(Insufficient Capacity): The server is unable to carry"
                + " out the request due to some capacity limit being"
                + " reached.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_GlobalFailure() {
        char errorCode = ErrorCodeAttribute.GLOBAL_FAILURE;
        String expected = "(Global Failure:) The server is refusing to fulfill"
                + " the request. The client should not retry.";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }

    @Test
    public void testGetDefaultReasonPhrase_UnknownError() {
        char errorCode = 999; // An unknown error code
        String expected = "Unknown Error";
        assertEquals(expected, ErrorCodeAttribute.getDefaultReasonPhrase(errorCode));
    }
}
