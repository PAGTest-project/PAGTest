
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class File_fileNameTest {

    private File file;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        file = new File(faker);
    }

    @Test
    public void testFileNameWithAllNulls() {
        when(faker.internet().slug()).thenReturn("defaultDir");
        when(faker.lorem().word()).thenReturn("defaultName");
        when(file.extension()).thenReturn("defaultExt");

        String expected = "defaultDir" + System.getProperty("file.separator") + "defaultname.defaultExt";
        assertEquals(expected, file.fileName(null, null, null, null));
    }

    @Test
    public void testFileNameWithCustomValues() {
        String expected = "customDir" + System.getProperty("file.separator") + "customName.customExt";
        assertEquals(expected, file.fileName("customDir", "customName", "customExt", null));
    }

    @Test
    public void testFileNameWithCustomSeparator() {
        String expected = "customDir/customName.customExt";
        assertEquals(expected, file.fileName("customDir", "customName", "customExt", "/"));
    }
}
