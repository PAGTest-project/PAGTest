
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
        assertThat(azure.subscriptionId())
            .matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
    }

    @Test
    void testTenantId() {
        assertThat(azure.tenantId())
            .matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");
    }

    @Test
    void testResourceGroup() {
        assertThat(azure.resourceGroup()).matches("^rg-[0-9a-f]{16}$");
    }

    @Test
    void testManagementGroup() {
        assertThat(azure.managementGroup()).matches("^mg-[0-9a-f]{16}$");
    }

    @Test
    void testApplicationGateway() {
        assertThat(azure.applicationGateway()).matches("^agw-[0-9a-f]{16}$");
    }

    @Test
    void testBastionHost() {
        assertThat(azure.bastionHost()).matches("^bas-[0-9a-f]{16}$");
    }

    @Test
    void testFirewall() {
        assertThat(azure.firewall()).matches("^afw-[0-9a-f]{16}$");
    }

    @Test
    void testLoadBalancer() {
        assertThat(azure.loadBalancer()).matches("^lbi-[0-9a-f]{16}$");
    }

    @Test
    void testNetworkSecurityGroup() {
        assertThat(azure.networkSecurityGroup()).matches("^nsg-[0-9a-f]{16}$");
    }

    @Test
    void testVirtualNetwork() {
        assertThat(azure.virtualNetwork()).matches("^vnet-[0-9a-f]{16}$");
    }

    @Test
    void testVirtualWan() {
        assertThat(azure.virtualWan()).matches("^vwan-[0-9a-f]{16}$");
    }

    @Test
    void testAppServiceEnvironment() {
        assertThat(azure.appServiceEnvironment()).matches("^ase-[0-9a-f]{16}$");
    }

    @Test
    void testAppServicePlan() {
        assertThat(azure.appServicePlan()).matches("^asp-[0-9a-f]{16}$");
    }

    @Test
    void testLoadTesting() {
        assertThat(azure.loadTesting()).matches("^lt-[0-9a-f]{16}$");
    }

    @Test
    void testStaticWebApp() {
        assertThat(azure.staticWebApp()).matches("^stapp-[0-9a-f]{16}$");
    }

    @Test
    void testVirtualMachine() {
        assertThat(azure.virtualMachine()).matches("^vm-[0-9a-f]{16}$");
    }

    @Test
    void testStorageAccount() {
        assertThat(azure.storageAccount()).matches("^st-[0-9a-f]{16}$");
    }

    @Test
    void testContainerRegistry() {
        assertThat(azure.containerRegistry()).matches("^cr-[0-9a-f]{16}$");
    }

    @Test
    void testContainerApps() {
        assertThat(azure.containerApps()).matches("^ca-[0-9a-f]{16}$");
    }

    @Test
    void testContainerAppsEnvironment() {
        assertThat(azure.containerAppsEnvironment()).matches("^cae-[0-9a-f]{16}$");
    }

    @Test
    void testContainerInstance() {
        assertThat(azure.containerInstance()).matches("^ci-[0-9a-f]{16}$");
    }

    @Test
    void testCosmosDBDatabase() {
        assertThat(azure.cosmosDBDatabase()).matches("^cosmos-[0-9a-f]{16}$");
    }

    @Test
    void testSqlDatabase() {
        assertThat(azure.sqlDatabase()).matches("^sql-[0-9a-f]{16}$");
    }

    @Test
    void testMysqlDatabase() {
        assertThat(azure.mysqlDatabase()).matches("^mysql-[0-9a-f]{16}$");
    }

    @Test
    void testPostgreSQLDatabase() {
        assertThat(azure.postgreSQLDatabase()).matches("^psql-[0-9a-f]{16}$");
    }

    @Test
    void testServiceBus() {
        assertThat(azure.serviceBus()).matches("^sb-[0-9a-f]{16}$");
    }

    @Test
    void testServiceBusQueue() {
        assertThat(azure.serviceBusQueue()).matches("^sbq-[0-9a-f]{16}$");
    }

    @Test
    void testServiceBusTopic() {
        assertThat(azure.serviceBusTopic()).matches("^sbt-[0-9a-f]{16}$");
    }

    @Test
    void testKeyVault() {
        assertThat(azure.keyVault()).matches("^kv-[0-9a-f]{16}$");
    }

    @Test
    void testLogAnalytics() {
        assertThat(azure.logAnalytics()).matches("^log-[0-9a-f]{16}$");
    }

    @Test
    void testSpringApps() {
        assertThat(azure.springApps()).matches("^sa-[0-9a-f]{16}$");
    }
}
