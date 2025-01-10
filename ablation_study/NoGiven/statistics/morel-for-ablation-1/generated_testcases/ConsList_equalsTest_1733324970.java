
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsList_equalsTest {

    @Test
    void testEquals_SameInstance() {
        ConsList<Integer> list = new ConsList<>(1, ImmutableList.of(2, 3));
        assertTrue(list.equals(list));
    }

    @Test
    void testEquals_DifferentInstanceSameContent() {
        ConsList<Integer> list1 = new ConsList<>(1, ImmutableList.of(2, 3));
        ConsList<Integer> list2 = new ConsList<>(1, ImmutableList.of(2, 3));
        assertTrue(list1.equals(list2));
    }

    @Test
    void testEquals_DifferentContent() {
        ConsList<Integer> list1 = new ConsList<>(1, ImmutableList.of(2, 3));
        ConsList<Integer> list2 = new ConsList<>(1, ImmutableList.of(2, 4));
        assertFalse(list1.equals(list2));
    }

    @Test
    void testEquals_NotAList() {
        ConsList<Integer> list = new ConsList<>(1, ImmutableList.of(2, 3));
        assertFalse(list.equals("not a list"));
    }

    @Test
    void testEquals_Null() {
        ConsList<Integer> list = new ConsList<>(1, ImmutableList.of(2, 3));
        assertFalse(list.equals(null));
    }
}
