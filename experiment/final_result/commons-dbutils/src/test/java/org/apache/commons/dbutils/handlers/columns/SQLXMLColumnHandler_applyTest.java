
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SQLXMLColumnHandler_applyTest {

    private SQLXMLColumnHandler columnHandler;

    @Mock
    private ResultSet resultSet;

    @Mock
    private SQLXML sqlxml;

    @Before
    public void setUp() {
        columnHandler = new SQLXMLColumnHandler();
    }

    @Test
    public void testApply() throws SQLException {
        when(resultSet.getSQLXML(1)).thenReturn(sqlxml);
        SQLXML result = columnHandler.apply(resultSet, 1);
        assertEquals(sqlxml, result);
    }
}
