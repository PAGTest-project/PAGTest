
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class File_fileNameTest {

    @Test
    public void testFileNameWithAllNullInputs() {
        BaseProviders faker = Mockito.mock(BaseProviders.class);
        Internet internet = Mockito.mock(Internet.class);
        Lorem lorem = Mockito.mock(Lorem.class);
        File file = new File(faker);

        when(faker.internet()).thenReturn(internet);
        when(faker.lorem()).thenReturn(lorem);
        when(internet.slug()).thenReturn("defaultDir");
        when(lorem.word()).thenReturn("defaultName");
        when(file.extension()).thenReturn("defaultExt");

        String expected = "defaultDir" + System.getProperty("file.separator") + "defaultname.defaultExt";
        assertEquals(expected, file.fileName(null, null, null, null));
    }

    @Test
    public void testFileNameWithAllInputsProvided() {
        String dir = "providedDir";
        String name = "providedName";
        String ext = "providedExt";
        String sep = "/";

        BaseProviders faker = Mockito.mock(BaseProviders.class);
        File file = new File(faker);

        String expected = dir + sep + name + "." + ext;
        assertEquals(expected, file.fileName(dir, name, ext, sep));
    }
}
