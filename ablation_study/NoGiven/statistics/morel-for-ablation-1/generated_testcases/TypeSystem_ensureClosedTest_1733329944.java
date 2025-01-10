
package net.hydromatic.morel.type;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TypeSystem_ensureClosedTest {

    @Test
    void testEnsureClosedWithNoVars() {
        TypeSystem ts = new TypeSystem();
        Type type = mock(Type.class);
        VariableCollector collector = mock(VariableCollector.class);
        when(collector.vars).thenReturn(new LinkedHashSet<>());
        when(type.accept(any(VariableCollector.class))).thenReturn(null);

        Type result = ts.ensureClosed(type);

        assertEquals(type, result);
    }

    @Test
    void testEnsureClosedWithVars() {
        TypeSystem ts = new TypeSystem();
        Type type = mock(Type.class);
        VariableCollector collector = mock(VariableCollector.class);
        TypeVar typeVar = mock(TypeVar.class);
        when(collector.vars).thenReturn(new LinkedHashSet<TypeVar>() {{ add(typeVar); }});
        when(type.accept(any(VariableCollector.class))).thenReturn(null);
        when(type.copy(eq(ts), any())).thenReturn(type);

        Type result = ts.ensureClosed(type);

        assertNotNull(result);
    }
}
