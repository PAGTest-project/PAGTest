
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_digitTest {

    @Test
    void testDigitWithRemainderZero() {
        assertEquals(0, IdNumberGeneratorPtBrUtil.digit(0));
    }

    @Test
    void testDigitWithRemainderOne() {
        assertEquals(0, IdNumberGeneratorPtBrUtil.digit(1));
    }

    @Test
    void testDigitWithRemainderGreaterThanOne() {
        assertEquals(1, IdNumberGeneratorPtBrUtil.digit(21));
        assertEquals(2, IdNumberGeneratorPtBrUtil.digit(22));
        assertEquals(3, IdNumberGeneratorPtBrUtil.digit(23));
    }

    @Test
    void testDigitWithLargeInput() {
        assertEquals(2, IdNumberGeneratorPtBrUtil.digit(20));
        assertEquals(3, IdNumberGeneratorPtBrUtil.digit(29));
    }
}
