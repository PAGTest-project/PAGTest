
package org.apache.commons.dbutils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

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
        when(mockResultSet.isLast()).thenReturn(false);
        when(mockResultSet.next()).thenReturn(true);
        resultSetIterator.next();

        resultSetIterator.remove();

        verify(mockResultSet).deleteRow();
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveThrowsSQLException() throws SQLException {
        when(mockResultSet.isLast()).thenReturn(false);
        when(mockResultSet.next()).thenReturn(true);
        resultSetIterator.next();

        doThrow(new SQLException("Mocked SQLException")).when(mockResultSet).deleteRow();

        resultSetIterator.remove();
    }
}
