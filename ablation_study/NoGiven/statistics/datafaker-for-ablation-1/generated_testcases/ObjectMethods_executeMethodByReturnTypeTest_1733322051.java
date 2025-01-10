
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
    void testExecuteMethodByReturnTypeSuccess() {
        String returnTypeSimpleName = "String";
        String result = executeMethodByReturnType(person, returnTypeSimpleName);
        assertNotNull(result);
        assertEquals("John", result);
    }

    @Test
    void testExecuteMethodByReturnTypeMethodNotFound() {
        String returnTypeSimpleName = "NonExistentType";
        String result = executeMethodByReturnType(person, returnTypeSimpleName);
        assertNull(result);
    }

    @Test
    void testExecuteMethodByReturnTypeException() {
        String returnTypeSimpleName = "ExceptionThrowingType";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            executeMethodByReturnType(person, returnTypeSimpleName);
        });
        assertTrue(exception.getMessage().contains("Failed to call method"));
    }

    @Test
    void testGetMethodByReturnType() {
        String returnTypeSimpleName = "String";
        Method method = getMethodByReturnType(person, returnTypeSimpleName);
        assertNotNull(method);
        assertEquals("getName", method.getName());
    }

    @Test
    void testGetMethodByReturnTypeNotFound() {
        String returnTypeSimpleName = "NonExistentType";
        Method method = getMethodByReturnType(person, returnTypeSimpleName);
        assertNull(method);
    }
}
