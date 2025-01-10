
package net.hydromatic.morel.ast;

import com.google.common.collect.ImmutableList;
import net.hydromatic.morel.compile.Environment;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertSame;

public class FromBuilder_orderTest {

    private FromBuilder fromBuilder;

    @BeforeEach
    public void setUp() {
        TypeSystem typeSystem = new TypeSystem();
        Environment env = Environment.create(); // Use a factory method or a concrete implementation
        fromBuilder = new FromBuilder(typeSystem, env);
    }

    @Test
    public void testOrderWithEmptyOrderItems() {
        // Given
        Iterable<Core.OrderItem> emptyOrderItems = Collections.emptyList();

        // When
        FromBuilder result = fromBuilder.order(emptyOrderItems);

        // Then
        assertSame(fromBuilder, result);
    }

    @Test
    public void testOrderWithNonEmptyOrderItems() {
        // Given
        Core.Exp exp = new Core.Exp() {}; // Provide a valid Exp instance
        Core.Ast.Direction direction = Core.Ast.Direction.ASC; // Provide a valid Direction instance
        Iterable<Core.OrderItem> nonEmptyOrderItems = ImmutableList.of(new Core.OrderItem(exp, direction));

        // When
        FromBuilder result = fromBuilder.order(nonEmptyOrderItems);

        // Then
        assertSame(fromBuilder, result);
    }
}
