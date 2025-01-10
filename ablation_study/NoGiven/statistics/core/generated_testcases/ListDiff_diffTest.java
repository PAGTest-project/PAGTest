
package org.openapitools.openapidiff.core.compare;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListDiff_diffTest {

    @Test
    void testDiff_AllNull() {
        ChangedListImpl<String> instance = new ChangedListImpl<>(null, null);
        ChangedList<String> result = ListDiff.diff(instance);
        assertEquals(instance, result);
    }

    @Test
    void testDiff_OldValueNull() {
        ChangedListImpl<String> instance = new ChangedListImpl<>(null, Arrays.asList("new1", "new2"));
        ChangedList<String> result = ListDiff.diff(instance);
        assertEquals(Arrays.asList("new1", "new2"), result.getIncreased());
        assertTrue(result.getMissing().isEmpty());
        assertTrue(result.getShared().isEmpty());
    }

    @Test
    void testDiff_NewValueNull() {
        ChangedListImpl<String> instance = new ChangedListImpl<>(Arrays.asList("old1", "old2"), null);
        ChangedList<String> result = ListDiff.diff(instance);
        assertEquals(Arrays.asList("old1", "old2"), result.getMissing());
        assertTrue(result.getIncreased().isEmpty());
        assertTrue(result.getShared().isEmpty());
    }

    @Test
    void testDiff_BothValuesNotNull() {
        ChangedListImpl<String> instance = new ChangedListImpl<>(Arrays.asList("old1", "old2"), Arrays.asList("new1", "old2"));
        ChangedList<String> result = ListDiff.diff(instance);
        assertEquals(Collections.singletonList("new1"), result.getIncreased());
        assertEquals(Collections.singletonList("old1"), result.getMissing());
        assertEquals(Collections.singletonList("old2"), result.getShared());
    }

    static class ChangedListImpl<T> implements ChangedList<T> {
        private final List<T> oldValue;
        private final List<T> newValue;
        private List<T> increased;
        private List<T> missing;
        private List<T> shared;

        ChangedListImpl(List<T> oldValue, List<T> newValue) {
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.increased = Collections.emptyList();
            this.missing = Collections.emptyList();
            this.shared = Collections.emptyList();
        }

        @Override
        public List<T> getOldValue() {
            return oldValue;
        }

        @Override
        public List<T> getNewValue() {
            return newValue;
        }

        @Override
        public List<T> getIncreased() {
            return increased;
        }

        @Override
        public List<T> getMissing() {
            return missing;
        }

        @Override
        public List<T> getShared() {
            return shared;
        }

        @Override
        public ChangedList<T> setIncreased(List<T> increased) {
            this.increased = increased;
            return this;
        }

        @Override
        public ChangedList<T> setMissing(List<T> missing) {
            this.missing = missing;
            return this;
        }

        @Override
        public ChangedList<T> setShared(List<T> shared) {
            this.shared = shared;
            return this;
        }

        // Other methods of ChangedList interface are not implemented for simplicity
    }
}
