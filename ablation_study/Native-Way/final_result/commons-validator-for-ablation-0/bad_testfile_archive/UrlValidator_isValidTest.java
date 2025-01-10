
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlValidator_isValidTest {
    private UrlValidator urlValidator;

    @BeforeEach
    public void setUp() {
        urlValidator = new UrlValidator();
    }

    @Test
    public void testIsValid_Null() {
        assertFalse(urlValidator.isValid(null), "Null value should be invalid");
    }

    @Test
    public void testIsValid_InvalidScheme() {
        assertFalse(urlValidator.isValid("invalid://2001:db8:85a3:0:0:8a2e:370:7334"), "Invalid scheme should be invalid");
    }

    @Test
    public void testIsValid_ValidScheme() {
        assertTrue(urlValidator.isValid("http://2001:db8:85a3:0:0:8a2e:370:7334"), "Valid scheme should be valid");
    }

    @Test
    public void testIsValid_EmptyAuthority() {
        assertTrue(urlValidator.isValid("file:///path/to/file"), "Empty authority for file scheme should be valid");
    }

    @Test
    public void testIsValid_ColonInAuthority() {
        assertFalse(urlValidator.isValid("file://user:pass@host:port/path"), "Colon in authority for file scheme should be invalid");
    }

    @Test
    public void testIsValid_InvalidAuthority() {
        assertFalse(urlValidator.isValid("http://invalid-authority"), "Invalid authority should be invalid");
    }

    @Test
    public void testIsValid_InvalidPath() {
        assertFalse(urlValidator.isValid("http://example.com/invalid/path/.."), "Invalid path should be invalid");
    }

    @Test
    public void testIsValid_InvalidQuery() {
        assertFalse(urlValidator.isValid("http://example.com/?invalid query"), "Invalid query should be invalid");
    }

    @Test
    public void testIsValid_InvalidFragment() {
        assertFalse(urlValidator.isValid("http://example.com/#invalid fragment"), "Invalid fragment should be invalid");
    }

    @Test
    public void testIsValid_ValidUrl() {
        assertTrue(urlValidator.isValid("http://example.com"), "Valid URL should be valid");
    }

    @Test
    public void testIsValid_ValidUrlWithPath() {
        assertTrue(urlValidator.isValid("http://example.com/path/to/resource"), "Valid URL with path should be valid");
    }

    @Test
    public void testIsValid_ValidUrlWithQuery() {
        assertTrue(urlValidator.isValid("http://example.com/?query=param"), "Valid URL with query should be valid");
    }

    @Test
    public void testIsValid_ValidUrlWithFragment() {
        assertTrue(urlValidator.isValid("http://example.com/#fragment"), "Valid URL with fragment should be valid");
    }

    @Test
    public void testIsValid_ValidUrlWithAllComponents() {
        assertTrue(urlValidator.isValid("http://user:pass@example.com:8080/path/to/resource?query=param#fragment"), "Valid URL with all components should be valid");
    }

    @Test
    public void testIsValid_ValidIPv6Url() {
        assertTrue(urlValidator.isValid("http://[2001:db8:85a3:0:0:8a2e:370:7334]"), "Valid IPv6 URL should be valid");
    }

    @Test
    public void testIsValid_ValidIPv6UrlWithPort() {
        assertTrue(urlValidator.isValid("http://[2001:db8:85a3:0:0:8a2e:370:7334]:8080"), "Valid IPv6 URL with port should be valid");
    }

    @Test
    public void testIsValid_ValidIPv6UrlWithPath() {
        assertTrue(urlValidator.isValid("http://[2001:db8:85a3:0:0:8a2e:370:7334]/path/to/resource"), "Valid IPv6 URL with path should be valid");
    }

    @Test
    public void testIsValid_ValidIPv6UrlWithQuery() {
        assertTrue(urlValidator.isValid("http://[2001:db8:85a3:0:0:8a2e:370:7334]?query=param"), "Valid IPv6 URL with query should be valid");
    }

    @Test
    public void testIsValid_ValidIPv6UrlWithFragment() {
        assertTrue(urlValidator.isValid("http://[2001:db8:85a3:0:0:8a2e:370:7334]#fragment"), "Valid IPv6 URL with fragment should be valid");
    }

    @Test
    public void testIsValid_ValidIPv6UrlWithAllComponents() {
        assertTrue(urlValidator.isValid("http://user:pass@[2001:db8:85a3:0:0:8a2e:370:7334]:8080/path/to/resource?query=param#fragment"), "Valid IPv6 URL with all components should be valid");
    }
}
