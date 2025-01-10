
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.SAXException;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorResources_processTest {

    private ValidatorResources resources;

    @BeforeEach
    protected void setUp() throws IOException, SAXException {
        final InputStream[] streams = { this.getClass().getResourceAsStream("RetrieveFormTest-config.xml") };
        resources = new ValidatorResources(streams);
        for (final InputStream stream : streams) {
            stream.close();
        }
    }

    @Test
    public void testProcess() {
        resources.process();
        assertTrue(resources.hFormSets.getFast());
        assertTrue(resources.hConstants.getFast());
        assertTrue(resources.hActions.getFast());
    }
}
