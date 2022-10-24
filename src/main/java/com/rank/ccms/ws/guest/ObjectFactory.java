
package com.rank.ccms.ws.guest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rank.ibl.ws.guest package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClientType_QNAME = new QName("http://portal.vidyo.com/guest", "ClientType");
    private final static QName _GetPortalFeaturesRequest_QNAME = new QName("http://portal.vidyo.com/guest", "GetPortalFeaturesRequest");
    private final static QName _CreateTestcallRoomRequest_QNAME = new QName("http://portal.vidyo.com/guest", "CreateTestcallRoomRequest");
    private final static QName _GetPortalVersionRequest_QNAME = new QName("http://portal.vidyo.com/guest", "GetPortalVersionRequest");
    private final static QName _ErrorMessage_QNAME = new QName("http://portal.vidyo.com/guest", "ErrorMessage");
    private final static QName _OK_QNAME = new QName("http://portal.vidyo.com/guest", "OK");
    private final static QName _GuestJoinConferenceRequestReferenceNumber_QNAME = new QName("http://portal.vidyo.com/guest", "referenceNumber");
    private final static QName _GuestJoinConferenceRequestPin_QNAME = new QName("http://portal.vidyo.com/guest", "pin");
    private final static QName _LogInAsGuestResponseProxyaddress_QNAME = new QName("http://portal.vidyo.com/guest", "proxyaddress");
    private final static QName _LogInAsGuestResponseLoctag_QNAME = new QName("http://portal.vidyo.com/guest", "loctag");
    private final static QName _LogInAsGuestResponseVmaddress_QNAME = new QName("http://portal.vidyo.com/guest", "vmaddress");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rank.ibl.ws.guest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EndpointNotBoundFault }
     * 
     */
    public EndpointNotBoundFault createEndpointNotBoundFault() {
        return new EndpointNotBoundFault();
    }

    /**
     * Create an instance of {@link RaiseHandRequest }
     * 
     */
    public RaiseHandRequest createRaiseHandRequest() {
        return new RaiseHandRequest();
    }

    /**
     * Create an instance of {@link ResourceNotAvailableFault }
     * 
     */
    public ResourceNotAvailableFault createResourceNotAvailableFault() {
        return new ResourceNotAvailableFault();
    }

    /**
     * Create an instance of {@link EndpointFeature }
     * 
     */
    public EndpointFeature createEndpointFeature() {
        return new EndpointFeature();
    }

    /**
     * Create an instance of {@link UnraiseHandRequest }
     * 
     */
    public UnraiseHandRequest createUnraiseHandRequest() {
        return new UnraiseHandRequest();
    }

    /**
     * Create an instance of {@link SetEndpointDetailsResponse }
     * 
     */
    public SetEndpointDetailsResponse createSetEndpointDetailsResponse() {
        return new SetEndpointDetailsResponse();
    }

    /**
     * Create an instance of {@link CreateTestcallRoomResponse }
     * 
     */
    public CreateTestcallRoomResponse createCreateTestcallRoomResponse() {
        return new CreateTestcallRoomResponse();
    }

    /**
     * Create an instance of {@link LogInAsGuestResponse }
     * 
     */
    public LogInAsGuestResponse createLogInAsGuestResponse() {
        return new LogInAsGuestResponse();
    }

    /**
     * Create an instance of {@link EndpointBehaviorDataType }
     * 
     */
    public EndpointBehaviorDataType createEndpointBehaviorDataType() {
        return new EndpointBehaviorDataType();
    }

    /**
     * Create an instance of {@link LinkEndpointToGuestResponse }
     * 
     */
    public LinkEndpointToGuestResponse createLinkEndpointToGuestResponse() {
        return new LinkEndpointToGuestResponse();
    }

    /**
     * Create an instance of {@link NotificationEmailNotConfiguredFault }
     * 
     */
    public NotificationEmailNotConfiguredFault createNotificationEmailNotConfiguredFault() {
        return new NotificationEmailNotConfiguredFault();
    }

    /**
     * Create an instance of {@link UnraiseHandResponse }
     * 
     */
    public UnraiseHandResponse createUnraiseHandResponse() {
        return new UnraiseHandResponse();
    }

    /**
     * Create an instance of {@link AccessRestrictedFault }
     * 
     */
    public AccessRestrictedFault createAccessRestrictedFault() {
        return new AccessRestrictedFault();
    }

    /**
     * Create an instance of {@link ClientVersionRequest }
     * 
     */
    public ClientVersionRequest createClientVersionRequest() {
        return new ClientVersionRequest();
    }

    /**
     * Create an instance of {@link LinkEndpointToGuestRequest }
     * 
     */
    public LinkEndpointToGuestRequest createLinkEndpointToGuestRequest() {
        return new LinkEndpointToGuestRequest();
    }

    /**
     * Create an instance of {@link TestcallRoomCreationFault }
     * 
     */
    public TestcallRoomCreationFault createTestcallRoomCreationFault() {
        return new TestcallRoomCreationFault();
    }

    /**
     * Create an instance of {@link GetPortalVersionResponse }
     * 
     */
    public GetPortalVersionResponse createGetPortalVersionResponse() {
        return new GetPortalVersionResponse();
    }

    /**
     * Create an instance of {@link GetPortalFeaturesResponse }
     * 
     */
    public GetPortalFeaturesResponse createGetPortalFeaturesResponse() {
        return new GetPortalFeaturesResponse();
    }

    /**
     * Create an instance of {@link PortalFeature }
     * 
     */
    public PortalFeature createPortalFeature() {
        return new PortalFeature();
    }

    /**
     * Create an instance of {@link GetRoomDetailsByRoomKeyRequest }
     * 
     */
    public GetRoomDetailsByRoomKeyRequest createGetRoomDetailsByRoomKeyRequest() {
        return new GetRoomDetailsByRoomKeyRequest();
    }

    /**
     * Create an instance of {@link LogInAsGuestRequest }
     * 
     */
    public LogInAsGuestRequest createLogInAsGuestRequest() {
        return new LogInAsGuestRequest();
    }

    /**
     * Create an instance of {@link WhatIsMyIPAddressRequest }
     * 
     */
    public WhatIsMyIPAddressRequest createWhatIsMyIPAddressRequest() {
        return new WhatIsMyIPAddressRequest();
    }

    /**
     * Create an instance of {@link ConferenceLockedFault }
     * 
     */
    public ConferenceLockedFault createConferenceLockedFault() {
        return new ConferenceLockedFault();
    }

    /**
     * Create an instance of {@link RecoverPasswordResponse }
     * 
     */
    public RecoverPasswordResponse createRecoverPasswordResponse() {
        return new RecoverPasswordResponse();
    }

    /**
     * Create an instance of {@link RaiseHandResponse }
     * 
     */
    public RaiseHandResponse createRaiseHandResponse() {
        return new RaiseHandResponse();
    }

    /**
     * Create an instance of {@link ClientVersionResponse }
     * 
     */
    public ClientVersionResponse createClientVersionResponse() {
        return new ClientVersionResponse();
    }

    /**
     * Create an instance of {@link EmailAddressNotFoundFault }
     * 
     */
    public EmailAddressNotFoundFault createEmailAddressNotFoundFault() {
        return new EmailAddressNotFoundFault();
    }

    /**
     * Create an instance of {@link SetEndpointDetailsRequest }
     * 
     */
    public SetEndpointDetailsRequest createSetEndpointDetailsRequest() {
        return new SetEndpointDetailsRequest();
    }

    /**
     * Create an instance of {@link GetRoomDetailsByRoomKeyResponse }
     * 
     */
    public GetRoomDetailsByRoomKeyResponse createGetRoomDetailsByRoomKeyResponse() {
        return new GetRoomDetailsByRoomKeyResponse();
    }

    /**
     * Create an instance of {@link WrongPinFault }
     * 
     */
    public WrongPinFault createWrongPinFault() {
        return new WrongPinFault();
    }

    /**
     * Create an instance of {@link GuestJoinConferenceRequest }
     * 
     */
    public GuestJoinConferenceRequest createGuestJoinConferenceRequest() {
        return new GuestJoinConferenceRequest();
    }

    /**
     * Create an instance of {@link InvalidArgumentFault }
     * 
     */
    public InvalidArgumentFault createInvalidArgumentFault() {
        return new InvalidArgumentFault();
    }

    /**
     * Create an instance of {@link GeneralFault }
     * 
     */
    public GeneralFault createGeneralFault() {
        return new GeneralFault();
    }

    /**
     * Create an instance of {@link RoomIsFullFault }
     * 
     */
    public RoomIsFullFault createRoomIsFullFault() {
        return new RoomIsFullFault();
    }

    /**
     * Create an instance of {@link GuestJoinConferenceResponse }
     * 
     */
    public GuestJoinConferenceResponse createGuestJoinConferenceResponse() {
        return new GuestJoinConferenceResponse();
    }

    /**
     * Create an instance of {@link RecoverPasswordRequest }
     * 
     */
    public RecoverPasswordRequest createRecoverPasswordRequest() {
        return new RecoverPasswordRequest();
    }

    /**
     * Create an instance of {@link WhatIsMyIPAddressResponse }
     * 
     */
    public WhatIsMyIPAddressResponse createWhatIsMyIPAddressResponse() {
        return new WhatIsMyIPAddressResponse();
    }

    /**
     * Create an instance of {@link AllLinesInUseFault }
     * 
     */
    public AllLinesInUseFault createAllLinesInUseFault() {
        return new AllLinesInUseFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "ClientType")
    public JAXBElement<String> createClientType(String value) {
        return new JAXBElement<String>(_ClientType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "GetPortalFeaturesRequest")
    public JAXBElement<Object> createGetPortalFeaturesRequest(Object value) {
        return new JAXBElement<Object>(_GetPortalFeaturesRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "CreateTestcallRoomRequest")
    public JAXBElement<Object> createCreateTestcallRoomRequest(Object value) {
        return new JAXBElement<Object>(_CreateTestcallRoomRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "GetPortalVersionRequest")
    public JAXBElement<Object> createGetPortalVersionRequest(Object value) {
        return new JAXBElement<Object>(_GetPortalVersionRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "ErrorMessage")
    public JAXBElement<String> createErrorMessage(String value) {
        return new JAXBElement<String>(_ErrorMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "OK")
    public JAXBElement<String> createOK(String value) {
        return new JAXBElement<String>(_OK_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "referenceNumber", scope = GuestJoinConferenceRequest.class)
    public JAXBElement<String> createGuestJoinConferenceRequestReferenceNumber(String value) {
        return new JAXBElement<String>(_GuestJoinConferenceRequestReferenceNumber_QNAME, String.class, GuestJoinConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "pin", scope = GuestJoinConferenceRequest.class)
    public JAXBElement<String> createGuestJoinConferenceRequestPin(String value) {
        return new JAXBElement<String>(_GuestJoinConferenceRequestPin_QNAME, String.class, GuestJoinConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "proxyaddress", scope = LogInAsGuestResponse.class)
    public JAXBElement<String> createLogInAsGuestResponseProxyaddress(String value) {
        return new JAXBElement<String>(_LogInAsGuestResponseProxyaddress_QNAME, String.class, LogInAsGuestResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "loctag", scope = LogInAsGuestResponse.class)
    public JAXBElement<String> createLogInAsGuestResponseLoctag(String value) {
        return new JAXBElement<String>(_LogInAsGuestResponseLoctag_QNAME, String.class, LogInAsGuestResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/guest", name = "vmaddress", scope = LogInAsGuestResponse.class)
    public JAXBElement<String> createLogInAsGuestResponseVmaddress(String value) {
        return new JAXBElement<String>(_LogInAsGuestResponseVmaddress_QNAME, String.class, LogInAsGuestResponse.class, value);
    }

}
