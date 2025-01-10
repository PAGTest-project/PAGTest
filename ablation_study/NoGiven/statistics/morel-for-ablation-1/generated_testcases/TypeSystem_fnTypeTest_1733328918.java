
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeSystem_fnTypeTest {

    @Test
    void testFnType_SingleType() {
        TypeSystem typeSystem = new TypeSystem();
        Type paramType = new Type() {
            @Override
            public Key key() {
                return new Key() {
                    @Override
                    public Type toType(TypeSystem typeSystem) {
                        return this;
                    }
                };
            }
        };
        Type type1 = paramType;
        Type type2 = paramType;

        Type result = typeSystem.fnType(paramType, type1, type2);
        assertNotNull(result);
    }

    @Test
    void testFnType_MultipleTypes() {
        TypeSystem typeSystem = new TypeSystem();
        Type paramType = new Type() {
            @Override
            public Key key() {
                return new Key() {
                    @Override
                    public Type toType(TypeSystem typeSystem) {
                        return this;
                    }
                };
            }
        };
        Type type1 = paramType;
        Type type2 = paramType;
        Type type3 = paramType;

        Type result = typeSystem.fnType(paramType, type1, type2, type3);
        assertNotNull(result);
    }

    @Test
    void testFnType_NullHandling() {
        TypeSystem typeSystem = new TypeSystem();
        Type paramType = new Type() {
            @Override
            public Key key() {
                return new Key() {
                    @Override
                    public Type toType(TypeSystem typeSystem) {
                        return this;
                    }
                };
            }
        };
        Type type1 = null;
        Type type2 = paramType;

        Type result = typeSystem.fnType(paramType, type1, type2);
        assertNotNull(result);
    }
}
