
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

    private ResultSet resultSet;
    private ResultSetIterator iterator;

    @Before
    public void setUp() {
        resultSet = Mockito.mock(ResultSet.class);
        iterator = new ResultSetIterator(resultSet);
    }

    @Test
    public void testHasNext_ReturnsTrue_WhenNotLast() throws SQLException {
        when(resultSet.isLast()).thenReturn(false);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNext_ReturnsFalse_WhenLast() throws SQLException {
        when(resultSet.isLast()).thenReturn(true);
        assertFalse(iterator.hasNext());
    }

    @Test(expected = RuntimeException.class)
    public void testHasNext_ThrowsRuntimeException_OnSQLException() throws SQLException {
        when(resultSet.isLast()).thenThrow(new SQLException());
        iterator.hasNext();
    }
}
