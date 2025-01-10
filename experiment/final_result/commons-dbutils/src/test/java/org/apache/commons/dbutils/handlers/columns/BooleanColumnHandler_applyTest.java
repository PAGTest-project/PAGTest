
package org.apache.commons.dbutils.handlers.columns;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BooleanColumnHandler_applyTest {

    private BooleanColumnHandler booleanColumnHandler;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        booleanColumnHandler = new BooleanColumnHandler();
    }

    @Test
    public void testApplyReturnsTrue() throws SQLException {
        when(resultSet.getBoolean(1)).thenReturn(true);
        assertEquals(Boolean.TRUE, booleanColumnHandler.apply(resultSet, 1));
    }

    @Test
    public void testApplyReturnsFalse() throws SQLException {
        when(resultSet.getBoolean(1)).thenReturn(false);
        assertEquals(Boolean.FALSE, booleanColumnHandler.apply(resultSet, 1));
    }
}
