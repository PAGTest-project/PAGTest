
package net.datafaker.transformations.sql;

import net.datafaker.transformations.Field;
import net.datafaker.transformations.Schema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SqlTransformer_applyTest {

    @Test
    public void testApply_NoFields() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>()
                .build();
        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field[0]);

        CharSequence result = transformer.apply(new Object(), schema, 0);
        assertEquals("", result);
    }

    @Test
    public void testApply_BatchMode_FirstRow() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>()
                .batch(2)
                .build();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ? extends CharSequence>[] fields = new Field[1];
        when(schema.getFields()).thenReturn((Field<Object, ? extends CharSequence>[]) fields);

        CharSequence result = transformer.apply(new Object(), schema, 0);
        assertEquals("FIRST_ROW", result); // Assuming SqlDialect.getFirstRow returns "FIRST_ROW"
    }

    @Test
    public void testApply_BatchMode_OtherRow() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>()
                .batch(2)
                .build();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ? extends CharSequence>[] fields = new Field[1];
        when(schema.getFields()).thenReturn((Field<Object, ? extends CharSequence>[]) fields);

        CharSequence result = transformer.apply(new Object(), schema, 1);
        assertEquals("OTHER_ROW", result); // Assuming SqlDialect.getOtherRow returns "OTHER_ROW"
    }

    @Test
    public void testApply_NonBatchMode() {
        SqlTransformer<Object> transformer = new SqlTransformer.SqlTransformerBuilder<Object>()
                .build();
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ? extends CharSequence>[] fields = new Field[1];
        when(schema.getFields()).thenReturn((Field<Object, ? extends CharSequence>[]) fields);

        CharSequence result = transformer.apply(new Object(), schema, 0);
        assertEquals("INSERT INTO TABLE_INFO VALUES VALUES_INFO", result); // Assuming appendTableInfo and addValues return "TABLE_INFO" and "VALUES_INFO" respectively
    }
}
