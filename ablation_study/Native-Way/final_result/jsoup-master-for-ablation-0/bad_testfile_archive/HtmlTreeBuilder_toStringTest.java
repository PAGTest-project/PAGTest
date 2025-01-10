
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtmlTreeBuilder_toStringTest {
    private HtmlTreeBuilder htmlTreeBuilder;

    @BeforeEach
    public void setUp() {
        htmlTreeBuilder = new HtmlTreeBuilder();
    }

    @Test
    public void testToStringWithDefaultValues() {
        String expected = "TreeBuilder{currentToken=null, state=null, currentElement=null}";
        assertEquals(expected, htmlTreeBuilder.toString());
    }

    @Test
    public void testToStringWithNonDefaultValues() {
        // Assuming Token, HtmlTreeBuilderState, and Element are mockable or have default constructors
        Token mockToken = new Token();
        HtmlTreeBuilderState mockState = new HtmlTreeBuilderState();
        Element mockElement = new Element("div");

        // Assuming setters or direct field access for testing purposes
        htmlTreeBuilder.currentToken = mockToken;
        htmlTreeBuilder.state = mockState;
        htmlTreeBuilder.stack.add(mockElement); // Assuming stack is a list of elements

        String expected = "TreeBuilder{currentToken=" + mockToken + ", state=" + mockState + ", currentElement=" + mockElement + "}";
        assertEquals(expected, htmlTreeBuilder.toString());
    }
}
