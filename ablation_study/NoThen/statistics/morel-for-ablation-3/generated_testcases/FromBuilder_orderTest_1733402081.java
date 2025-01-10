
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.TypeSystem;
import com.google.common.collect.ImmutableList;
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
        Iterable<Core.OrderItem> emptyOrderItems = ImmutableList.of();
        FromBuilder result = fromBuilder.order(emptyOrderItems);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testOrderWithNonEmptyOrderItems() {
        Core.OrderItem orderItem1 = new Core.OrderItem(Core.IdPat.of("id1"), true);
        Core.OrderItem orderItem2 = new Core.OrderItem(Core.IdPat.of("id2"), false);
        Iterable<Core.OrderItem> orderItems = ImmutableList.of(orderItem1, orderItem2);
        FromBuilder result = fromBuilder.order(orderItems);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testOrderWithSingleOrderItem() {
        Core.OrderItem orderItem = new Core.OrderItem(Core.IdPat.of("id"), true);
        Iterable<Core.OrderItem> orderItems = ImmutableList.of(orderItem);
        FromBuilder result = fromBuilder.order(orderItems);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testOrderWithMultipleOrderItems() {
        Core.OrderItem orderItem1 = new Core.OrderItem(Core.IdPat.of("id1"), true);
        Core.OrderItem orderItem2 = new Core.OrderItem(Core.IdPat.of("id2"), false);
        Core.OrderItem orderItem3 = new Core.OrderItem(Core.IdPat.of("id3"), true);
        Iterable<Core.OrderItem> orderItems = ImmutableList.of(orderItem1, orderItem2, orderItem3);
        FromBuilder result = fromBuilder.order(orderItems);
        assertEquals(fromBuilder, result);
    }
}
