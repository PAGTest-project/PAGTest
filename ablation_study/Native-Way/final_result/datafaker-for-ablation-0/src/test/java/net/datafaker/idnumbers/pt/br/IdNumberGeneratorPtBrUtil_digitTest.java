
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdNumberGeneratorPtBrUtil_digitTest {

    @Test
    public void testDigitWithRemainderZero() {
        assertEquals(0, IdNumberGeneratorPtBrUtil.digit(0));
    }

    @Test
    public void testDigitWithRemainderOne() {
        assertEquals(0, IdNumberGeneratorPtBrUtil.digit(1));
    }

    @Test
    public void testDigitWithRemainderGreaterThanOne() {
        assertEquals(9, IdNumberGeneratorPtBrUtil.digit(2));
        assertEquals(8, IdNumberGeneratorPtBrUtil.digit(3));
        assertEquals(7, IdNumberGeneratorPtBrUtil.digit(4));
        assertEquals(6, IdNumberGeneratorPtBrUtil.digit(5));
        assertEquals(5, IdNumberGeneratorPtBrUtil.digit(6));
        assertEquals(4, IdNumberGeneratorPtBrUtil.digit(7));
        assertEquals(3, IdNumberGeneratorPtBrUtil.digit(8));
        assertEquals(2, IdNumberGeneratorPtBrUtil.digit(9));
        assertEquals(1, IdNumberGeneratorPtBrUtil.digit(10));
    }
}
