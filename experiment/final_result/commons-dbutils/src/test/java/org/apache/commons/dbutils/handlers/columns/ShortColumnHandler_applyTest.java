
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
public class ShortColumnHandler_applyTest {

    private ShortColumnHandler shortColumnHandler;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        shortColumnHandler = new ShortColumnHandler();
    }

    @Test
    public void testApply() throws SQLException {
        when(resultSet.getShort(1)).thenReturn((short) 123);
        Short result = shortColumnHandler.apply(resultSet, 1);
        assertEquals(Short.valueOf((short) 123), result);
    }
}
