
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Azure_subscriptionIdTest {

    private final BaseProviders faker = new BaseProviders();
    private final Azure azure = new Azure(faker);

    @Test
    void testSubscriptionId() {
        assertThat(azure.subscriptionId()).matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
    }

    @Test
    void testTenantId() {
        assertThat(azure.tenantId()).matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
    }
}
