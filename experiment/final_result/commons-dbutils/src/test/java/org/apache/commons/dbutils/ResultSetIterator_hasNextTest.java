
package org.apache.commons.dbutils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class ResultSetIterator_hasNextTest {

    private ResultSet resultSet;
    private ResultSetIterator resultSetIterator;

    @Before
    public void setUp() throws SQLException {
        resultSet = mock(ResultSet.class);
        resultSetIterator = new ResultSetIterator(resultSet);
    }

    @Test
    public void testHasNextReturnsTrueWhenNotLast() throws SQLException {
        when(resultSet.isLast()).thenReturn(false);
        assertTrue(resultSetIterator.hasNext());
    }

    @Test
    public void testHasNextReturnsFalseWhenLast() throws SQLException {
        when(resultSet.isLast()).thenReturn(true);
        assertFalse(resultSetIterator.hasNext());
    }

    @Test(expected = RuntimeException.class)
    public void testHasNextThrowsRuntimeExceptionOnSQLException() throws SQLException {
        when(resultSet.isLast()).thenThrow(new SQLException("Mocked SQLException"));
        resultSetIterator.hasNext();
    }
}
