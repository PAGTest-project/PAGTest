
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Azure_subscriptionIdTest {

    @Test
    void testSubscriptionId() {
        assertThat(new Azure(new BaseFaker()).subscriptionId())
            .matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
    }

    @Test
    void testTenantId() {
        assertThat(new Azure(new BaseFaker()).tenantId())
            .matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
    }
}
