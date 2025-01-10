
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_orderTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
    }

    @Test
    public void testOrderWithEmptyOrderItems() {
        List<Core.OrderItem> emptyOrderItems = Arrays.asList();
        FromBuilder result = fromBuilder.order(emptyOrderItems);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testOrderWithNonEmptyOrderItems() {
        List<Core.OrderItem> orderItems = Arrays.asList(
            new Core.OrderItem(core.id(new Core.IdPat("id1")), true),
            new Core.OrderItem(core.id(new Core.IdPat("id2")), false)
        );
        FromBuilder result = fromBuilder.order(orderItems);
        assertEquals(fromBuilder, result);
    }
}
