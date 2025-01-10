
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TimestampColumnHandler_applyTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void testApply_ReturnsTimestamp() throws SQLException {
        // Given
        Timestamp expectedTimestamp = new Timestamp(System.currentTimeMillis());
        when(resultSet.getTimestamp(1)).thenReturn(expectedTimestamp);

        TimestampColumnHandler handler = new TimestampColumnHandler();

        // When
        Timestamp actualTimestamp = handler.apply(resultSet, 1);

        // Then
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test(expected = SQLException.class)
    public void testApply_ThrowsSQLException() throws SQLException {
        // Given
        when(resultSet.getTimestamp(1)).thenThrow(new SQLException());

        TimestampColumnHandler handler = new TimestampColumnHandler();

        // When
        handler.apply(resultSet, 1);

        // Then (exception is expected)
    }
}
