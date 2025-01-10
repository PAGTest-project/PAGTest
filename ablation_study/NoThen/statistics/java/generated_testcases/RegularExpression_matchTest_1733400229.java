
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RegularExpression_matchTest {

    private ParameterTypeRegistry parameterTypeRegistry;
    private RegularExpression regularExpression;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(Locale.ENGLISH);
        regularExpression = new RegularExpression(Pattern.compile("I have a (red|blue|yellow) ball"), parameterTypeRegistry);
    }

    @Test
    public void testMatchWithValidText() {
        parameterTypeRegistry.defineParameterType(new ParameterType<>(
                null,
                "red|blue|yellow",
                Color.class,
                Color::new,
                false,
                false
        ));

        List<Argument<?>> arguments = regularExpression.match("I have a red ball");
        assertEquals(1, arguments.size());
        assertEquals(new Color("red"), arguments.get(0).getValue());
    }

    @Test
    public void testMatchWithInvalidText() {
        List<Argument<?>> arguments = regularExpression.match("I have a green ball");
        assertNull(arguments);
    }

    @Test
    public void testMatchWithTypeHint() {
        parameterTypeRegistry.defineParameterType(new ParameterType<>(
                null,
                "red|blue|yellow",
                Color.class,
                Color::new,
                false,
                false
        ));

        List<Argument<?>> arguments = regularExpression.match("I have a red ball", String.class);
        assertEquals(1, arguments.size());
        assertEquals(new Color("red"), arguments.get(0).getValue());
    }

    @Test
    public void testMatchWithAnonymousParameterType() {
        List<Argument<?>> arguments = regularExpression.match("I have a red ball");
        assertEquals(1, arguments.size());
        assertEquals("red", arguments.get(0).getValue());
    }

    private static class Color {
        private final String color;

        public Color(String color) {
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Color color1 = (Color) o;
            return color.equals(color1.color);
        }

        @Override
        public int hashCode() {
            return color.hashCode();
        }
    }
}
