
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class File_fileNameTest {

    private File file;
    private BaseProviders faker;
    private Internet internet;
    private Lorem lorem;
    private net.datafaker.AbstractProvider.Context context;

    @BeforeEach
    public void setUp() {
        faker = Mockito.mock(BaseProviders.class);
        internet = Mockito.mock(Internet.class);
        lorem = Mockito.mock(Lorem.class);
        context = Mockito.mock(net.datafaker.AbstractProvider.Context.class);
        file = new File(faker);

        when(faker.internet()).thenReturn(internet);
        when(faker.lorem()).thenReturn(lorem);
        when(faker.getContext()).thenReturn(context);
    }

    @Test
    public void testFileNameWithAllNullInputs() {
        when(internet.slug()).thenReturn("defaultDir");
        when(lorem.word()).thenReturn("defaultName");
        when(file.extension()).thenReturn("defaultExt");
        when(context.getLocale()).thenReturn(java.util.Locale.getDefault());

        String expected = "defaultDir" + System.getProperty("file.separator") + "defaultname" + "." + "defaultExt";
        assertEquals(expected, file.fileName(null, null, null, null));
    }
}
