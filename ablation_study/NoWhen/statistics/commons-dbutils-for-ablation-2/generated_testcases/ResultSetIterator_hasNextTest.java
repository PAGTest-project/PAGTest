
package org.apache.commons.dbutils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ResultSetIterator_hasNextTest {

    private ResultSet mockResultSet;
    private ResultSetIterator resultSetIterator;

    @Before
    public void setUp() {
        mockResultSet = Mockito.mock(ResultSet.class);
        resultSetIterator = new ResultSetIterator(mockResultSet);
    }

    @Test
    public void testHasNext_ReturnsTrue_WhenNotLast() throws SQLException {
        when(mockResultSet.isLast()).thenReturn(false);
        assertTrue(resultSetIterator.hasNext());
    }

    @Test
    public void testHasNext_ReturnsFalse_WhenLast() throws SQLException {
        when(mockResultSet.isLast()).thenReturn(true);
        assertFalse(resultSetIterator.hasNext());
    }

    @Test(expected = RuntimeException.class)
    public void testHasNext_ThrowsRuntimeException_OnSQLException() throws SQLException {
        when(mockResultSet.isLast()).thenThrow(new SQLException("Mocked SQL Exception"));
        resultSetIterator.hasNext();
    }
}
