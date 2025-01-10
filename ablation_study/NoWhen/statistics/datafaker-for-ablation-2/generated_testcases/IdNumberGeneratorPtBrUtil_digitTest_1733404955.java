
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
        assertEquals(10, IdNumberGeneratorPtBrUtil.digit(11));
        assertEquals(9, IdNumberGeneratorPtBrUtil.digit(12));
        assertEquals(8, IdNumberGeneratorPtBrUtil.digit(13));
    }

    @Test
    void testDigitWithLargeInput() {
        assertEquals(1, IdNumberGeneratorPtBrUtil.digit(20));
        assertEquals(2, IdNumberGeneratorPtBrUtil.digit(19));
    }
}
