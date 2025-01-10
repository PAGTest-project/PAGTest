
package net.datafaker.transformations.sql;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SqlTransformer_applyTest {

    @Test
    void testApply_NoFields() {
        SqlTransformer<Object> transformer = new SqlTransformer<>(null, "MyTable", '\'', null, "\"\"", Casing.TO_UPPER, false, -1, Case.UPPERCASE, false);
        Schema<Object, ?> schema = mock(Schema.class);
        when(schema.getFields()).thenReturn(new Field<?, ?>[0]);

        CharSequence result = transformer.apply(new Object(), schema, 0);
        assertEquals("", result);
    }

    @Test
    void testApply_BatchMode_FirstRow() {
        SqlTransformer<Object> transformer = new SqlTransformer<>(null, "MyTable", '\'', null, "\"\"", Casing.TO_UPPER, true, 10, Case.UPPERCASE, false);
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?> field = mock(Field.class);
        when(field.getName()).thenReturn("field1");
        when(schema.getFields()).thenReturn(new Field[]{field});

        CharSequence result = transformer.apply(new Object(), schema, 0);
        assertEquals("INSERT INTO MyTable (field1) VALUES ()", result);
    }

    @Test
    void testApply_BatchMode_OtherRow() {
        SqlTransformer<Object> transformer = new SqlTransformer<>(null, "MyTable", '\'', null, "\"\"", Casing.TO_UPPER, true, 10, Case.UPPERCASE, false);
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?> field = mock(Field.class);
        when(field.getName()).thenReturn("field1");
        when(schema.getFields()).thenReturn(new Field[]{field});

        CharSequence result = transformer.apply(new Object(), schema, 1);
        assertEquals(", (field1) VALUES ()", result);
    }

    @Test
    void testApply_NonBatchMode() {
        SqlTransformer<Object> transformer = new SqlTransformer<>(null, "MyTable", '\'', null, "\"\"", Casing.TO_UPPER, false, -1, Case.UPPERCASE, false);
        Schema<Object, ?> schema = mock(Schema.class);
        Field<?, ?> field = mock(Field.class);
        when(field.getName()).thenReturn("field1");
        when(schema.getFields()).thenReturn(new Field[]{field});

        CharSequence result = transformer.apply(new Object(), schema, 0);
        assertEquals("INSERT INTO MyTable (field1) VALUES ()", result);
    }
}
