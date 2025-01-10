
package org.apache.commons.dbutils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ResultSetIterator_removeTest {

    private ResultSet resultSet;
    private ResultSetIterator iterator;

    @Before
    public void setUp() throws SQLException {
        resultSet = mock(ResultSet.class);
        when(resultSet.getMetaData()).thenReturn(mock(java.sql.ResultSetMetaData.class));
        iterator = new ResultSetIterator(resultSet);
    }

    @Test
    public void testRemoveSuccess() throws SQLException {
        when(resultSet.isLast()).thenReturn(false);
        when(resultSet.next()).thenReturn(true);

        iterator.hasNext();
        iterator.next();
        iterator.remove();

        verify(resultSet, times(1)).deleteRow();
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveThrowsSQLException() throws SQLException {
        when(resultSet.isLast()).thenReturn(false);
        when(resultSet.next()).thenReturn(true);
        doThrow(new SQLException("Mocked SQLException")).when(resultSet).deleteRow();

        iterator.hasNext();
        iterator.next();
        iterator.remove();
    }
}
