
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Comment_asXmlDeclarationTest {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment("<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->");
    }

    @Test
    public void testAsXmlDeclarationValid() {
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNotNull(decl);
        assertEquals("xml", decl.name());
        assertEquals("version=\"1.0\" encoding=\"UTF-8\"", decl.getWholeDeclaration());
    }

    @Test
    public void testAsXmlDeclarationInvalid() {
        comment.setData("<!--invalid-->");
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNull(decl);
    }

    @Test
    public void testAsXmlDeclarationEmpty() {
        comment.setData("<!---->");
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNull(decl);
    }

    @Test
    public void testAsXmlDeclarationRecursive() {
        comment.setData("<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->");
        XmlDeclaration decl = comment.asXmlDeclaration();
        assertNotNull(decl);
        assertEquals("xml", decl.name());
        assertEquals("version=\"1.0\" encoding=\"UTF-8\"", decl.getWholeDeclaration());
    }
}
