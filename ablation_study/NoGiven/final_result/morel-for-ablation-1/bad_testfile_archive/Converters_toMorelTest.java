
package net.hydromatic.morel.foreign;

import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeFactoryImpl;
import org.junit.jupiter.api.Test;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.Type;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Converters_toMorelTest {

    @Test
    public void testToMorel() {
        // Given
        Type type = PrimitiveType.INT;
        RelDataTypeFactory typeFactory = new RelDataTypeFactoryImpl();

        // When
        Function<Object, Object> converter = Converters.toMorel(type, typeFactory);

        // Then
        assertNotNull(converter);
    }
}
