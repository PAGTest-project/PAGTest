
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_orderTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;
    private Core core;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        core = new Core();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
    }

    @Test
    public void testOrderWithEmptyOrderItems() {
        FromBuilder result = fromBuilder.order(Collections.emptyList());
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testOrderWithNonEmptyOrderItems() {
        Core.OrderItem orderItem = new Core.OrderItem(core.id(new Core.IdPat(typeSystem.getType("int"), "id", 0)), true);
        FromBuilder result = fromBuilder.order(Collections.singletonList(orderItem));
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testOrderAndBuildSimplify() {
        Core.OrderItem orderItem = new Core.OrderItem(core.id(new Core.IdPat(typeSystem.getType("int"), "id", 0)), true);
        fromBuilder.order(Collections.singletonList(orderItem));
        Core.Exp result = fromBuilder.buildSimplify();
        assertEquals(core.from(typeSystem, Collections.singletonList(core.order(fromBuilder.bindings(), Collections.singletonList(orderItem)))), result);
    }

    @Test
    public void testOrderAndBindings() {
        Core.OrderItem orderItem = new Core.OrderItem(core.id(new Core.IdPat(typeSystem.getType("int"), "id", 0)), true);
        fromBuilder.order(Collections.singletonList(orderItem));
        List<Binding> bindings = fromBuilder.bindings();
        assertEquals(1, bindings.size());
    }
}
