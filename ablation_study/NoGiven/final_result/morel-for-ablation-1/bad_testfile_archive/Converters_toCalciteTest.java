
package net.hydromatic.morel.foreign;

import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeFactoryImpl;
import org.apache.calcite.sql.type.SqlTypeName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.function.Function;

public class Converters_toCalciteTest {

    @Test
    public void testToCalcite() {
        // Given
        Type type = PrimitiveType.INT;
        RelDataTypeFactory typeFactory = new RelDataTypeFactoryImpl();

        // When
        Function<Object, Object> converter = Converters.toCalcite(type, typeFactory);

        // Then
        Object result = converter.apply(42);
        assertEquals(42, result);
    }
}
