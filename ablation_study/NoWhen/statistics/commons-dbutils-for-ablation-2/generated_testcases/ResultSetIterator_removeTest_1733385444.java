
package org.apache.commons.dbutils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetIterator_removeTest {

    private ResultSetIterator resultSetIterator;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        mockResultSet = mock(ResultSet.class);
        resultSetIterator = new ResultSetIterator(mockResultSet);
    }

    @Test
    public void testRemoveSuccess() throws SQLException {
        when(mockResultSet.deleteRow()).thenReturn(true);
        resultSetIterator.remove();
        verify(mockResultSet, times(1)).deleteRow();
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveThrowsSQLException() throws SQLException {
        when(mockResultSet.deleteRow()).thenThrow(new SQLException("Mocked SQL Exception"));
        resultSetIterator.remove();
    }
}
