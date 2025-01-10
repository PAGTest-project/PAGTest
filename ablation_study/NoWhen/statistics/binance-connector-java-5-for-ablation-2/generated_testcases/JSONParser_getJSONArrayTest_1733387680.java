
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
        String expected = "[\"BTCUSDT\",\"ETHUSDT\"]";
        assertEquals(expected, JSONParser.getJSONArray(symbols, "symbols"));
    }

    @Test
    public void testGetJSONArrayFailure() {
        ArrayList<Object> invalidSymbols = new ArrayList<>();
        invalidSymbols.add(new Object());
        JSONException exception = assertThrows(JSONException.class, () -> {
            JSONParser.getJSONArray(invalidSymbols, "invalidSymbols");
        });
        assertEquals("[JSONParser] Failed to convert \"invalidSymbols\" to JSON array", exception.getMessage());
    }
}
