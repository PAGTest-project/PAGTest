
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
    public void testGetJSONArrayFailure() {
        ArrayList<Object> symbols = new ArrayList<>();
        symbols.add(new Object()); // Adding an invalid object for JSON conversion
        String key = "symbols";
        JSONException exception = assertThrows(JSONException.class, () -> {
            JSONParser.getJSONArray(symbols, key);
        });
        assertEquals("[JSONParser] Failed to convert \"symbols\" to JSON array", exception.getMessage());
    }
}
