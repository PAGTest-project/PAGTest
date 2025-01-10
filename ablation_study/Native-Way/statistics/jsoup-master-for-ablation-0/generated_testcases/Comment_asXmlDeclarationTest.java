
package org.jsoup.nodes;

import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Comment_asXmlDeclarationTest {
    private Comment comment;
    private Comment decl;

    @BeforeEach
    public void setUp() {
        comment = new Comment("This is a comment");
        decl = new Comment("?xml version=\"1.0\" encoding=\"UTF-8\"?");
    }

    @Test
    public void testAsXmlDeclarationValid() {
        XmlDeclaration xmlDecl = decl.asXmlDeclaration();
        assertNotNull(xmlDecl);
        assertEquals("xml", xmlDecl.name());
    }

    @Test
    public void testAsXmlDeclarationInvalid() {
        XmlDeclaration xmlDecl = comment.asXmlDeclaration();
        assertNull(xmlDecl);
    }

    @Test
    public void testAsXmlDeclarationBogus() {
        Comment bogusDecl = new Comment("!bogus");
        XmlDeclaration xmlDecl = bogusDecl.asXmlDeclaration();
        assertNull(xmlDecl);
    }

    @Test
    public void testAsXmlDeclarationEmpty() {
        Comment emptyDecl = new Comment("");
        XmlDeclaration xmlDecl = emptyDecl.asXmlDeclaration();
        assertNull(xmlDecl);
    }

    @Test
    public void testAsXmlDeclarationRecursive() {
        Comment recursiveDecl = new Comment("?xml?xml");
        XmlDeclaration xmlDecl = recursiveDecl.asXmlDeclaration();
        assertNull(xmlDecl);
    }
}
