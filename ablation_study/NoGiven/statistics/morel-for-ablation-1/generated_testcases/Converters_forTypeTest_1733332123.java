
package net.hydromatic.morel.foreign;

import net.hydromatic.morel.eval.Unit;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.RecordLikeType;
import net.hydromatic.morel.type.Type;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeField;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeImpl;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.sql.type.SqlTypeFactoryImpl;
import org.apache.calcite.sql.type.SqlTypeName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Converters_forTypeTest {

    @Test
    public void testForType_PrimitiveType() {
        RelDataTypeFactory typeFactory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
        RelDataType fromType = typeFactory.createSqlType(SqlTypeName.INTEGER);
        Type type = PrimitiveType.INT;

        Function<Object, Object> converter = Converters.forType(fromType, type);
        assertNotNull(converter);

        Object result = converter.apply(123);
        assertEquals(123, result);
    }

    @Test
    public void testForType_RecordLikeType() {
        RelDataTypeFactory typeFactory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
        RelDataType fromType = typeFactory.createSqlType(SqlTypeName.ROW);
        Type type = new RecordLikeType() {
            @Override
            public SortedMap<String, Type> argNameTypes() {
                return new TreeMap<>();
            }

            @Override
            public List<Type> argTypes() {
                return Collections.emptyList();
            }

            @Override
            public Type argType(int index) {
                return null;
            }

            @Override
            public TypedValue asTypedValue() {
                return null;
            }
        };

        Function<Object, Object> converter = Converters.forType(fromType, type);
        assertNotNull(converter);

        Object result = converter.apply(new Object[]{});
        assertNotNull(result);
    }

    @Test
    public void testForType_NullableType() {
        RelDataTypeFactory typeFactory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
        RelDataType fromType = typeFactory.createTypeWithNullability(
                typeFactory.createSqlType(SqlTypeName.INTEGER), true);
        Type type = PrimitiveType.INT;

        Function<Object, Object> converter = Converters.forType(fromType, type);
        assertNotNull(converter);

        Object result = converter.apply(null);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testForType_UnitType() {
        RelDataTypeFactory typeFactory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
        RelDataType fromType = typeFactory.createSqlType(SqlTypeName.INTEGER);
        Type type = PrimitiveType.UNIT;

        Function<Object, Object> converter = Converters.forType(fromType, type);
        assertNotNull(converter);

        Object result = converter.apply(123);
        assertEquals(Unit.INSTANCE, result);
    }
}
