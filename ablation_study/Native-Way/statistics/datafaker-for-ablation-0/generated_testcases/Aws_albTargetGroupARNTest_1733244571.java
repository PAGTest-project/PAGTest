
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Aws_albTargetGroupARNTest {

    private Aws aws;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = mock(BaseProviders.class);
        aws = new Aws(faker);
    }

    @Test
    void testAlbTargetGroupARN() {
        when(faker.resolve("aws.regions")).thenReturn("us-west-2");
        when(faker.numerify("0#########")).thenReturn("0123456789");
        when(faker.app().name()).thenReturn("MyApp");
        when(faker.random().hex(16, false)).thenReturn("abcdef1234567890");

        String expectedARN = "arn:aws:elasticloadbalancing:us-west-2:0123456789:targetgroup/myapp/abcdef1234567890";
        assertEquals(expectedARN, aws.albTargetGroupARN());
    }
}
