
package net.hydromatic.morel;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.eval.Session;

public class Main_runTest {

    @Test
    public void testRun() {
        // Given
        Main main = Mockito.mock(Main.class);
        BufferedReader in = Mockito.mock(BufferedReader.class);
        PrintWriter out = Mockito.mock(PrintWriter.class);
        Mockito.when(main.in).thenReturn(in);
        Mockito.when(main.out).thenReturn(out);
        Mockito.when(main.typeSystem).thenReturn(new TypeSystem());
        Mockito.when(main.session).thenReturn(new Session(new LinkedHashMap<>()));
        Mockito.when(main.valueMap).thenReturn(new LinkedHashMap<>());
        Mockito.when(main.idempotent).thenReturn(false);

        // When
        main.run();

        // Then
        Mockito.verify(main.session).withShell(Mockito.any(Shell.class), Mockito.any(Consumer.class), Mockito.any(Consumer.class));
        Mockito.verify(main.out).flush();
    }
}
