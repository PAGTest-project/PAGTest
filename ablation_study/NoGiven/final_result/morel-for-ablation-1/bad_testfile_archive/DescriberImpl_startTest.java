
package net.hydromatic.morel.eval;

import org.junit.jupiter.api.Test;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DescriberImpl_startTest {

    @Test
    void testStart() {
        DescriberImpl describer = new DescriberImpl();
        String name = "TestName";
        Consumer<Detail> consumer = detail -> detail.arg("key", "value");

        describer.start(name, consumer);

        assertEquals("TestName(key value)", describer.toString());
    }
}
