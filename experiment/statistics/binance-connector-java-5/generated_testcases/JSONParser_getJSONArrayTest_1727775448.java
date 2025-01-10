
package com.binance.connector.client.utils;

import org.json.JSONException;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class JSONParser_getJSONArrayTest {

    @Test
    public void testGetJSONArray_Success() {
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        symbols.add("ETHUSDT");

        String expected = "[\"BTCUSDT\",\"ETHUSDT\"]";
        String actual = JSONParser.getJSONArray(symbols, "symbols");

        assertEquals(expected, actual);
    }

    @Test(expected = JSONException.class)
    public void testGetJSONArray_JSONException() {
        ArrayList<Object> invalidSymbols = new ArrayList<>();
        invalidSymbols.add(new Object()); // Adding an invalid object to trigger JSONException

        try {
            JSONParser.getJSONArray(invalidSymbols, "invalidSymbols");
        } catch (JSONException e) {
            throw e;
        }
    }
}
