
package net.hydromatic.morel.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TailList_clearTest {
    private List<String> backingList;
    private TailList<String> tailList;

    @BeforeEach
    public void setUp() {
        backingList = new ArrayList<>();
        backingList.add("a");
        backingList.add("b");
        backingList.add("c");
        tailList = new TailList<>(backingList, 1);
    }

    @Test
    public void testClearWithElementsToRemove() {
        tailList.clear();
        assertThat(backingList.size(), is(1));
        assertThat(backingList.get(0), is("a"));
    }

    @Test
    public void testClearWithNoElementsToRemove() {
        TailList<String> emptyTailList = new TailList<>(backingList, 3);
        emptyTailList.clear();
        assertThat(backingList.size(), is(3));
        assertThat(backingList.get(0), is("a"));
        assertThat(backingList.get(1), is("b"));
        assertThat(backingList.get(2), is("c"));
    }
}
