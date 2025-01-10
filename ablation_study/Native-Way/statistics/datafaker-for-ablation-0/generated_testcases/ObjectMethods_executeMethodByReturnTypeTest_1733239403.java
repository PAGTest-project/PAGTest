
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;

public class ObjectMethods_executeMethodByReturnTypeTest {

    private Person person;
    private PersonName personName;

    @BeforeEach
    public void setUp() {
        person = new Person();
        personName = new PersonName();
    }

    @Test
    void testExecuteMethodByReturnTypeSuccess() throws NoSuchMethodException {
        Method expectedMethod = Person.class.getMethod("age");
        assertEquals(expectedMethod, ObjectMethods.getMethodByReturnType(person, "int"));
        assertEquals(30, ObjectMethods.executeMethodByReturnType(person, "int"));
    }

    @Test
    void testExecuteMethodByReturnTypeMethodNotFound() {
        assertNull(ObjectMethods.executeMethodByReturnType(person, "nonexistentType"));
    }

    @Test
    void testExecuteMethodByReturnTypeException() {
        assertThrows(RuntimeException.class, () -> {
            ObjectMethods.executeMethodByReturnType(person, "void");
        });
    }

    @Test
    void testExecuteMethodByReturnTypeWithPersonName() throws NoSuchMethodException {
        Method expectedMethod = PersonName.class.getMethod("firstName");
        assertEquals(expectedMethod, ObjectMethods.getMethodByReturnType(personName, "String"));
        assertEquals("John", ObjectMethods.executeMethodByReturnType(personName, "String"));
    }

    @Test
    void testExecuteMethodByReturnTypeWithIgnoredMethods() {
        assertNull(ObjectMethods.executeMethodByReturnType(person, "toString"));
        assertNull(ObjectMethods.executeMethodByReturnType(person, "hashCode"));
    }

    private static class Person {
        public int age() {
            return 30;
        }
    }

    private static class PersonName {
        public String firstName() {
            return "John";
        }
    }
}
