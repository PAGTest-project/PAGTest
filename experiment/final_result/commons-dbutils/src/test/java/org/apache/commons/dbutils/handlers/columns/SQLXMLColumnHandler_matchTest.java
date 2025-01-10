
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLXML;

public class SQLXMLColumnHandler_matchTest {

    private SQLXMLColumnHandler columnHandler;

    @Before
    public void setUp() {
        columnHandler = new SQLXMLColumnHandler();
    }

    @Test
    public void testMatchWithSQLXMLClass() {
        assertTrue(columnHandler.match(SQLXML.class));
    }

    @Test
    public void testMatchWithNonSQLXMLClass() {
        assertFalse(columnHandler.match(String.class));
    }
}
