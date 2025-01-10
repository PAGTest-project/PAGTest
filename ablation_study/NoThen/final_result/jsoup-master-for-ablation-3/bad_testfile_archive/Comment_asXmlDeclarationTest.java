
package org.jsoup.nodes;

import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Comment_asXmlDeclarationTest {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment("!DOCTYPE html");
    }

    @Test
    public void testAsXmlDeclarationValid() {
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNotNull(decl);
        assertEquals("!DOCTYPE", decl.name());
    }

    @Test
    public void testAsXmlDeclarationInvalid() {
        comment.setData("<!-- This is a comment -->");
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNull(decl);
    }

    @Test
    public void testAsXmlDeclarationEmpty() {
        comment.setData("");
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNull(decl);
    }

    @Test
    public void testAsXmlDeclarationMalformed() {
        comment.setData("!DOCTYPEhtml");
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNull(decl);
    }
}
