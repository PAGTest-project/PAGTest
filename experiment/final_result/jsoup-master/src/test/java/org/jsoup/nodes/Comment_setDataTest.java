
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Comment_setDataTest {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment("initial data");
    }

    @Test
    public void testSetData() {
        String newData = "new data";
        Comment updatedComment = comment.setData(newData);
        assertEquals(newData, updatedComment.getData());
        assertSame(comment, updatedComment);
    }
}
