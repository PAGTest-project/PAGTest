
package net.hydromatic.morel.type;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TypeVar_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        TypeVar typeVar = new TypeVar(0);
        assertTrue(typeVar.equals(typeVar));
    }

    @Test
    public void testEquals_DifferentInstancesSameOrdinal() {
        TypeVar typeVar1 = new TypeVar(0);
        TypeVar typeVar2 = new TypeVar(0);
        assertTrue(typeVar1.equals(typeVar2));
    }

    @Test
    public void testEquals_DifferentInstancesDifferentOrdinal() {
        TypeVar typeVar1 = new TypeVar(0);
        TypeVar typeVar2 = new TypeVar(1);
        assertFalse(typeVar1.equals(typeVar2));
    }

    @Test
    public void testEquals_DifferentClass() {
        TypeVar typeVar = new TypeVar(0);
        Object obj = new Object();
        assertFalse(typeVar.equals(obj));
    }
}
