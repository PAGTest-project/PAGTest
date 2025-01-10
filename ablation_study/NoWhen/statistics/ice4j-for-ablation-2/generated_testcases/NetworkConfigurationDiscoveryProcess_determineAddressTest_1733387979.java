
package org.ice4j.stunclient;

import org.ice4j.*;
import org.ice4j.attribute.*;
import org.ice4j.message.*;
import org.ice4j.socket.*;
import org.ice4j.stack.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NetworkConfigurationDiscoveryProcess_determineAddressTest {

    private NetworkConfigurationDiscoveryProcess discoveryProcess;
    private StunStack stunStack;
    private TransportAddress localAddress;
    private TransportAddress serverAddress;
    private BlockingRequestSender requestSender;

    @BeforeEach
    public void setUp() throws IOException, StunException {
        stunStack = mock(StunStack.class);
        localAddress = mock(TransportAddress.class);
        serverAddress = mock(TransportAddress.class);
        requestSender = mock(BlockingRequestSender.class);

        discoveryProcess = new NetworkConfigurationDiscoveryProcess(stunStack, localAddress, serverAddress);
        discoveryProcess.setRequestSender(requestSender); // Change here
        discoveryProcess.start();
    }

    @Test
    public void testDetermineAddress_UDPBlocked() throws StunException, IOException {
        when(requestSender.sendRequestAndWaitForResponse(any(), eq(serverAddress))).thenReturn(null);

        StunDiscoveryReport report = discoveryProcess.determineAddress();

        assertEquals(StunDiscoveryReport.UDP_BLOCKING_FIREWALL, report.getNatType());
    }

    @Test
    public void testDetermineAddress_OpenInternet() throws StunException, IOException {
        StunMessageEvent evt = mock(StunMessageEvent.class);
        MappedAddressAttribute mappedAddressAttribute = mock(MappedAddressAttribute.class);
        TransportAddress mappedAddress = mock(TransportAddress.class);
        when(mappedAddressAttribute.getAddress()).thenReturn(mappedAddress);
        when(evt.getMessage().getAttribute(Attribute.MAPPED_ADDRESS)).thenReturn(mappedAddressAttribute);
        when(mappedAddress.equals(localAddress)).thenReturn(true);
        when(requestSender.sendRequestAndWaitForResponse(any(), eq(serverAddress))).thenReturn(evt);

        StunDiscoveryReport report = discoveryProcess.determineAddress();

        assertEquals(StunDiscoveryReport.OPEN_INTERNET, report.getNatType());
    }

    @Test
    public void testDetermineAddress_SymmetricUDPFirewall() throws StunException, IOException {
        StunMessageEvent evt = mock(StunMessageEvent.class);
        MappedAddressAttribute mappedAddressAttribute = mock(MappedAddressAttribute.class);
        TransportAddress mappedAddress = mock(TransportAddress.class);
        when(mappedAddressAttribute.getAddress()).thenReturn(mappedAddress);
        when(evt.getMessage().getAttribute(Attribute.MAPPED_ADDRESS)).thenReturn(mappedAddressAttribute);
        when(mappedAddress.equals(localAddress)).thenReturn(true);
        when(requestSender.sendRequestAndWaitForResponse(any(), eq(serverAddress))).thenReturn(evt, null);

        StunDiscoveryReport report = discoveryProcess.determineAddress();

        assertEquals(StunDiscoveryReport.SYMMETRIC_UDP_FIREWALL, report.getNatType());
    }

    @Test
    public void testDetermineAddress_FullConeNAT() throws StunException, IOException {
        StunMessageEvent evt = mock(StunMessageEvent.class);
        MappedAddressAttribute mappedAddressAttribute = mock(MappedAddressAttribute.class);
        TransportAddress mappedAddress = mock(TransportAddress.class);
        when(mappedAddressAttribute.getAddress()).thenReturn(mappedAddress);
        when(evt.getMessage().getAttribute(Attribute.MAPPED_ADDRESS)).thenReturn(mappedAddressAttribute);
        when(mappedAddress.equals(localAddress)).thenReturn(false);
        when(requestSender.sendRequestAndWaitForResponse(any(), eq(serverAddress))).thenReturn(evt);

        StunDiscoveryReport report = discoveryProcess.determineAddress();

        assertEquals(StunDiscoveryReport.FULL_CONE_NAT, report.getNatType());
    }

    @Test
    public void testDetermineAddress_SymmetricNAT() throws StunException, IOException {
        StunMessageEvent evt = mock(StunMessageEvent.class);
        MappedAddressAttribute mappedAddressAttribute = mock(MappedAddressAttribute.class);
        TransportAddress mappedAddress = mock(TransportAddress.class);
        TransportAddress mappedAddress2 = mock(TransportAddress.class);
        when(mappedAddressAttribute.getAddress()).thenReturn(mappedAddress);
        when(evt.getMessage().getAttribute(Attribute.MAPPED_ADDRESS)).thenReturn(mappedAddressAttribute);
        when(mappedAddress.equals(localAddress)).thenReturn(false);
        when(requestSender.sendRequestAndWaitForResponse(any(), eq(serverAddress))).thenReturn(evt, null);

        StunMessageEvent evt2 = mock(StunMessageEvent.class);
        MappedAddressAttribute mappedAddressAttribute2 = mock(MappedAddressAttribute.class);
        when(mappedAddressAttribute2.getAddress()).thenReturn(mappedAddress2);
        when(evt2.getMessage().getAttribute(Attribute.MAPPED_ADDRESS)).thenReturn(mappedAddressAttribute2);
        when(mappedAddress.equals(mappedAddress2)).thenReturn(false);
        when(requestSender.sendRequestAndWaitForResponse(any(), any())).thenReturn(evt2);

        StunDiscoveryReport report = discoveryProcess.determineAddress();

        assertEquals(StunDiscoveryReport.SYMMETRIC_NAT, report.getNatType());
    }
}
