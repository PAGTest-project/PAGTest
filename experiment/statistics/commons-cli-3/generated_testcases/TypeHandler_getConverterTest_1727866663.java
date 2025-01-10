
package org.apache.commons.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeHandler_getConverterTest {

    private TypeHandler typeHandler;
    private Map<Class<?>, Converter<?, ? extends Throwable>> converterMap;

    @BeforeEach
    public void setUp() {
        converterMap = new HashMap<>(TypeHandler.createDefaultMap());
        typeHandler = new TypeHandler(converterMap);
    }

    @Test
    public void testGetConverterExisting() {
        Converter<Path, ?> pathConverter = typeHandler.getConverter(Path.class);
        assertEquals(Converter.PATH, pathConverter);
    }

    @Test
    public void testGetConverterDefault() {
        Converter<String, ?> stringConverter = typeHandler.getConverter(String.class);
        assertEquals(Converter.DEFAULT, stringConverter);
    }
}
