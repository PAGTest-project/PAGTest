
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
public class ByteColumnHandler_applyTest {

    private ByteColumnHandler byteColumnHandler;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        byteColumnHandler = new ByteColumnHandler();
    }

    @Test
    public void testApplyReturnsByteValue() throws SQLException {
        when(resultSet.getByte(1)).thenReturn((byte) 42);
        Byte result = byteColumnHandler.apply(resultSet, 1);
        assertEquals(Byte.valueOf((byte) 42), result);
    }
}
