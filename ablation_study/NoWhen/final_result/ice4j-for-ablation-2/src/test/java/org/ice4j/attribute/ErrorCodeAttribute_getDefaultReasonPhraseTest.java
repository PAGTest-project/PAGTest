
package org.ice4j.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorCodeAttribute_getDefaultReasonPhraseTest {
    private ErrorCodeAttribute errorCodeAttribute;

    @BeforeEach
    public void setUp() {
        errorCodeAttribute = new ErrorCodeAttribute();
    }

    @Test
    public void testGetDefaultReasonPhraseTryAlternate() {
        assertEquals(
            "(Try Alternate): The server would like the client to"
            + " use the server specified in the ALTERNATE-SERVER"
            + " attribute instead.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.TRY_ALTERNATE)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseBadRequest() {
        assertEquals(
            "(Bad Request): The request was malformed.  The client"
            + " should not retry the request without modification from"
            + " the previous attempt.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.BAD_REQUEST)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseUnauthorized() {
        assertEquals(
            "(Unauthorized): The Binding Request did not contain"
            + " a MESSAGE-INTEGRITY attribute.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.UNAUTHORIZED)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseForbidden() {
        assertEquals(
            "(Forbidden): The request was valid but cannot be"
            + " performed due to administrative or similar"
            + " restrictions.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.FORBIDDEN)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseUnknownAttribute() {
        assertEquals(
            "(Unknown Attribute): The server did not understand"
            + " a mandatory attribute in the request.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.UNKNOWN_ATTRIBUTE)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseStaleCredentials() {
        assertEquals(
            "(Stale Credentials): The Binding Request did contain"
            + " a MESSAGE-INTEGRITY attribute, but it used a shared"
            + " secret that has expired.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.STALE_CREDENTIALS)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseIntegrityCheckFailure() {
        assertEquals(
            "(Integrity Check Failure): The Binding Request"
            + " contained a MESSAGE-INTEGRITY attribute, but the HMAC"
            + " failed verification.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.INTEGRITY_CHECK_FAILURE)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseMissingUsername() {
        assertEquals(
            "(Missing Username): The Binding Request contained"
            + " a MESSAGE-INTEGRITY attribute, but not a USERNAME"
            + " attribute.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.MISSING_USERNAME)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseUseTLS() {
        assertEquals(
            "(Use TLS): The Shared Secret request has to be sent"
            + " over TLS, but was not received over TLS.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.USE_TLS)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseAllocationMismatch() {
        assertEquals(
            "(Allocation Mismatch): A request was received by the"
            + " server that requires an allocation to be in place,"
            + " but no allocation exists, or a request was received"
            + " that requires no allocation, but an allocation exists.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.ALLOCATION_MISMATCH)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseStaleNonce() {
        assertEquals(
            "(Stale Nonce): See the procedures for the long-term"
            + " credential mechanism.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.STALE_NONCE)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseAddressFamilyNotSupported() {
        assertEquals(
            "(Address Family not Supported):  The server does not"
            + " support the address family requested by the client.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.ADDRESS_FAMILY_NOT_SUPPORTED)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseWrongCredentials() {
        assertEquals(
            "(Wrong Credentials): The credentials in the"
            + " (non-Allocate) request do not match those used"
            + " to create the allocation.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.WRONG_CREDENTIALS)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseUnsupportedTransportProtocol() {
        assertEquals(
            "(Unsupported Transport Protocol): The Allocate request"
            + " asked the server to use a transport protocol between"
            + " the server and the peer that the server does not"
            + " support.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.UNSUPPORTED_TRANSPORT_PROTOCOL)
        );
    }

    @Test
    public void testGetDefaultReasonPhrasePeerAddressFamilyMismatch() {
        assertEquals(
            "Peer Address Family Mismatch):  A peer address was of"
            + " a different address family than that of the relayed"
            + " transport address of the allocation.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.PEER_ADDRESS_FAMILY_MISMATCH)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseConnectionAlreadyExists() {
        assertEquals(
            "Connection Already Exists",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.CONNECTION_ALREADY_EXISTS)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseConnectionTimeoutOrFailure() {
        assertEquals(
            "Connection Timeout or Failure",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.CONNECTION_TIMEOUT_OR_FAILURE)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseAllocationQuotaReached() {
        assertEquals(
            "(Allocation Quota Reached): No more allocations using"
            + " this username can be created at the present time.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.ALLOCATION_QUOTA_REACHED)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseServerError() {
        assertEquals(
            "(Server Error): The server has suffered a temporary"
            + " error. The client should try again.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.SERVER_ERROR)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseInsufficientCapacity() {
        assertEquals(
            "(Insufficient Capacity): The server is unable to carry"
            + " out the request due to some capacity limit being"
            + " reached.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.INSUFFICIENT_CAPACITY)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseGlobalFailure() {
        assertEquals(
            "(Global Failure:) The server is refusing to fulfill"
            + " the request. The client should not retry.",
            ErrorCodeAttribute.getDefaultReasonPhrase(ErrorCodeAttribute.GLOBAL_FAILURE)
        );
    }

    @Test
    public void testGetDefaultReasonPhraseUnknownError() {
        assertEquals(
            "Unknown Error",
            ErrorCodeAttribute.getDefaultReasonPhrase((char)999)
        );
    }
}
