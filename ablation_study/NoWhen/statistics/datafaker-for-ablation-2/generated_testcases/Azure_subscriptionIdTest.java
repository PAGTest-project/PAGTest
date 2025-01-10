
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Azure_subscriptionIdTest {

    private Azure azure;

    @Test
    void testSubscriptionId() {
        BaseFaker faker = new BaseFaker();
        azure = new Azure(faker);
        assertThat(azure.subscriptionId()).matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
    }
}
