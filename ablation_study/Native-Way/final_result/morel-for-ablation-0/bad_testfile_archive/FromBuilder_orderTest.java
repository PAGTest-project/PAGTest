
package net.hydromatic.morel.ast;

import com.google.common.collect.ImmutableList;
import net.hydromatic.morel.compile.Core;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_orderTest {

    @Test
    public void testOrderWithEmptyOrderItems() {
        FromBuilder fromBuilder = new FromBuilder(null, null);
        Iterable<Core.OrderItem> emptyOrderItems = ImmutableList.of();
        FromBuilder result = fromBuilder.order(emptyOrderItems);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testOrderWithNonEmptyOrderItems() {
        FromBuilder fromBuilder = new FromBuilder(null, null);
        Core.OrderItem orderItem = new Core.OrderItem(Core.OrderItem.Direction.ASC, new Core.Id("id"));
        Iterable<Core.OrderItem> nonEmptyOrderItems = ImmutableList.of(orderItem);
        FromBuilder result = fromBuilder.order(nonEmptyOrderItems);
        assertEquals(fromBuilder, result);
    }
}
