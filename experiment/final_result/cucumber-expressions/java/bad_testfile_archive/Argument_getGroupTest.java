
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collections;
import java.util.List;

public class Argument_getGroupTest {

    @Test
    public void testGetGroup() {
        Group group = new Group("testValue", 0, 4, Collections.emptyList());
        ParameterType<String> parameterType = new ParameterType<>("type", ".*", String.class, (List<String> values) -> values.get(0));
        List<Argument<?>> arguments = Argument.build(group, Collections.singletonList(parameterType));
        Argument<String> argument = (Argument<String>) arguments.get(0);

        assertEquals(group, argument.getGroup());
    }
}
