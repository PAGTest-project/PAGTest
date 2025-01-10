
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_removeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.add("Key1", "Val1");
        attributes.add("Key2", "Val2");
    }

    @Test
    public void testRemoveExistingKey() {
        assertTrue(attributes.hasKey("Key1"));
        assertEquals(2, attributes.size());

        attributes.remove("Key1");

        assertFalse(attributes.hasKey("Key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testRemoveNonExistingKey() {
        assertFalse(attributes.hasKey("Key3"));
        assertEquals(2, attributes.size());

        attributes.remove("Key3");

        assertFalse(attributes.hasKey("Key3"));
        assertEquals(2, attributes.size());
    }

    @Test
    public void testRemoveWithCheckCapacity() {
        attributes.add("Key3", "Val3");
        attributes.add("Key4", "Val4");
        attributes.add("Key5", "Val5");
        attributes.add("Key6", "Val6");
        attributes.add("Key7", "Val7");
        attributes.add("Key8", "Val8");
        attributes.add("Key9", "Val9");
        attributes.add("Key10", "Val10");
        attributes.add("Key11", "Val11");
        attributes.add("Key12", "Val12");
        attributes.add("Key13", "Val13");
        attributes.add("Key14", "Val14");
        attributes.add("Key15", "Val15");
        attributes.add("Key16", "Val16");
        attributes.add("Key17", "Val17");
        attributes.add("Key18", "Val18");
        attributes.add("Key19", "Val19");
        attributes.add("Key20", "Val20");
        attributes.add("Key21", "Val21");
        attributes.add("Key22", "Val22");
        attributes.add("Key23", "Val23");
        attributes.add("Key24", "Val24");
        attributes.add("Key25", "Val25");
        attributes.add("Key26", "Val26");
        attributes.add("Key27", "Val27");
        attributes.add("Key28", "Val28");
        attributes.add("Key29", "Val29");
        attributes.add("Key30", "Val30");
        attributes.add("Key31", "Val31");
        attributes.add("Key32", "Val32");
        attributes.add("Key33", "Val33");
        attributes.add("Key34", "Val34");
        attributes.add("Key35", "Val35");
        attributes.add("Key36", "Val36");
        attributes.add("Key37", "Val37");
        attributes.add("Key38", "Val38");
        attributes.add("Key39", "Val39");
        attributes.add("Key40", "Val40");
        attributes.add("Key41", "Val41");
        attributes.add("Key42", "Val42");
        attributes.add("Key43", "Val43");
        attributes.add("Key44", "Val44");
        attributes.add("Key45", "Val45");
        attributes.add("Key46", "Val46");
        attributes.add("Key47", "Val47");
        attributes.add("Key48", "Val48");
        attributes.add("Key49", "Val49");
        attributes.add("Key50", "Val50");
        attributes.add("Key51", "Val51");
        attributes.add("Key52", "Val52");
        attributes.add("Key53", "Val53");
        attributes.add("Key54", "Val54");
        attributes.add("Key55", "Val55");
        attributes.add("Key56", "Val56");
        attributes.add("Key57", "Val57");
        attributes.add("Key58", "Val58");
        attributes.add("Key59", "Val59");
        attributes.add("Key60", "Val60");
        attributes.add("Key61", "Val61");
        attributes.add("Key62", "Val62");
        attributes.add("Key63", "Val63");
        attributes.add("Key64", "Val64");
        attributes.add("Key65", "Val65");
        attributes.add("Key66", "Val66");
        attributes.add("Key67", "Val67");
        attributes.add("Key68", "Val68");
        attributes.add("Key69", "Val69");
        attributes.add("Key70", "Val70");
        attributes.add("Key71", "Val71");
        attributes.add("Key72", "Val72");
        attributes.add("Key73", "Val73");
        attributes.add("Key74", "Val74");
        attributes.add("Key75", "Val75");
        attributes.add("Key76", "Val76");
        attributes.add("Key77", "Val77");
        attributes.add("Key78", "Val78");
        attributes.add("Key79", "Val79");
        attributes.add("Key80", "Val80");
        attributes.add("Key81", "Val81");
        attributes.add("Key82", "Val82");
        attributes.add("Key83", "Val83");
        attributes.add("Key84", "Val84");
        attributes.add("Key85", "Val85");
        attributes.add("Key86", "Val86");
        attributes.add("Key87", "Val87");
        attributes.add("Key88", "Val88");
        attributes.add("Key89", "Val89");
        attributes.add("Key90", "Val90");
        attributes.add("Key91", "Val91");
        attributes.add("Key92", "Val92");
        attributes.add("Key93", "Val93");
        attributes.add("Key94", "Val94");
        attributes.add("Key95", "Val95");
        attributes.add("Key96", "Val96");
        attributes.add("Key97", "Val97");
        attributes.add("Key98", "Val98");
        attributes.add("Key99", "Val99");
        attributes.add("Key100", "Val100");

        assertTrue(attributes.hasKey("Key1"));
        assertEquals(100, attributes.size());

        attributes.remove("Key1");

        assertFalse(attributes.hasKey("Key1"));
        assertEquals(99, attributes.size());
    }
}
