
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class ObjectMethods_executeMethodByReturnTypeTest {
    private Person person;
    private PersonName personName;

    @BeforeEach
    void setUp() {
        person = new Person();
        personName = new PersonName();
    }

    @Test
    void testExecuteMethodByReturnTypeSuccess() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method ageMethod = Person.class.getMethod("age");
        ageMethod.setAccessible(true);
        Object expectedAge = ageMethod.invoke(person);

        Object actualAge = ObjectMethods.executeMethodByReturnType(person, "int");
        assertEquals(expectedAge, actualAge);
    }

    @Test
    void testExecuteMethodByReturnTypeMethodNotFound() {
        Object result = ObjectMethods.executeMethodByReturnType(person, "nonexistentReturnType");
        assertNull(result);
    }

    @Test
    void testExecuteMethodByReturnTypeException() {
        assertThrows(RuntimeException.class, () -> {
            ObjectMethods.executeMethodByReturnType(person, "void");
        });
    }

    // Dummy classes for testing
    private static class Person {
        public int age() {
            return 30;
        }
    }

    private static class PersonName {
        public String firstName() {
            return "John";
        }

        public String lastName() {
            return "Doe";
        }
    }
}
