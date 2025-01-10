
package org.openapitools.openapidiff.core.model.deferred;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.Changed;
import org.openapitools.openapidiff.core.model.ComposedChanged;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeferredLogger_valueToStringTest {

    @Test
    public void testValueToString_Null() {
        assertEquals("null", DeferredLogger.valueToString(null));
    }

    @Test
    public void testValueToString_Changed() {
        Changed changed = new ComposedChanged() {
            @Override
            public boolean isChanged() {
                return true;
            }

            @Override
            public boolean isCoreChanged() {
                return false;
            }
        };
        assertEquals("Changed: " + ComposedChanged.class + " (composed) ", DeferredLogger.valueToString(changed));
    }

    @Test
    public void testValueToString_Optional() {
        Optional<String> optional = Optional.of("test");
        assertEquals("Optional[test]", DeferredLogger.valueToString(optional));
    }

    @Test
    public void testValueToString_DeferredChanged() {
        DeferredChanged<String> deferredChanged = new DeferredChanged<String>() {
            @Override
            public boolean isValueSet() {
                return true;
            }

            @Override
            public boolean isPresent() {
                return true;
            }

            @Override
            public String get() {
                return "deferred";
            }

            @Override
            public String toString() {
                return "DeferredChanged";
            }

            @Override
            public <Q> DeferredChanged<Q> flatMap(java.util.function.Function<java.util.Optional<String>, DeferredChanged<Q>> mapper) {
                return null;
            }
        };
        assertEquals("deferred", DeferredLogger.valueToString(deferredChanged));
    }

    @Test
    public void testValueToString_Array() {
        Object[] array = {"a", "b", "c"};
        assertEquals("[a, b, c]", DeferredLogger.valueToString(array));
    }

    @Test
    public void testValueToString_Collection() {
        Collection<String> collection = Arrays.asList("x", "y", "z");
        assertEquals("[x, y, z]", DeferredLogger.valueToString(collection));
    }

    @Test
    public void testValueToString_Other() {
        assertEquals("other", DeferredLogger.valueToString("other"));
    }
}
