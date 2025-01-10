
package net.hydromatic.morel.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;

public class TailList_clearTest {
    private List<String> backingList;
    private TailList<String> tailList;

    @BeforeEach
    public void setUp() {
        backingList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        tailList = new TailList<>(backingList, 2);
    }

    @Test
    public void testClear() {
        assertThat(tailList.size(), is(2));
        assertThat(backingList.size(), is(4));

        tailList.clear();

        assertThat(tailList.size(), is(0));
        assertThat(backingList.size(), is(2));
        assertThat(backingList.get(0), is("a"));
        assertThat(backingList.get(1), is("b"));
    }

    @Test
    public void testClearEmptyList() {
        TailList<String> emptyTailList = new TailList<>(new ArrayList<>(), 0);

        assertThat(emptyTailList.size(), is(0));

        emptyTailList.clear();

        assertThat(emptyTailList.size(), is(0));
    }

    @Test
    public void testClearWithNoElementsToRemove() {
        TailList<String> noRemoveTailList = new TailList<>(backingList, 4);

        assertThat(noRemoveTailList.size(), is(0));

        noRemoveTailList.clear();

        assertThat(noRemoveTailList.size(), is(0));
        assertThat(backingList.size(), is(4));
    }
}
