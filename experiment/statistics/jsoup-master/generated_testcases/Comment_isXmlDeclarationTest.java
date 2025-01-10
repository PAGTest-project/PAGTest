
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Comment_isXmlDeclarationTest {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment("data");
    }

    @Test
    public void testIsXmlDeclarationTrue() {
        comment.setData("!xml version=\"1.0\" encoding=\"UTF-8\"");
        assertTrue(comment.isXmlDeclaration());
    }

    @Test
    public void testIsXmlDeclarationFalse() {
        comment.setData("This is a comment");
        assertFalse(comment.isXmlDeclaration());
    }
}
