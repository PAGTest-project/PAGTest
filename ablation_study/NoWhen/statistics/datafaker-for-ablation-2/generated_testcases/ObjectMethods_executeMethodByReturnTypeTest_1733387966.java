
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static net.datafaker.providers.base.ObjectMethods.executeMethodByReturnType;
import static net.datafaker.providers.base.ObjectMethods.getMethodByReturnType;

class ObjectMethods_executeMethodByReturnTypeTest {
    private Person person;
    private PersonName personName;

    @BeforeEach
    void setUp() {
        person = new Person();
        personName = new PersonName();
    }

    @Test
    void testExecuteMethodByReturnTypeWithValidReturnType() {
        Integer age = executeMethodByReturnType(person, "Integer");
        assertNotNull(age);
        assertEquals(person.age(), age);

        String firstName = executeMethodByReturnType(personName, "String");
        assertNotNull(firstName);
        assertEquals(personName.firstName(), firstName);
    }

    @Test
    void testExecuteMethodByReturnTypeWithInvalidReturnType() {
        assertNull(executeMethodByReturnType(person, "InvalidType"));
        assertNull(executeMethodByReturnType(personName, "InvalidType"));
    }

    @Test
    void testExecuteMethodByReturnTypeWithException() {
        assertThrows(RuntimeException.class, () -> executeMethodByReturnType(person, "void"));
        assertThrows(RuntimeException.class, () -> executeMethodByReturnType(personName, "void"));
    }

    @Test
    void testGetMethodByReturnTypeWithValidReturnType() {
        assertNotNull(getMethodByReturnType(person, "Integer"));
        assertNotNull(getMethodByReturnType(personName, "String"));
    }

    @Test
    void testGetMethodByReturnTypeWithInvalidReturnType() {
        assertNull(getMethodByReturnType(person, "InvalidType"));
        assertNull(getMethodByReturnType(personName, "InvalidType"));
    }
}
