
package org.jsoup.helper;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class AuthenticationHandler_getPasswordAuthenticationTest {

    @Test
    void testGetPasswordAuthentication_NoDelegate() {
        AuthenticationHandler handler = new AuthenticationHandler();
        AuthenticationHandler.AuthShim mockHandler = mock(AuthenticationHandler.AuthShim.class);
        AuthenticationHandler.handler = mockHandler;
        when(mockHandler.get(handler)).thenReturn(null);

        PasswordAuthentication result = handler.getPasswordAuthentication();
        assertNull(result);
    }

    @Test
    void testGetPasswordAuthentication_MaxAttemptsExceeded() {
        AuthenticationHandler handler = new AuthenticationHandler();
        AuthenticationHandler delegate = new AuthenticationHandler();
        delegate.attemptCount = AuthenticationHandler.MaxAttempts + 1;
        AuthenticationHandler.AuthShim mockHandler = mock(AuthenticationHandler.AuthShim.class);
        AuthenticationHandler.handler = mockHandler;
        when(mockHandler.get(handler)).thenReturn(delegate);

        PasswordAuthentication result = handler.getPasswordAuthentication();
        assertNull(result);
    }

    @Test
    void testGetPasswordAuthentication_AuthNull() {
        AuthenticationHandler handler = new AuthenticationHandler();
        AuthenticationHandler delegate = new AuthenticationHandler();
        delegate.auth = null;
        AuthenticationHandler.AuthShim mockHandler = mock(AuthenticationHandler.AuthShim.class);
        AuthenticationHandler.handler = mockHandler;
        when(mockHandler.get(handler)).thenReturn(delegate);

        PasswordAuthentication result = handler.getPasswordAuthentication();
        assertNull(result);
    }

    @Test
    void testGetPasswordAuthentication_Success() {
        AuthenticationHandler handler = new AuthenticationHandler();
        AuthenticationHandler delegate = new AuthenticationHandler();
        RequestAuthenticator auth = mock(RequestAuthenticator.class);
        delegate.auth = auth;
        AuthenticationHandler.AuthShim mockHandler = mock(AuthenticationHandler.AuthShim.class);
        AuthenticationHandler.handler = mockHandler;
        when(mockHandler.get(handler)).thenReturn(delegate);
        when(auth.authenticate(any())).thenReturn(new PasswordAuthentication("user", "pass".toCharArray()));

        PasswordAuthentication result = handler.getPasswordAuthentication();
        assertNotNull(result);
    }
}
