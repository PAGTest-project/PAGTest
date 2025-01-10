
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Aws_albTargetGroupARNTest {

    private Aws aws;
    private BaseProviders faker;
    private App app;
    private RandomService randomService;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        app = mock(App.class);
        randomService = mock(RandomService.class);
        when(faker.app()).thenReturn(app);
        when(faker.random()).thenReturn(randomService);
        aws = new Aws(faker);
    }

    @Test
    void testAlbTargetGroupARN() {
        when(faker.resolve("aws.regions")).thenReturn("us-west-2");
        when(faker.numerify("0#########")).thenReturn("0123456789");
        when(app.name()).thenReturn("MyApp");
        when(randomService.hex(16, false)).thenReturn("abcdef1234567890");

        String expectedARN = "arn:aws:elasticloadbalancing:us-west-2:0123456789:targetgroup/myapp/abcdef1234567890";
        assertEquals(expectedARN, aws.albTargetGroupARN());
    }
}
