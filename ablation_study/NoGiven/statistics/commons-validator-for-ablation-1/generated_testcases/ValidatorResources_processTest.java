
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorResources_processTest {

    private ValidatorResources resources;

    @BeforeEach
    protected void setUp() throws IOException, SAXException {
        final InputStream[] streams = { this.getClass().getResourceAsStream("ValidatorResourcesTest-config.xml") };
        resources = new ValidatorResources(streams);
        for (final InputStream stream : streams) {
            stream.close();
        }
    }

    @Test
    public void testProcess() {
        resources.process();
        assertTrue(resources.getFormSets() instanceof Map);
        assertTrue(resources.getConstants() instanceof Map);
        assertTrue(resources.getActions() instanceof Map);
    }
}
