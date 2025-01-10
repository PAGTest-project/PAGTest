
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_clearTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
    }

    @Test
    public void testClear() {
        // Given
        Core core = new Core();
        fromBuilder.scan(core.idPat("i"), core.list(core.intLiteral(1), core.intLiteral(2)));
        fromBuilder.yield_(core.id("i"));

        // When
        fromBuilder.clear();

        // Then
        assertEquals(0, fromBuilder.bindings().size());
    }
}
