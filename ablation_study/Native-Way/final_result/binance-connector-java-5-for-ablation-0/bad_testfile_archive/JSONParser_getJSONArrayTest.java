
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class JSONParser_getJSONArrayTest {

    @Test
    public void testGetJSONArraySuccess() {
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        symbols.add("ETHUSDT");
        String key = "symbols";
        String expected = "[\"BTCUSDT\",\"ETHUSDT\"]";
        assertEquals(expected, JSONParser.getJSONArray(symbols, key));
    }

    @Test
    public void testGetJSONArrayThrowException() {
        ArrayList<Object> invalidSymbols = new ArrayList<>();
        invalidSymbols.add(new Object()); // Adding an invalid object to trigger JSONException
        String key = "invalidSymbols";
        assertThrows(JSONException.class, () -> JSONParser.getJSONArray(invalidSymbols, key));
    }
}
