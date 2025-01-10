
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LongColumnHandler_applyTest {

    private LongColumnHandler longColumnHandler;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        longColumnHandler = new LongColumnHandler();
    }

    @Test
    public void testApply() throws SQLException {
        when(resultSet.getLong(1)).thenReturn(123456789L);
        Long result = longColumnHandler.apply(resultSet, 1);
        assertEquals(123456789L, result.longValue());
    }
}
