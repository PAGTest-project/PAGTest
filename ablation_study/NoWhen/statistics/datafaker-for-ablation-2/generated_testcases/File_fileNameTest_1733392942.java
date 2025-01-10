
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class File_fileNameTest {

    private File file;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        file = new File(faker);
    }

    @Test
    public void testFileNameWithAllNullInputs() {
        when(faker.internet().slug()).thenReturn("defaultDir");
        when(faker.lorem().word()).thenReturn("defaultName");
        when(file.extension()).thenReturn("defaultExt");

        String result = file.fileName(null, null, null, null);

        assertEquals("defaultDir" + System.getProperty("file.separator") + "defaultname.defaultExt", result);
    }

    @Test
    public void testFileNameWithAllInputsProvided() {
        String result = file.fileName("customDir", "customName", "customExt", "customSep");

        assertEquals("customDircustomSepcustomName.customExt", result);
    }
}
