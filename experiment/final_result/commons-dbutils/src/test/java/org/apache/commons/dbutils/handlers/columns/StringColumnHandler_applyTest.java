
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StringColumnHandler_applyTest {

    @Test
    public void testApply_ReturnsStringFromResultSet() throws SQLException {
        // Given
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getString(1)).thenReturn("testString");
        StringColumnHandler handler = new StringColumnHandler();

        // When
        String result = handler.apply(mockResultSet, 1);

        // Then
        assertEquals("testString", result);
    }

    @Test(expected = SQLException.class)
    public void testApply_ThrowsSQLException() throws SQLException {
        // Given
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getString(1)).thenThrow(new SQLException());
        StringColumnHandler handler = new StringColumnHandler();

        // When
        handler.apply(mockResultSet, 1);

        // Then (expected exception)
    }
}
