
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IntegerColumnHandler_applyTest {

    @Test
    public void testApply_Success() throws SQLException {
        // Given
        Integer expectedValue = 42;
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt(anyInt())).thenReturn(expectedValue);

        IntegerColumnHandler handler = new IntegerColumnHandler();

        // When
        Integer actualValue = handler.apply(mockResultSet, 1);

        // Then
        assertEquals(expectedValue, actualValue);
    }

    @Test(expected = SQLException.class)
    public void testApply_SQLException() throws SQLException {
        // Given
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt(anyInt())).thenThrow(new SQLException());

        IntegerColumnHandler handler = new IntegerColumnHandler();

        // When
        handler.apply(mockResultSet, 1);

        // Then (exception is expected)
    }
}
