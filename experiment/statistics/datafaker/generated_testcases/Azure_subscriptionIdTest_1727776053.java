
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Azure_subscriptionIdTest {

    private Azure azure;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        azure = new Azure(faker);
    }

    @Test
    void testSubscriptionId() {
        assertThat(azure.subscriptionId()).matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
    }
}
