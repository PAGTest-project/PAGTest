
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FloatColumnHandler_applyTest {

    @Test
    public void testApply_Success() throws SQLException {
        // Given
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getFloat(1)).thenReturn(123.45f);
        FloatColumnHandler handler = new FloatColumnHandler();

        // When
        Float result = handler.apply(mockResultSet, 1);

        // Then
        assertEquals(Float.valueOf(123.45f), result);
    }

    @Test(expected = SQLException.class)
    public void testApply_SQLException() throws SQLException {
        // Given
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getFloat(1)).thenThrow(new SQLException());
        FloatColumnHandler handler = new FloatColumnHandler();

        // When
        handler.apply(mockResultSet, 1);

        // Then (expected exception)
    }
}
