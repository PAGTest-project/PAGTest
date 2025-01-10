
package org.ice4j.stack;

import org.ice4j.CredentialsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StunStack_getCredentialsManagerTest {
    private StunStack stunStack;

    @BeforeEach
    public void setUp() {
        stunStack = new StunStack();
    }

    @Test
    public void testGetCredentialsManager() {
        CredentialsManager credentialsManager = stunStack.getCredentialsManager();
        assertNotNull(credentialsManager, "CredentialsManager should not be null");
    }
}
