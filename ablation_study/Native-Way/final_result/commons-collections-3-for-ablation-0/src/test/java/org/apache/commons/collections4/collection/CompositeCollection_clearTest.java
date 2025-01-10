
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CompositeCollection_clearTest {

    private CompositeCollection<String> compositeCollection;
    private Collection<String> mockCollection1;
    private Collection<String> mockCollection2;

    @BeforeEach
    public void setUp() {
        compositeCollection = new CompositeCollection<>();
        mockCollection1 = Mockito.mock(Collection.class);
        mockCollection2 = Mockito.mock(Collection.class);
        compositeCollection.addComposited(mockCollection1, mockCollection2);
    }

    @Test
    public void testClear() {
        compositeCollection.clear();

        verify(mockCollection1, times(1)).clear();
        verify(mockCollection2, times(1)).clear();
    }
}
