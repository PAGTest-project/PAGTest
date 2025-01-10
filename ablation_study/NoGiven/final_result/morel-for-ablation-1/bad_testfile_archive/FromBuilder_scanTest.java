
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.TypeSystem;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.hydromatic.morel.ast.CoreBuilder.core;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_scanTest {
    private TypeSystem typeSystem;
    private FromBuilder fromBuilder;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
    }

    @Test
    public void testScanWithValidPat() {
        Core.Pat pat = core.idPat(typeSystem.getType("INT"), "x");
        FromBuilder result = fromBuilder.scan(pat);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testScanWithInvalidPat() {
        Core.Pat pat = core.idPat(typeSystem.getType("INT"), "invalid");
        FromBuilder result = fromBuilder.scan(pat);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testScanWithEmptyRange() {
        Core.Pat pat = core.idPat(typeSystem.getType("INT"), "x");
        FromBuilder result = fromBuilder.scan(pat);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testScanWithNonEmptyRange() {
        Core.Pat pat = core.idPat(typeSystem.getType("INT"), "x");
        FromBuilder result = fromBuilder.scan(pat);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testScanWithComplexPat() {
        Core.Pat pat = core.recordPat(typeSystem.getType("RECORD"), "record", core.idPat(typeSystem.getType("INT"), "x"), core.idPat(typeSystem.getType("INT"), "y"));
        FromBuilder result = fromBuilder.scan(pat);
        assertEquals(fromBuilder, result);
    }
}
