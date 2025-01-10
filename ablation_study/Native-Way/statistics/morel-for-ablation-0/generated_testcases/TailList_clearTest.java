
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
    public void testClear() {
        assertThat(backingList.size(), is(3));
        assertThat(tailList.size(), is(2));

        tailList.clear();

        assertThat(backingList.size(), is(1));
        assertThat(tailList.size(), is(0));
    }

    @Test
    public void testClearEmptyList() {
        TailList<String> emptyTailList = new TailList<>(new ArrayList<>(), 0);
        assertThat(emptyTailList.size(), is(0));

        emptyTailList.clear();

        assertThat(emptyTailList.size(), is(0));
    }

    @Test
    public void testClearWithStartGreaterThanSize() {
        TailList<String> tailListWithStartGreater = new TailList<>(backingList, 5);
        assertThat(tailListWithStartGreater.size(), is(0));

        tailListWithStartGreater.clear();

        assertThat(backingList.size(), is(3));
        assertThat(tailListWithStartGreater.size(), is(0));
    }
}
