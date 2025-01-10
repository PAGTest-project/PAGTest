
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
public class DoubleColumnHandler_applyTest {

    private DoubleColumnHandler doubleColumnHandler;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        doubleColumnHandler = new DoubleColumnHandler();
    }

    @Test
    public void testApply() throws SQLException {
        when(resultSet.getDouble(1)).thenReturn(123.456);
        Double result = doubleColumnHandler.apply(resultSet, 1);
        assertEquals(Double.valueOf(123.456), result);
    }
}
