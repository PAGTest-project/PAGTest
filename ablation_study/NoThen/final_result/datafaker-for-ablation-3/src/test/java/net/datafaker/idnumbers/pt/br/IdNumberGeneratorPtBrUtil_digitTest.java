
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
    }

    @Test
    public void testDigitWithRemainderEleven() {
        assertEquals(0, IdNumberGeneratorPtBrUtil.digit(11));
    }

    @Test
    public void testDigitWithRemainderTwelve() {
        assertEquals(9, IdNumberGeneratorPtBrUtil.digit(2));
    }
}
