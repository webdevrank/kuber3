
package com.rank.ccms.ws.user;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rank.ibl.ws.user package. 
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

    private final static QName _GetRoomProfilesRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "GetRoomProfilesRequest");
    private final static QName _RaiseHandRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "RaiseHandRequest");
    private final static QName _GetPortalFeaturesRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "GetPortalFeaturesRequest");
    private final static QName _SilenceSpeakerServerRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "SilenceSpeakerServerRequest");
    private final static QName _GetActiveSessionsRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "GetActiveSessionsRequest");
    private final static QName _UnraiseHandRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "UnraiseHandRequest");
    private final static QName _RoomStatus_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "RoomStatus");
    private final static QName _PortalPrefixRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "PortalPrefixRequest");
    private final static QName _MemberStatus_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "MemberStatus");
    private final static QName _MemberMode_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "MemberMode");
    private final static QName _OK_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "OK");
    private final static QName _LogoutAllOtherSessionsRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "LogoutAllOtherSessionsRequest");
    private final static QName _SilenceSpeakerServerAllResponse_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "SilenceSpeakerServerAllResponse");
    private final static QName _SilenceSpeakerServerAllRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "SilenceSpeakerServerAllRequest");
    private final static QName _ErrorMessage_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "ErrorMessage");
    private final static QName _OnetimeAccessRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "OnetimeAccessRequest");
    private final static QName _Language_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "Language");
    private final static QName _SilenceSpeakerServerResponse_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "SilenceSpeakerServerResponse");
    private final static QName _CreateTestcallRoomRequest_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "CreateTestcallRoomRequest");
    private final static QName _EntityType_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "EntityType");
    private final static QName _ClientType_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "ClientType");
    private final static QName _MuteVideoClientAllRequestModeratorPIN_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "moderatorPIN");
    private final static QName _RoomModeRoomPIN_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "roomPIN");
    private final static QName _RoomModeRoomURL_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "roomURL");
    private final static QName _RoomModeHasModeratorPIN_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "hasModeratorPIN");
    private final static QName _GetLoginAndWelcomeBannerResponseLoginBannerText_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "loginBannerText");
    private final static QName _MemberTitle_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "title");
    private final static QName _MemberInstantMessagerID_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "instantMessagerID");
    private final static QName _MemberThumbnailUpdateTime_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "thumbnailUpdateTime");
    private final static QName _MemberDepartment_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "department");
    private final static QName _MemberLocation_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "location");
    private final static QName _MemberPhone2_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "phone2");
    private final static QName _MemberPhone3_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "phone3");
    private final static QName _MemberPhone1_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "phone1");
    private final static QName _GetConferenceIDResponseConferenceID_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "conferenceID");
    private final static QName _EntityDetailsAudio_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "audio");
    private final static QName _EntityDetailsTenant_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "tenant");
    private final static QName _EntityDetailsOwnerID_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "ownerID");
    private final static QName _EntityDetailsParticipantID_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "participantID");
    private final static QName _EntityDetailsEmailAddress_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "emailAddress");
    private final static QName _EntityDetailsVideo_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "video");
    private final static QName _EntityDetailsAppshare_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "appshare");
    private final static QName _WelcomeBannerContentWelcomeBannerText_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "welcomeBannerText");
    private final static QName _LectureModeParticipantPresenter_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "presenter");
    private final static QName _LectureModeParticipantHandRaised_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "handRaised");
    private final static QName _GetModeratorURLResponseModeratorURL_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "moderatorURL");
    private final static QName _SearchMembersRequestLimit_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "limit");
    private final static QName _SearchMembersRequestQueryField_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "queryField");
    private final static QName _SearchMembersRequestSortBy_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "sortBy");
    private final static QName _SearchMembersRequestStart_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "start");
    private final static QName _SearchMembersRequestMemberType_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "memberType");
    private final static QName _SearchMembersRequestSortDir_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "sortDir");
    private final static QName _GetLectureModeParticipantsResponseRecorderID_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "recorderID");
    private final static QName _GetLectureModeParticipantsResponseWebcast_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "webcast");
    private final static QName _GetLectureModeParticipantsResponseRecorderName_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "recorderName");
    private final static QName _GetLectureModeParticipantsResponsePaused_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "paused");
    private final static QName _GetLectureModeParticipantsResponseLectureMode_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "lectureMode");
    private final static QName _FilterQuery_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "query");
    private final static QName _FilterDir_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "dir");
    private final static QName _GetWebcastURLResponseWebCastURL_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "webCastURL");
    private final static QName _SearchRoomsRequestRoomType_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "roomType");
    private final static QName _JoinIPCConferenceRequestReferenceNumber_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "referenceNumber");
    private final static QName _JoinIPCConferenceRequestPIN_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "PIN");
    private final static QName _LogInResponseVmaddress_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "vmaddress");
    private final static QName _LogInResponseLoctag_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "loctag");
    private final static QName _LogInResponsePak2_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "pak2");
    private final static QName _LogInResponseProxyaddress_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "proxyaddress");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rank.ibl.ws.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLectureModeParticipantsResponse }
     * 
     */
    public GetLectureModeParticipantsResponse createGetLectureModeParticipantsResponse() {
        return new GetLectureModeParticipantsResponse();
    }

    /**
     * Create an instance of {@link LectureModeParticipant }
     * 
     */
    public LectureModeParticipant createLectureModeParticipant() {
        return new LectureModeParticipant();
    }

    /**
     * Create an instance of {@link SearchMembersResponse }
     * 
     */
    public SearchMembersResponse createSearchMembersResponse() {
        return new SearchMembersResponse();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link RemoveModeratorURLResponse }
     * 
     */
    public RemoveModeratorURLResponse createRemoveModeratorURLResponse() {
        return new RemoveModeratorURLResponse();
    }

    /**
     * Create an instance of {@link StopVideoResponse }
     * 
     */
    public StopVideoResponse createStopVideoResponse() {
        return new StopVideoResponse();
    }

    /**
     * Create an instance of {@link GetRecordingProfilesResponse }
     * 
     */
    public GetRecordingProfilesResponse createGetRecordingProfilesResponse() {
        return new GetRecordingProfilesResponse();
    }

    /**
     * Create an instance of {@link Recorder }
     * 
     */
    public Recorder createRecorder() {
        return new Recorder();
    }

    /**
     * Create an instance of {@link JoinIPCConferenceRequest }
     * 
     */
    public JoinIPCConferenceRequest createJoinIPCConferenceRequest() {
        return new JoinIPCConferenceRequest();
    }

    /**
     * Create an instance of {@link DismissAllRaisedHandResponse }
     * 
     */
    public DismissAllRaisedHandResponse createDismissAllRaisedHandResponse() {
        return new DismissAllRaisedHandResponse();
    }

    /**
     * Create an instance of {@link RemoveModeratorURLRequest }
     * 
     */
    public RemoveModeratorURLRequest createRemoveModeratorURLRequest() {
        return new RemoveModeratorURLRequest();
    }

    /**
     * Create an instance of {@link ScheduledRoomCreationFault }
     * 
     */
    public ScheduledRoomCreationFault createScheduledRoomCreationFault() {
        return new ScheduledRoomCreationFault();
    }

    /**
     * Create an instance of {@link GetModeratorURLRequest }
     * 
     */
    public GetModeratorURLRequest createGetModeratorURLRequest() {
        return new GetModeratorURLRequest();
    }

    /**
     * Create an instance of {@link GenerateAuthTokenResponse }
     * 
     */
    public GenerateAuthTokenResponse createGenerateAuthTokenResponse() {
        return new GenerateAuthTokenResponse();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link StopRecordingResponse }
     * 
     */
    public StopRecordingResponse createStopRecordingResponse() {
        return new StopRecordingResponse();
    }

    /**
     * Create an instance of {@link RemovePresenterResponse }
     * 
     */
    public RemovePresenterResponse createRemovePresenterResponse() {
        return new RemovePresenterResponse();
    }

    /**
     * Create an instance of {@link MyAccountRequest }
     * 
     */
    public MyAccountRequest createMyAccountRequest() {
        return new MyAccountRequest();
    }

    /**
     * Create an instance of {@link RemoveWebcastPINRequest }
     * 
     */
    public RemoveWebcastPINRequest createRemoveWebcastPINRequest() {
        return new RemoveWebcastPINRequest();
    }

    /**
     * Create an instance of {@link VidyoReplayNotAvailableFault }
     * 
     */
    public VidyoReplayNotAvailableFault createVidyoReplayNotAvailableFault() {
        return new VidyoReplayNotAvailableFault();
    }

    /**
     * Create an instance of {@link LeaveConferenceRequest }
     * 
     */
    public LeaveConferenceRequest createLeaveConferenceRequest() {
        return new LeaveConferenceRequest();
    }

    /**
     * Create an instance of {@link RemoveFromMyContactsRequest }
     * 
     */
    public RemoveFromMyContactsRequest createRemoveFromMyContactsRequest() {
        return new RemoveFromMyContactsRequest();
    }

    /**
     * Create an instance of {@link MuteVideoClientAllResponse }
     * 
     */
    public MuteVideoClientAllResponse createMuteVideoClientAllResponse() {
        return new MuteVideoClientAllResponse();
    }

    /**
     * Create an instance of {@link SearchRequest }
     * 
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest();
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link ParticipantStatusResponse }
     * 
     */
    public ParticipantStatusResponse createParticipantStatusResponse() {
        return new ParticipantStatusResponse();
    }

    /**
     * Create an instance of {@link ParticipantStatus }
     * 
     */
    public ParticipantStatus createParticipantStatus() {
        return new ParticipantStatus();
    }

    /**
     * Create an instance of {@link FileTooLargeFault }
     * 
     */
    public FileTooLargeFault createFileTooLargeFault() {
        return new FileTooLargeFault();
    }

    /**
     * Create an instance of {@link SetEndpointDetailsRequest }
     * 
     */
    public SetEndpointDetailsRequest createSetEndpointDetailsRequest() {
        return new SetEndpointDetailsRequest();
    }

    /**
     * Create an instance of {@link EndpointFeature }
     * 
     */
    public EndpointFeature createEndpointFeature() {
        return new EndpointFeature();
    }

    /**
     * Create an instance of {@link LockRoomRequest }
     * 
     */
    public LockRoomRequest createLockRoomRequest() {
        return new LockRoomRequest();
    }

    /**
     * Create an instance of {@link GetWebcastURLResponse }
     * 
     */
    public GetWebcastURLResponse createGetWebcastURLResponse() {
        return new GetWebcastURLResponse();
    }

    /**
     * Create an instance of {@link CreateModeratorPINRequest }
     * 
     */
    public CreateModeratorPINRequest createCreateModeratorPINRequest() {
        return new CreateModeratorPINRequest();
    }

    /**
     * Create an instance of {@link JoinIPCConferenceResponse }
     * 
     */
    public JoinIPCConferenceResponse createJoinIPCConferenceResponse() {
        return new JoinIPCConferenceResponse();
    }

    /**
     * Create an instance of {@link MuteAudioClientAllRequest }
     * 
     */
    public MuteAudioClientAllRequest createMuteAudioClientAllRequest() {
        return new MuteAudioClientAllRequest();
    }

    /**
     * Create an instance of {@link StartRecordingRequest }
     * 
     */
    public StartRecordingRequest createStartRecordingRequest() {
        return new StartRecordingRequest();
    }

    /**
     * Create an instance of {@link GetPINLengthRangeRequest }
     * 
     */
    public GetPINLengthRangeRequest createGetPINLengthRangeRequest() {
        return new GetPINLengthRangeRequest();
    }

    /**
     * Create an instance of {@link UpdatePublicRoomDescriptionRequest }
     * 
     */
    public UpdatePublicRoomDescriptionRequest createUpdatePublicRoomDescriptionRequest() {
        return new UpdatePublicRoomDescriptionRequest();
    }

    /**
     * Create an instance of {@link GetModeratorURLWithTokenResponse }
     * 
     */
    public GetModeratorURLWithTokenResponse createGetModeratorURLWithTokenResponse() {
        return new GetModeratorURLWithTokenResponse();
    }

    /**
     * Create an instance of {@link GetEntityByEntityIDResponse }
     * 
     */
    public GetEntityByEntityIDResponse createGetEntityByEntityIDResponse() {
        return new GetEntityByEntityIDResponse();
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link RoomMode }
     * 
     */
    public RoomMode createRoomMode() {
        return new RoomMode();
    }

    /**
     * Create an instance of {@link ResumeRecordingRequest }
     * 
     */
    public ResumeRecordingRequest createResumeRecordingRequest() {
        return new ResumeRecordingRequest();
    }

    /**
     * Create an instance of {@link PauseRecordingResponse }
     * 
     */
    public PauseRecordingResponse createPauseRecordingResponse() {
        return new PauseRecordingResponse();
    }

    /**
     * Create an instance of {@link CreateScheduledRoomResponse }
     * 
     */
    public CreateScheduledRoomResponse createCreateScheduledRoomResponse() {
        return new CreateScheduledRoomResponse();
    }

    /**
     * Create an instance of {@link CreateWebcastPINResponse }
     * 
     */
    public CreateWebcastPINResponse createCreateWebcastPINResponse() {
        return new CreateWebcastPINResponse();
    }

    /**
     * Create an instance of {@link RemoveWebcastPINResponse }
     * 
     */
    public RemoveWebcastPINResponse createRemoveWebcastPINResponse() {
        return new RemoveWebcastPINResponse();
    }

    /**
     * Create an instance of {@link LinkEndpointResponse }
     * 
     */
    public LinkEndpointResponse createLinkEndpointResponse() {
        return new LinkEndpointResponse();
    }

    /**
     * Create an instance of {@link MuteAudioServerAllRequest }
     * 
     */
    public MuteAudioServerAllRequest createMuteAudioServerAllRequest() {
        return new MuteAudioServerAllRequest();
    }

    /**
     * Create an instance of {@link StartLectureModeResponse }
     * 
     */
    public StartLectureModeResponse createStartLectureModeResponse() {
        return new StartLectureModeResponse();
    }

    /**
     * Create an instance of {@link GetRoomProfilesResponse }
     * 
     */
    public GetRoomProfilesResponse createGetRoomProfilesResponse() {
        return new GetRoomProfilesResponse();
    }

    /**
     * Create an instance of {@link RoomProfile }
     * 
     */
    public RoomProfile createRoomProfile() {
        return new RoomProfile();
    }

    /**
     * Create an instance of {@link GetLoginAndWelcomeBannerRequest }
     * 
     */
    public GetLoginAndWelcomeBannerRequest createGetLoginAndWelcomeBannerRequest() {
        return new GetLoginAndWelcomeBannerRequest();
    }

    /**
     * Create an instance of {@link GetChangePasswordHtmlUrlWithTokenRequest }
     * 
     */
    public GetChangePasswordHtmlUrlWithTokenRequest createGetChangePasswordHtmlUrlWithTokenRequest() {
        return new GetChangePasswordHtmlUrlWithTokenRequest();
    }

    /**
     * Create an instance of {@link CreateWebcastURLResponse }
     * 
     */
    public CreateWebcastURLResponse createCreateWebcastURLResponse() {
        return new CreateWebcastURLResponse();
    }

    /**
     * Create an instance of {@link LockRoomResponse }
     * 
     */
    public LockRoomResponse createLockRoomResponse() {
        return new LockRoomResponse();
    }

    /**
     * Create an instance of {@link RemoveModeratorPINResponse }
     * 
     */
    public RemoveModeratorPINResponse createRemoveModeratorPINResponse() {
        return new RemoveModeratorPINResponse();
    }

    /**
     * Create an instance of {@link RaiseHandResponse }
     * 
     */
    public RaiseHandResponse createRaiseHandResponse() {
        return new RaiseHandResponse();
    }

    /**
     * Create an instance of {@link GetRoomProfileRequest }
     * 
     */
    public GetRoomProfileRequest createGetRoomProfileRequest() {
        return new GetRoomProfileRequest();
    }

    /**
     * Create an instance of {@link DisconnectConferenceAllResponse }
     * 
     */
    public DisconnectConferenceAllResponse createDisconnectConferenceAllResponse() {
        return new DisconnectConferenceAllResponse();
    }

    /**
     * Create an instance of {@link DismissRaisedHandResponse }
     * 
     */
    public DismissRaisedHandResponse createDismissRaisedHandResponse() {
        return new DismissRaisedHandResponse();
    }

    /**
     * Create an instance of {@link UnmuteAudioRequest }
     * 
     */
    public UnmuteAudioRequest createUnmuteAudioRequest() {
        return new UnmuteAudioRequest();
    }

    /**
     * Create an instance of {@link CancelOutboundCallRequest }
     * 
     */
    public CancelOutboundCallRequest createCancelOutboundCallRequest() {
        return new CancelOutboundCallRequest();
    }

    /**
     * Create an instance of {@link RoomAccessOptionsRequest }
     * 
     */
    public RoomAccessOptionsRequest createRoomAccessOptionsRequest() {
        return new RoomAccessOptionsRequest();
    }

    /**
     * Create an instance of {@link SilenceSpeakerServerType }
     * 
     */
    public SilenceSpeakerServerType createSilenceSpeakerServerType() {
        return new SilenceSpeakerServerType();
    }

    /**
     * Create an instance of {@link InPointToPointCallFault }
     * 
     */
    public InPointToPointCallFault createInPointToPointCallFault() {
        return new InPointToPointCallFault();
    }

    /**
     * Create an instance of {@link PublicRoomCreationFault }
     * 
     */
    public PublicRoomCreationFault createPublicRoomCreationFault() {
        return new PublicRoomCreationFault();
    }

    /**
     * Create an instance of {@link DirectCallResponse }
     * 
     */
    public DirectCallResponse createDirectCallResponse() {
        return new DirectCallResponse();
    }

    /**
     * Create an instance of {@link CreateWebcastURLRequest }
     * 
     */
    public CreateWebcastURLRequest createCreateWebcastURLRequest() {
        return new CreateWebcastURLRequest();
    }

    /**
     * Create an instance of {@link SetEndpointDetailsResponse }
     * 
     */
    public SetEndpointDetailsResponse createSetEndpointDetailsResponse() {
        return new SetEndpointDetailsResponse();
    }

    /**
     * Create an instance of {@link SetPresenterResponse }
     * 
     */
    public SetPresenterResponse createSetPresenterResponse() {
        return new SetPresenterResponse();
    }

    /**
     * Create an instance of {@link MyEndpointStatusRequest }
     * 
     */
    public MyEndpointStatusRequest createMyEndpointStatusRequest() {
        return new MyEndpointStatusRequest();
    }

    /**
     * Create an instance of {@link PauseRecordingRequest }
     * 
     */
    public PauseRecordingRequest createPauseRecordingRequest() {
        return new PauseRecordingRequest();
    }

    /**
     * Create an instance of {@link MuteAudioClientAllResponse }
     * 
     */
    public MuteAudioClientAllResponse createMuteAudioClientAllResponse() {
        return new MuteAudioClientAllResponse();
    }

    /**
     * Create an instance of {@link RemoveWebcastURLRequest }
     * 
     */
    public RemoveWebcastURLRequest createRemoveWebcastURLRequest() {
        return new RemoveWebcastURLRequest();
    }

    /**
     * Create an instance of {@link MuteVideoClientAllRequest }
     * 
     */
    public MuteVideoClientAllRequest createMuteVideoClientAllRequest() {
        return new MuteVideoClientAllRequest();
    }

    /**
     * Create an instance of {@link TestcallRoomCreationFault }
     * 
     */
    public TestcallRoomCreationFault createTestcallRoomCreationFault() {
        return new TestcallRoomCreationFault();
    }

    /**
     * Create an instance of {@link SetPresenterRequest }
     * 
     */
    public SetPresenterRequest createSetPresenterRequest() {
        return new SetPresenterRequest();
    }

    /**
     * Create an instance of {@link SetRoomProfileResponse }
     * 
     */
    public SetRoomProfileResponse createSetRoomProfileResponse() {
        return new SetRoomProfileResponse();
    }

    /**
     * Create an instance of {@link CreateRoomURLRequest }
     * 
     */
    public CreateRoomURLRequest createCreateRoomURLRequest() {
        return new CreateRoomURLRequest();
    }

    /**
     * Create an instance of {@link RemoveFromMyContactsResponse }
     * 
     */
    public RemoveFromMyContactsResponse createRemoveFromMyContactsResponse() {
        return new RemoveFromMyContactsResponse();
    }

    /**
     * Create an instance of {@link GetEntityDetailsByEntityIDRequest }
     * 
     */
    public GetEntityDetailsByEntityIDRequest createGetEntityDetailsByEntityIDRequest() {
        return new GetEntityDetailsByEntityIDRequest();
    }

    /**
     * Create an instance of {@link JoinConferenceResponse }
     * 
     */
    public JoinConferenceResponse createJoinConferenceResponse() {
        return new JoinConferenceResponse();
    }

    /**
     * Create an instance of {@link CancelOutboundCallResponse }
     * 
     */
    public CancelOutboundCallResponse createCancelOutboundCallResponse() {
        return new CancelOutboundCallResponse();
    }

    /**
     * Create an instance of {@link FeatureNotAvailableFault }
     * 
     */
    public FeatureNotAvailableFault createFeatureNotAvailableFault() {
        return new FeatureNotAvailableFault();
    }

    /**
     * Create an instance of {@link ControlMeetingFault }
     * 
     */
    public ControlMeetingFault createControlMeetingFault() {
        return new ControlMeetingFault();
    }

    /**
     * Create an instance of {@link WhatIsMyIPAddressRequest }
     * 
     */
    public WhatIsMyIPAddressRequest createWhatIsMyIPAddressRequest() {
        return new WhatIsMyIPAddressRequest();
    }

    /**
     * Create an instance of {@link GetModeratorURLResponse }
     * 
     */
    public GetModeratorURLResponse createGetModeratorURLResponse() {
        return new GetModeratorURLResponse();
    }

    /**
     * Create an instance of {@link ResumeRecordingResponse }
     * 
     */
    public ResumeRecordingResponse createResumeRecordingResponse() {
        return new ResumeRecordingResponse();
    }

    /**
     * Create an instance of {@link GetParticipantsResponse }
     * 
     */
    public GetParticipantsResponse createGetParticipantsResponse() {
        return new GetParticipantsResponse();
    }

    /**
     * Create an instance of {@link GetEntityByEntityIDRequest }
     * 
     */
    public GetEntityByEntityIDRequest createGetEntityByEntityIDRequest() {
        return new GetEntityByEntityIDRequest();
    }

    /**
     * Create an instance of {@link RemoveRoomProfileRequest }
     * 
     */
    public RemoveRoomProfileRequest createRemoveRoomProfileRequest() {
        return new RemoveRoomProfileRequest();
    }

    /**
     * Create an instance of {@link EntityDetails }
     * 
     */
    public EntityDetails createEntityDetails() {
        return new EntityDetails();
    }

    /**
     * Create an instance of {@link GetLectureModeParticipantsRequest }
     * 
     */
    public GetLectureModeParticipantsRequest createGetLectureModeParticipantsRequest() {
        return new GetLectureModeParticipantsRequest();
    }

    /**
     * Create an instance of {@link SetRoomProfileRequest }
     * 
     */
    public SetRoomProfileRequest createSetRoomProfileRequest() {
        return new SetRoomProfileRequest();
    }

    /**
     * Create an instance of {@link EndpointNotBoundFault }
     * 
     */
    public EndpointNotBoundFault createEndpointNotBoundFault() {
        return new EndpointNotBoundFault();
    }

    /**
     * Create an instance of {@link LogInRequest }
     * 
     */
    public LogInRequest createLogInRequest() {
        return new LogInRequest();
    }

    /**
     * Create an instance of {@link InvalidModeratorPINFormatFault }
     * 
     */
    public InvalidModeratorPINFormatFault createInvalidModeratorPINFormatFault() {
        return new InvalidModeratorPINFormatFault();
    }

    /**
     * Create an instance of {@link WelcomeBannerContent }
     * 
     */
    public WelcomeBannerContent createWelcomeBannerContent() {
        return new WelcomeBannerContent();
    }

    /**
     * Create an instance of {@link LoginAttempt }
     * 
     */
    public LoginAttempt createLoginAttempt() {
        return new LoginAttempt();
    }

    /**
     * Create an instance of {@link GetRecordingProfilesRequest }
     * 
     */
    public GetRecordingProfilesRequest createGetRecordingProfilesRequest() {
        return new GetRecordingProfilesRequest();
    }

    /**
     * Create an instance of {@link CreateScheduledRoomRequest }
     * 
     */
    public CreateScheduledRoomRequest createCreateScheduledRoomRequest() {
        return new CreateScheduledRoomRequest();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link StopRecordingRequest }
     * 
     */
    public StopRecordingRequest createStopRecordingRequest() {
        return new StopRecordingRequest();
    }

    /**
     * Create an instance of {@link GetPortalVersionResponse }
     * 
     */
    public GetPortalVersionResponse createGetPortalVersionResponse() {
        return new GetPortalVersionResponse();
    }

    /**
     * Create an instance of {@link InviteToConferenceResponse }
     * 
     */
    public InviteToConferenceResponse createInviteToConferenceResponse() {
        return new InviteToConferenceResponse();
    }

    /**
     * Create an instance of {@link StopLectureModeResponse }
     * 
     */
    public StopLectureModeResponse createStopLectureModeResponse() {
        return new StopLectureModeResponse();
    }

    /**
     * Create an instance of {@link GetModeratorURLWithTokenRequest }
     * 
     */
    public GetModeratorURLWithTokenRequest createGetModeratorURLWithTokenRequest() {
        return new GetModeratorURLWithTokenRequest();
    }

    /**
     * Create an instance of {@link CreateRoomURLResponse }
     * 
     */
    public CreateRoomURLResponse createCreateRoomURLResponse() {
        return new CreateRoomURLResponse();
    }

    /**
     * Create an instance of {@link AddToMyContactsResponse }
     * 
     */
    public AddToMyContactsResponse createAddToMyContactsResponse() {
        return new AddToMyContactsResponse();
    }

    /**
     * Create an instance of {@link InvalidArgumentFault }
     * 
     */
    public InvalidArgumentFault createInvalidArgumentFault() {
        return new InvalidArgumentFault();
    }

    /**
     * Create an instance of {@link CreatePublicRoomRequest }
     * 
     */
    public CreatePublicRoomRequest createCreatePublicRoomRequest() {
        return new CreatePublicRoomRequest();
    }

    /**
     * Create an instance of {@link UnlockRoomRequest }
     * 
     */
    public UnlockRoomRequest createUnlockRoomRequest() {
        return new UnlockRoomRequest();
    }

    /**
     * Create an instance of {@link MyEndpointStatusResponse }
     * 
     */
    public MyEndpointStatusResponse createMyEndpointStatusResponse() {
        return new MyEndpointStatusResponse();
    }

    /**
     * Create an instance of {@link JoinConferenceRequest }
     * 
     */
    public JoinConferenceRequest createJoinConferenceRequest() {
        return new JoinConferenceRequest();
    }

    /**
     * Create an instance of {@link DeleteRoomResponse }
     * 
     */
    public DeleteRoomResponse createDeleteRoomResponse() {
        return new DeleteRoomResponse();
    }

    /**
     * Create an instance of {@link GetConferenceIDResponse }
     * 
     */
    public GetConferenceIDResponse createGetConferenceIDResponse() {
        return new GetConferenceIDResponse();
    }

    /**
     * Create an instance of {@link SetMemberModeRequest }
     * 
     */
    public SetMemberModeRequest createSetMemberModeRequest() {
        return new SetMemberModeRequest();
    }

    /**
     * Create an instance of {@link StopLectureModeRequest }
     * 
     */
    public StopLectureModeRequest createStopLectureModeRequest() {
        return new StopLectureModeRequest();
    }

    /**
     * Create an instance of {@link GetWebcastURLRequest }
     * 
     */
    public GetWebcastURLRequest createGetWebcastURLRequest() {
        return new GetWebcastURLRequest();
    }

    /**
     * Create an instance of {@link StartVideoResponse }
     * 
     */
    public StartVideoResponse createStartVideoResponse() {
        return new StartVideoResponse();
    }

    /**
     * Create an instance of {@link UnraiseHandResponse }
     * 
     */
    public UnraiseHandResponse createUnraiseHandResponse() {
        return new UnraiseHandResponse();
    }

    /**
     * Create an instance of {@link SearchMyContactsRequest }
     * 
     */
    public SearchMyContactsRequest createSearchMyContactsRequest() {
        return new SearchMyContactsRequest();
    }

    /**
     * Create an instance of {@link RemoveRoomURLResponse }
     * 
     */
    public RemoveRoomURLResponse createRemoveRoomURLResponse() {
        return new RemoveRoomURLResponse();
    }

    /**
     * Create an instance of {@link InviteToConferenceRequest }
     * 
     */
    public InviteToConferenceRequest createInviteToConferenceRequest() {
        return new InviteToConferenceRequest();
    }

    /**
     * Create an instance of {@link StartRecordingResponse }
     * 
     */
    public StartRecordingResponse createStartRecordingResponse() {
        return new StartRecordingResponse();
    }

    /**
     * Create an instance of {@link CreatePublicRoomResponse }
     * 
     */
    public CreatePublicRoomResponse createCreatePublicRoomResponse() {
        return new CreatePublicRoomResponse();
    }

    /**
     * Create an instance of {@link MuteAudioRequest }
     * 
     */
    public MuteAudioRequest createMuteAudioRequest() {
        return new MuteAudioRequest();
    }

    /**
     * Create an instance of {@link LogoutAllOtherSessionsResponse }
     * 
     */
    public LogoutAllOtherSessionsResponse createLogoutAllOtherSessionsResponse() {
        return new LogoutAllOtherSessionsResponse();
    }

    /**
     * Create an instance of {@link UpdateLanguageResponse }
     * 
     */
    public UpdateLanguageResponse createUpdateLanguageResponse() {
        return new UpdateLanguageResponse();
    }

    /**
     * Create an instance of {@link GetUserAccountTypeResponse }
     * 
     */
    public GetUserAccountTypeResponse createGetUserAccountTypeResponse() {
        return new GetUserAccountTypeResponse();
    }

    /**
     * Create an instance of {@link RoomNotFoundFault }
     * 
     */
    public RoomNotFoundFault createRoomNotFoundFault() {
        return new RoomNotFoundFault();
    }

    /**
     * Create an instance of {@link DismissRaisedHandRequest }
     * 
     */
    public DismissRaisedHandRequest createDismissRaisedHandRequest() {
        return new DismissRaisedHandRequest();
    }

    /**
     * Create an instance of {@link CreateRoomRequest }
     * 
     */
    public CreateRoomRequest createCreateRoomRequest() {
        return new CreateRoomRequest();
    }

    /**
     * Create an instance of {@link InvalidParticipantFault }
     * 
     */
    public InvalidParticipantFault createInvalidParticipantFault() {
        return new InvalidParticipantFault();
    }

    /**
     * Create an instance of {@link CreateRoomPINRequest }
     * 
     */
    public CreateRoomPINRequest createCreateRoomPINRequest() {
        return new CreateRoomPINRequest();
    }

    /**
     * Create an instance of {@link GetLoginAndWelcomeBannerResponse }
     * 
     */
    public GetLoginAndWelcomeBannerResponse createGetLoginAndWelcomeBannerResponse() {
        return new GetLoginAndWelcomeBannerResponse();
    }

    /**
     * Create an instance of {@link LogAggregationDisabledFault }
     * 
     */
    public LogAggregationDisabledFault createLogAggregationDisabledFault() {
        return new LogAggregationDisabledFault();
    }

    /**
     * Create an instance of {@link StopVideoRequest }
     * 
     */
    public StopVideoRequest createStopVideoRequest() {
        return new StopVideoRequest();
    }

    /**
     * Create an instance of {@link GeneralFault }
     * 
     */
    public GeneralFault createGeneralFault() {
        return new GeneralFault();
    }

    /**
     * Create an instance of {@link LeaveConferenceResponse }
     * 
     */
    public LeaveConferenceResponse createLeaveConferenceResponse() {
        return new LeaveConferenceResponse();
    }

    /**
     * Create an instance of {@link UpdatePublicRoomDescriptionResponse }
     * 
     */
    public UpdatePublicRoomDescriptionResponse createUpdatePublicRoomDescriptionResponse() {
        return new UpdatePublicRoomDescriptionResponse();
    }

    /**
     * Create an instance of {@link MuteAudioLocalRequest }
     * 
     */
    public MuteAudioLocalRequest createMuteAudioLocalRequest() {
        return new MuteAudioLocalRequest();
    }

    /**
     * Create an instance of {@link LogInResponse }
     * 
     */
    public LogInResponse createLogInResponse() {
        return new LogInResponse();
    }

    /**
     * Create an instance of {@link EndpointBehaviorDataType }
     * 
     */
    public EndpointBehaviorDataType createEndpointBehaviorDataType() {
        return new EndpointBehaviorDataType();
    }

    /**
     * Create an instance of {@link UpdateLanguageRequest }
     * 
     */
    public UpdateLanguageRequest createUpdateLanguageRequest() {
        return new UpdateLanguageRequest();
    }

    /**
     * Create an instance of {@link OnetimeAccessResponse }
     * 
     */
    public OnetimeAccessResponse createOnetimeAccessResponse() {
        return new OnetimeAccessResponse();
    }

    /**
     * Create an instance of {@link CreateWebcastPINRequest }
     * 
     */
    public CreateWebcastPINRequest createCreateWebcastPINRequest() {
        return new CreateWebcastPINRequest();
    }

    /**
     * Create an instance of {@link ResourceNotAvailableFault }
     * 
     */
    public ResourceNotAvailableFault createResourceNotAvailableFault() {
        return new ResourceNotAvailableFault();
    }

    /**
     * Create an instance of {@link LinkEndpointRequest }
     * 
     */
    public LinkEndpointRequest createLinkEndpointRequest() {
        return new LinkEndpointRequest();
    }

    /**
     * Create an instance of {@link DeleteScheduledRoomRequest }
     * 
     */
    public DeleteScheduledRoomRequest createDeleteScheduledRoomRequest() {
        return new DeleteScheduledRoomRequest();
    }

    /**
     * Create an instance of {@link SearchMyContactsResponse }
     * 
     */
    public SearchMyContactsResponse createSearchMyContactsResponse() {
        return new SearchMyContactsResponse();
    }

    /**
     * Create an instance of {@link GetParticipantsRequest }
     * 
     */
    public GetParticipantsRequest createGetParticipantsRequest() {
        return new GetParticipantsRequest();
    }

    /**
     * Create an instance of {@link GetChangePasswordHtmlUrlWithTokenResponse }
     * 
     */
    public GetChangePasswordHtmlUrlWithTokenResponse createGetChangePasswordHtmlUrlWithTokenResponse() {
        return new GetChangePasswordHtmlUrlWithTokenResponse();
    }

    /**
     * Create an instance of {@link CreateRoomPINResponse }
     * 
     */
    public CreateRoomPINResponse createCreateRoomPINResponse() {
        return new CreateRoomPINResponse();
    }

    /**
     * Create an instance of {@link GetPINLengthRangeResponse }
     * 
     */
    public GetPINLengthRangeResponse createGetPINLengthRangeResponse() {
        return new GetPINLengthRangeResponse();
    }

    /**
     * Create an instance of {@link GetUserNameRequest }
     * 
     */
    public GetUserNameRequest createGetUserNameRequest() {
        return new GetUserNameRequest();
    }

    /**
     * Create an instance of {@link StartVideoRequest }
     * 
     */
    public StartVideoRequest createStartVideoRequest() {
        return new StartVideoRequest();
    }

    /**
     * Create an instance of {@link StartLectureModeRequest }
     * 
     */
    public StartLectureModeRequest createStartLectureModeRequest() {
        return new StartLectureModeRequest();
    }

    /**
     * Create an instance of {@link SearchByEmailResponse }
     * 
     */
    public SearchByEmailResponse createSearchByEmailResponse() {
        return new SearchByEmailResponse();
    }

    /**
     * Create an instance of {@link SearchByEmailRequest }
     * 
     */
    public SearchByEmailRequest createSearchByEmailRequest() {
        return new SearchByEmailRequest();
    }

    /**
     * Create an instance of {@link MyAccountResponse }
     * 
     */
    public MyAccountResponse createMyAccountResponse() {
        return new MyAccountResponse();
    }

    /**
     * Create an instance of {@link CreateModeratorPINResponse }
     * 
     */
    public CreateModeratorPINResponse createCreateModeratorPINResponse() {
        return new CreateModeratorPINResponse();
    }

    /**
     * Create an instance of {@link RoomAccessOptionsResponse }
     * 
     */
    public RoomAccessOptionsResponse createRoomAccessOptionsResponse() {
        return new RoomAccessOptionsResponse();
    }

    /**
     * Create an instance of {@link RoomAccessOption }
     * 
     */
    public RoomAccessOption createRoomAccessOption() {
        return new RoomAccessOption();
    }

    /**
     * Create an instance of {@link RemoveRoomPINRequest }
     * 
     */
    public RemoveRoomPINRequest createRemoveRoomPINRequest() {
        return new RemoveRoomPINRequest();
    }

    /**
     * Create an instance of {@link GenerateAuthTokenRequest }
     * 
     */
    public GenerateAuthTokenRequest createGenerateAuthTokenRequest() {
        return new GenerateAuthTokenRequest();
    }

    /**
     * Create an instance of {@link DeleteScheduledRoomResponse }
     * 
     */
    public DeleteScheduledRoomResponse createDeleteScheduledRoomResponse() {
        return new DeleteScheduledRoomResponse();
    }

    /**
     * Create an instance of {@link InvalidFault }
     * 
     */
    public InvalidFault createInvalidFault() {
        return new InvalidFault();
    }

    /**
     * Create an instance of {@link RemovePresenterRequest }
     * 
     */
    public RemovePresenterRequest createRemovePresenterRequest() {
        return new RemovePresenterRequest();
    }

    /**
     * Create an instance of {@link UpdatePasswordResponse }
     * 
     */
    public UpdatePasswordResponse createUpdatePasswordResponse() {
        return new UpdatePasswordResponse();
    }

    /**
     * Create an instance of {@link GetLogAggregationServerRequest }
     * 
     */
    public GetLogAggregationServerRequest createGetLogAggregationServerRequest() {
        return new GetLogAggregationServerRequest();
    }

    /**
     * Create an instance of {@link GetEntityDetailsByEntityIDResponse }
     * 
     */
    public GetEntityDetailsByEntityIDResponse createGetEntityDetailsByEntityIDResponse() {
        return new GetEntityDetailsByEntityIDResponse();
    }

    /**
     * Create an instance of {@link UnlockRoomResponse }
     * 
     */
    public UnlockRoomResponse createUnlockRoomResponse() {
        return new UnlockRoomResponse();
    }

    /**
     * Create an instance of {@link GetInviteContentRequest }
     * 
     */
    public GetInviteContentRequest createGetInviteContentRequest() {
        return new GetInviteContentRequest();
    }

    /**
     * Create an instance of {@link SearchByEntityIDRequest }
     * 
     */
    public SearchByEntityIDRequest createSearchByEntityIDRequest() {
        return new SearchByEntityIDRequest();
    }

    /**
     * Create an instance of {@link NotAllowedToCreateFault }
     * 
     */
    public NotAllowedToCreateFault createNotAllowedToCreateFault() {
        return new NotAllowedToCreateFault();
    }

    /**
     * Create an instance of {@link CreateModeratorURLResponse }
     * 
     */
    public CreateModeratorURLResponse createCreateModeratorURLResponse() {
        return new CreateModeratorURLResponse();
    }

    /**
     * Create an instance of {@link RemoveRoomPINResponse }
     * 
     */
    public RemoveRoomPINResponse createRemoveRoomPINResponse() {
        return new RemoveRoomPINResponse();
    }

    /**
     * Create an instance of {@link GetEntityByRoomKeyRequest }
     * 
     */
    public GetEntityByRoomKeyRequest createGetEntityByRoomKeyRequest() {
        return new GetEntityByRoomKeyRequest();
    }

    /**
     * Create an instance of {@link DirectCallRequest }
     * 
     */
    public DirectCallRequest createDirectCallRequest() {
        return new DirectCallRequest();
    }

    /**
     * Create an instance of {@link SetThumbnailPhotoRequest }
     * 
     */
    public SetThumbnailPhotoRequest createSetThumbnailPhotoRequest() {
        return new SetThumbnailPhotoRequest();
    }

    /**
     * Create an instance of {@link RemoveModeratorPINRequest }
     * 
     */
    public RemoveModeratorPINRequest createRemoveModeratorPINRequest() {
        return new RemoveModeratorPINRequest();
    }

    /**
     * Create an instance of {@link MuteAudioServerAllResponse }
     * 
     */
    public MuteAudioServerAllResponse createMuteAudioServerAllResponse() {
        return new MuteAudioServerAllResponse();
    }

    /**
     * Create an instance of {@link MuteVideoServerAllResponse }
     * 
     */
    public MuteVideoServerAllResponse createMuteVideoServerAllResponse() {
        return new MuteVideoServerAllResponse();
    }

    /**
     * Create an instance of {@link AddToMyContactsRequest }
     * 
     */
    public AddToMyContactsRequest createAddToMyContactsRequest() {
        return new AddToMyContactsRequest();
    }

    /**
     * Create an instance of {@link RemoveWebcastURLResponse }
     * 
     */
    public RemoveWebcastURLResponse createRemoveWebcastURLResponse() {
        return new RemoveWebcastURLResponse();
    }

    /**
     * Create an instance of {@link MuteVideoRequest }
     * 
     */
    public MuteVideoRequest createMuteVideoRequest() {
        return new MuteVideoRequest();
    }

    /**
     * Create an instance of {@link NotAllowedThumbnailPhotoFault }
     * 
     */
    public NotAllowedThumbnailPhotoFault createNotAllowedThumbnailPhotoFault() {
        return new NotAllowedThumbnailPhotoFault();
    }

    /**
     * Create an instance of {@link MuteAudioResponse }
     * 
     */
    public MuteAudioResponse createMuteAudioResponse() {
        return new MuteAudioResponse();
    }

    /**
     * Create an instance of {@link SearchRoomsResponse }
     * 
     */
    public SearchRoomsResponse createSearchRoomsResponse() {
        return new SearchRoomsResponse();
    }

    /**
     * Create an instance of {@link SetMemberModeResponse }
     * 
     */
    public SetMemberModeResponse createSetMemberModeResponse() {
        return new SetMemberModeResponse();
    }

    /**
     * Create an instance of {@link InvalidConferenceFault }
     * 
     */
    public InvalidConferenceFault createInvalidConferenceFault() {
        return new InvalidConferenceFault();
    }

    /**
     * Create an instance of {@link LogOutResponse }
     * 
     */
    public LogOutResponse createLogOutResponse() {
        return new LogOutResponse();
    }

    /**
     * Create an instance of {@link DismissAllRaisedHandRequest }
     * 
     */
    public DismissAllRaisedHandRequest createDismissAllRaisedHandRequest() {
        return new DismissAllRaisedHandRequest();
    }

    /**
     * Create an instance of {@link GetLogAggregationServerResponse }
     * 
     */
    public GetLogAggregationServerResponse createGetLogAggregationServerResponse() {
        return new GetLogAggregationServerResponse();
    }

    /**
     * Create an instance of {@link UpdatePasswordRequest }
     * 
     */
    public UpdatePasswordRequest createUpdatePasswordRequest() {
        return new UpdatePasswordRequest();
    }

    /**
     * Create an instance of {@link LogOutRequest }
     * 
     */
    public LogOutRequest createLogOutRequest() {
        return new LogOutRequest();
    }

    /**
     * Create an instance of {@link SearchRoomsRequest }
     * 
     */
    public SearchRoomsRequest createSearchRoomsRequest() {
        return new SearchRoomsRequest();
    }

    /**
     * Create an instance of {@link RemoveRoomProfileResponse }
     * 
     */
    public RemoveRoomProfileResponse createRemoveRoomProfileResponse() {
        return new RemoveRoomProfileResponse();
    }

    /**
     * Create an instance of {@link PortalFeature }
     * 
     */
    public PortalFeature createPortalFeature() {
        return new PortalFeature();
    }

    /**
     * Create an instance of {@link PrefixNotConfiguredFault }
     * 
     */
    public PrefixNotConfiguredFault createPrefixNotConfiguredFault() {
        return new PrefixNotConfiguredFault();
    }

    /**
     * Create an instance of {@link PublicRoomDescUpdationFault }
     * 
     */
    public PublicRoomDescUpdationFault createPublicRoomDescUpdationFault() {
        return new PublicRoomDescUpdationFault();
    }

    /**
     * Create an instance of {@link MuteVideoServerAllRequest }
     * 
     */
    public MuteVideoServerAllRequest createMuteVideoServerAllRequest() {
        return new MuteVideoServerAllRequest();
    }

    /**
     * Create an instance of {@link WhatIsMyIPAddressResponse }
     * 
     */
    public WhatIsMyIPAddressResponse createWhatIsMyIPAddressResponse() {
        return new WhatIsMyIPAddressResponse();
    }

    /**
     * Create an instance of {@link GetActiveSessionsResponse }
     * 
     */
    public GetActiveSessionsResponse createGetActiveSessionsResponse() {
        return new GetActiveSessionsResponse();
    }

    /**
     * Create an instance of {@link GetEntityByRoomKeyResponse }
     * 
     */
    public GetEntityByRoomKeyResponse createGetEntityByRoomKeyResponse() {
        return new GetEntityByRoomKeyResponse();
    }

    /**
     * Create an instance of {@link UnmuteAudioResponse }
     * 
     */
    public UnmuteAudioResponse createUnmuteAudioResponse() {
        return new UnmuteAudioResponse();
    }

    /**
     * Create an instance of {@link SearchMembersRequest }
     * 
     */
    public SearchMembersRequest createSearchMembersRequest() {
        return new SearchMembersRequest();
    }

    /**
     * Create an instance of {@link GetVidyoReplayLibraryResponse }
     * 
     */
    public GetVidyoReplayLibraryResponse createGetVidyoReplayLibraryResponse() {
        return new GetVidyoReplayLibraryResponse();
    }

    /**
     * Create an instance of {@link GetUserAccountTypeRequest }
     * 
     */
    public GetUserAccountTypeRequest createGetUserAccountTypeRequest() {
        return new GetUserAccountTypeRequest();
    }

    /**
     * Create an instance of {@link SetThumbnailPhotoResponse }
     * 
     */
    public SetThumbnailPhotoResponse createSetThumbnailPhotoResponse() {
        return new SetThumbnailPhotoResponse();
    }

    /**
     * Create an instance of {@link CreateTestcallRoomResponse }
     * 
     */
    public CreateTestcallRoomResponse createCreateTestcallRoomResponse() {
        return new CreateTestcallRoomResponse();
    }

    /**
     * Create an instance of {@link DisconnectConferenceAllRequest }
     * 
     */
    public DisconnectConferenceAllRequest createDisconnectConferenceAllRequest() {
        return new DisconnectConferenceAllRequest();
    }

    /**
     * Create an instance of {@link AccessRestrictedFault }
     * 
     */
    public AccessRestrictedFault createAccessRestrictedFault() {
        return new AccessRestrictedFault();
    }

    /**
     * Create an instance of {@link CreateModeratorURLRequest }
     * 
     */
    public CreateModeratorURLRequest createCreateModeratorURLRequest() {
        return new CreateModeratorURLRequest();
    }

    /**
     * Create an instance of {@link PortalPrefixResponse }
     * 
     */
    public PortalPrefixResponse createPortalPrefixResponse() {
        return new PortalPrefixResponse();
    }

    /**
     * Create an instance of {@link SearchByEntityIDResponse }
     * 
     */
    public SearchByEntityIDResponse createSearchByEntityIDResponse() {
        return new SearchByEntityIDResponse();
    }

    /**
     * Create an instance of {@link GetPortalFeaturesResponse }
     * 
     */
    public GetPortalFeaturesResponse createGetPortalFeaturesResponse() {
        return new GetPortalFeaturesResponse();
    }

    /**
     * Create an instance of {@link CreateRoomResponse }
     * 
     */
    public CreateRoomResponse createCreateRoomResponse() {
        return new CreateRoomResponse();
    }

    /**
     * Create an instance of {@link NotLicensedFault }
     * 
     */
    public NotLicensedFault createNotLicensedFault() {
        return new NotLicensedFault();
    }

    /**
     * Create an instance of {@link GetInviteContentResponse }
     * 
     */
    public GetInviteContentResponse createGetInviteContentResponse() {
        return new GetInviteContentResponse();
    }

    /**
     * Create an instance of {@link ConferenceLockedFault }
     * 
     */
    public ConferenceLockedFault createConferenceLockedFault() {
        return new ConferenceLockedFault();
    }

    /**
     * Create an instance of {@link DeleteRoomRequest }
     * 
     */
    public DeleteRoomRequest createDeleteRoomRequest() {
        return new DeleteRoomRequest();
    }

    /**
     * Create an instance of {@link SeatLicenseExpiredFault }
     * 
     */
    public SeatLicenseExpiredFault createSeatLicenseExpiredFault() {
        return new SeatLicenseExpiredFault();
    }

    /**
     * Create an instance of {@link WrongPINFault }
     * 
     */
    public WrongPINFault createWrongPINFault() {
        return new WrongPINFault();
    }

    /**
     * Create an instance of {@link RemoveRoomURLRequest }
     * 
     */
    public RemoveRoomURLRequest createRemoveRoomURLRequest() {
        return new RemoveRoomURLRequest();
    }

    /**
     * Create an instance of {@link GetVidyoReplayLibraryRequest }
     * 
     */
    public GetVidyoReplayLibraryRequest createGetVidyoReplayLibraryRequest() {
        return new GetVidyoReplayLibraryRequest();
    }

    /**
     * Create an instance of {@link GetPortalVersionRequest }
     * 
     */
    public GetPortalVersionRequest createGetPortalVersionRequest() {
        return new GetPortalVersionRequest();
    }

    /**
     * Create an instance of {@link PrefixNotConfiguredException }
     * 
     */
    public PrefixNotConfiguredException createPrefixNotConfiguredException() {
        return new PrefixNotConfiguredException();
    }

    /**
     * Create an instance of {@link GetUserNameResponse }
     * 
     */
    public GetUserNameResponse createGetUserNameResponse() {
        return new GetUserNameResponse();
    }

    /**
     * Create an instance of {@link GetRoomProfileResponse }
     * 
     */
    public GetRoomProfileResponse createGetRoomProfileResponse() {
        return new GetRoomProfileResponse();
    }

    /**
     * Create an instance of {@link GetConferenceIDRequest }
     * 
     */
    public GetConferenceIDRequest createGetConferenceIDRequest() {
        return new GetConferenceIDRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "GetRoomProfilesRequest")
    public JAXBElement<Object> createGetRoomProfilesRequest(Object value) {
        return new JAXBElement<Object>(_GetRoomProfilesRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "RaiseHandRequest")
    public JAXBElement<Object> createRaiseHandRequest(Object value) {
        return new JAXBElement<Object>(_RaiseHandRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "GetPortalFeaturesRequest")
    public JAXBElement<Object> createGetPortalFeaturesRequest(Object value) {
        return new JAXBElement<Object>(_GetPortalFeaturesRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SilenceSpeakerServerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "SilenceSpeakerServerRequest")
    public JAXBElement<SilenceSpeakerServerType> createSilenceSpeakerServerRequest(SilenceSpeakerServerType value) {
        return new JAXBElement<SilenceSpeakerServerType>(_SilenceSpeakerServerRequest_QNAME, SilenceSpeakerServerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "GetActiveSessionsRequest")
    public JAXBElement<Object> createGetActiveSessionsRequest(Object value) {
        return new JAXBElement<Object>(_GetActiveSessionsRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "UnraiseHandRequest")
    public JAXBElement<Object> createUnraiseHandRequest(Object value) {
        return new JAXBElement<Object>(_UnraiseHandRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "RoomStatus")
    public JAXBElement<String> createRoomStatus(String value) {
        return new JAXBElement<String>(_RoomStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "PortalPrefixRequest")
    public JAXBElement<Object> createPortalPrefixRequest(Object value) {
        return new JAXBElement<Object>(_PortalPrefixRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "MemberStatus")
    public JAXBElement<String> createMemberStatus(String value) {
        return new JAXBElement<String>(_MemberStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "MemberMode")
    public JAXBElement<String> createMemberMode(String value) {
        return new JAXBElement<String>(_MemberMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "OK")
    public JAXBElement<String> createOK(String value) {
        return new JAXBElement<String>(_OK_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "LogoutAllOtherSessionsRequest")
    public JAXBElement<Object> createLogoutAllOtherSessionsRequest(Object value) {
        return new JAXBElement<Object>(_LogoutAllOtherSessionsRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SilenceSpeakerServerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "SilenceSpeakerServerAllResponse")
    public JAXBElement<SilenceSpeakerServerType> createSilenceSpeakerServerAllResponse(SilenceSpeakerServerType value) {
        return new JAXBElement<SilenceSpeakerServerType>(_SilenceSpeakerServerAllResponse_QNAME, SilenceSpeakerServerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SilenceSpeakerServerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "SilenceSpeakerServerAllRequest")
    public JAXBElement<SilenceSpeakerServerType> createSilenceSpeakerServerAllRequest(SilenceSpeakerServerType value) {
        return new JAXBElement<SilenceSpeakerServerType>(_SilenceSpeakerServerAllRequest_QNAME, SilenceSpeakerServerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "ErrorMessage")
    public JAXBElement<String> createErrorMessage(String value) {
        return new JAXBElement<String>(_ErrorMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "OnetimeAccessRequest")
    public JAXBElement<Object> createOnetimeAccessRequest(Object value) {
        return new JAXBElement<Object>(_OnetimeAccessRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "Language")
    public JAXBElement<String> createLanguage(String value) {
        return new JAXBElement<String>(_Language_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SilenceSpeakerServerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "SilenceSpeakerServerResponse")
    public JAXBElement<SilenceSpeakerServerType> createSilenceSpeakerServerResponse(SilenceSpeakerServerType value) {
        return new JAXBElement<SilenceSpeakerServerType>(_SilenceSpeakerServerResponse_QNAME, SilenceSpeakerServerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "CreateTestcallRoomRequest")
    public JAXBElement<Object> createCreateTestcallRoomRequest(Object value) {
        return new JAXBElement<Object>(_CreateTestcallRoomRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "EntityType")
    public JAXBElement<String> createEntityType(String value) {
        return new JAXBElement<String>(_EntityType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "ClientType")
    public JAXBElement<String> createClientType(String value) {
        return new JAXBElement<String>(_ClientType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = MuteVideoClientAllRequest.class)
    public JAXBElement<String> createMuteVideoClientAllRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, MuteVideoClientAllRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = MuteAudioServerAllRequest.class)
    public JAXBElement<String> createMuteAudioServerAllRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, MuteAudioServerAllRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = MuteVideoServerAllRequest.class)
    public JAXBElement<String> createMuteVideoServerAllRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, MuteVideoServerAllRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = RemovePresenterRequest.class)
    public JAXBElement<String> createRemovePresenterRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, RemovePresenterRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = LeaveConferenceRequest.class)
    public JAXBElement<String> createLeaveConferenceRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, LeaveConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "roomPIN", scope = RoomMode.class)
    public JAXBElement<String> createRoomModeRoomPIN(String value) {
        return new JAXBElement<String>(_RoomModeRoomPIN_QNAME, String.class, RoomMode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "roomURL", scope = RoomMode.class)
    public JAXBElement<String> createRoomModeRoomURL(String value) {
        return new JAXBElement<String>(_RoomModeRoomURL_QNAME, String.class, RoomMode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "hasModeratorPIN", scope = RoomMode.class)
    public JAXBElement<Boolean> createRoomModeHasModeratorPIN(Boolean value) {
        return new JAXBElement<Boolean>(_RoomModeHasModeratorPIN_QNAME, Boolean.class, RoomMode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = RoomMode.class)
    public JAXBElement<String> createRoomModeModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, RoomMode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = StopLectureModeRequest.class)
    public JAXBElement<String> createStopLectureModeRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, StopLectureModeRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = ResumeRecordingRequest.class)
    public JAXBElement<String> createResumeRecordingRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, ResumeRecordingRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "loginBannerText", scope = GetLoginAndWelcomeBannerResponse.class)
    public JAXBElement<String> createGetLoginAndWelcomeBannerResponseLoginBannerText(String value) {
        return new JAXBElement<String>(_GetLoginAndWelcomeBannerResponseLoginBannerText_QNAME, String.class, GetLoginAndWelcomeBannerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = MuteVideoRequest.class)
    public JAXBElement<String> createMuteVideoRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, MuteVideoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = DismissAllRaisedHandRequest.class)
    public JAXBElement<String> createDismissAllRaisedHandRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, DismissAllRaisedHandRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "title", scope = Member.class)
    public JAXBElement<String> createMemberTitle(String value) {
        return new JAXBElement<String>(_MemberTitle_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "instantMessagerID", scope = Member.class)
    public JAXBElement<String> createMemberInstantMessagerID(String value) {
        return new JAXBElement<String>(_MemberInstantMessagerID_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "thumbnailUpdateTime", scope = Member.class)
    public JAXBElement<XMLGregorianCalendar> createMemberThumbnailUpdateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MemberThumbnailUpdateTime_QNAME, XMLGregorianCalendar.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "department", scope = Member.class)
    public JAXBElement<String> createMemberDepartment(String value) {
        return new JAXBElement<String>(_MemberDepartment_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "location", scope = Member.class)
    public JAXBElement<String> createMemberLocation(String value) {
        return new JAXBElement<String>(_MemberLocation_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone2", scope = Member.class)
    public JAXBElement<String> createMemberPhone2(String value) {
        return new JAXBElement<String>(_MemberPhone2_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone3", scope = Member.class)
    public JAXBElement<String> createMemberPhone3(String value) {
        return new JAXBElement<String>(_MemberPhone3_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone1", scope = Member.class)
    public JAXBElement<String> createMemberPhone1(String value) {
        return new JAXBElement<String>(_MemberPhone1_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = StartLectureModeRequest.class)
    public JAXBElement<String> createStartLectureModeRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, StartLectureModeRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = MuteAudioClientAllRequest.class)
    public JAXBElement<String> createMuteAudioClientAllRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, MuteAudioClientAllRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "conferenceID", scope = GetConferenceIDResponse.class)
    public JAXBElement<String> createGetConferenceIDResponseConferenceID(String value) {
        return new JAXBElement<String>(_GetConferenceIDResponseConferenceID_QNAME, String.class, GetConferenceIDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "title", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsTitle(String value) {
        return new JAXBElement<String>(_MemberTitle_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "instantMessagerID", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsInstantMessagerID(String value) {
        return new JAXBElement<String>(_MemberInstantMessagerID_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "thumbnailUpdateTime", scope = EntityDetails.class)
    public JAXBElement<XMLGregorianCalendar> createEntityDetailsThumbnailUpdateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MemberThumbnailUpdateTime_QNAME, XMLGregorianCalendar.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "department", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsDepartment(String value) {
        return new JAXBElement<String>(_MemberDepartment_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "audio", scope = EntityDetails.class)
    public JAXBElement<Boolean> createEntityDetailsAudio(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAudio_QNAME, Boolean.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "tenant", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsTenant(String value) {
        return new JAXBElement<String>(_EntityDetailsTenant_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "ownerID", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsOwnerID(String value) {
        return new JAXBElement<String>(_EntityDetailsOwnerID_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "location", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsLocation(String value) {
        return new JAXBElement<String>(_MemberLocation_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone2", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsPhone2(String value) {
        return new JAXBElement<String>(_MemberPhone2_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone3", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsPhone3(String value) {
        return new JAXBElement<String>(_MemberPhone3_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "participantID", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsParticipantID(String value) {
        return new JAXBElement<String>(_EntityDetailsParticipantID_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "emailAddress", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsEmailAddress(String value) {
        return new JAXBElement<String>(_EntityDetailsEmailAddress_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "video", scope = EntityDetails.class)
    public JAXBElement<Boolean> createEntityDetailsVideo(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsVideo_QNAME, Boolean.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "appshare", scope = EntityDetails.class)
    public JAXBElement<Boolean> createEntityDetailsAppshare(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAppshare_QNAME, Boolean.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone1", scope = EntityDetails.class)
    public JAXBElement<String> createEntityDetailsPhone1(String value) {
        return new JAXBElement<String>(_MemberPhone1_QNAME, String.class, EntityDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "welcomeBannerText", scope = WelcomeBannerContent.class)
    public JAXBElement<String> createWelcomeBannerContentWelcomeBannerText(String value) {
        return new JAXBElement<String>(_WelcomeBannerContentWelcomeBannerText_QNAME, String.class, WelcomeBannerContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = GetParticipantsRequest.class)
    public JAXBElement<String> createGetParticipantsRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, GetParticipantsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = PauseRecordingRequest.class)
    public JAXBElement<String> createPauseRecordingRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, PauseRecordingRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = UnmuteAudioRequest.class)
    public JAXBElement<String> createUnmuteAudioRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, UnmuteAudioRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "audio", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantAudio(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAudio_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "presenter", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantPresenter(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantPresenter_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "video", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantVideo(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsVideo_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "handRaised", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantHandRaised(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantHandRaised_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "appshare", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantAppshare(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAppshare_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorURL", scope = GetModeratorURLResponse.class)
    public JAXBElement<String> createGetModeratorURLResponseModeratorURL(String value) {
        return new JAXBElement<String>(_GetModeratorURLResponseModeratorURL_QNAME, String.class, GetModeratorURLResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = LockRoomRequest.class)
    public JAXBElement<String> createLockRoomRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, LockRoomRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "limit", scope = SearchMembersRequest.class)
    public JAXBElement<BigInteger> createSearchMembersRequestLimit(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchMembersRequestLimit_QNAME, BigInteger.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "queryField", scope = SearchMembersRequest.class)
    public JAXBElement<String> createSearchMembersRequestQueryField(String value) {
        return new JAXBElement<String>(_SearchMembersRequestQueryField_QNAME, String.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "sortBy", scope = SearchMembersRequest.class)
    public JAXBElement<String> createSearchMembersRequestSortBy(String value) {
        return new JAXBElement<String>(_SearchMembersRequestSortBy_QNAME, String.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "start", scope = SearchMembersRequest.class)
    public JAXBElement<BigInteger> createSearchMembersRequestStart(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchMembersRequestStart_QNAME, BigInteger.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "memberType", scope = SearchMembersRequest.class)
    public JAXBElement<String> createSearchMembersRequestMemberType(String value) {
        return new JAXBElement<String>(_SearchMembersRequestMemberType_QNAME, String.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "sortDir", scope = SearchMembersRequest.class)
    public JAXBElement<SortDir> createSearchMembersRequestSortDir(SortDir value) {
        return new JAXBElement<SortDir>(_SearchMembersRequestSortDir_QNAME, SortDir.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = MuteAudioRequest.class)
    public JAXBElement<String> createMuteAudioRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, MuteAudioRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = InviteToConferenceRequest.class)
    public JAXBElement<String> createInviteToConferenceRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, InviteToConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "recorderID", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Integer> createGetLectureModeParticipantsResponseRecorderID(Integer value) {
        return new JAXBElement<Integer>(_GetLectureModeParticipantsResponseRecorderID_QNAME, Integer.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "webcast", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Boolean> createGetLectureModeParticipantsResponseWebcast(Boolean value) {
        return new JAXBElement<Boolean>(_GetLectureModeParticipantsResponseWebcast_QNAME, Boolean.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "recorderName", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<String> createGetLectureModeParticipantsResponseRecorderName(String value) {
        return new JAXBElement<String>(_GetLectureModeParticipantsResponseRecorderName_QNAME, String.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "paused", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Boolean> createGetLectureModeParticipantsResponsePaused(Boolean value) {
        return new JAXBElement<Boolean>(_GetLectureModeParticipantsResponsePaused_QNAME, Boolean.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "lectureMode", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Boolean> createGetLectureModeParticipantsResponseLectureMode(Boolean value) {
        return new JAXBElement<Boolean>(_GetLectureModeParticipantsResponseLectureMode_QNAME, Boolean.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "limit", scope = Filter.class)
    public JAXBElement<Integer> createFilterLimit(Integer value) {
        return new JAXBElement<Integer>(_SearchMembersRequestLimit_QNAME, Integer.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "query", scope = Filter.class)
    public JAXBElement<String> createFilterQuery(String value) {
        return new JAXBElement<String>(_FilterQuery_QNAME, String.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "sortBy", scope = Filter.class)
    public JAXBElement<String> createFilterSortBy(String value) {
        return new JAXBElement<String>(_SearchMembersRequestSortBy_QNAME, String.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "dir", scope = Filter.class)
    public JAXBElement<SortDir> createFilterDir(SortDir value) {
        return new JAXBElement<SortDir>(_FilterDir_QNAME, SortDir.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "start", scope = Filter.class)
    public JAXBElement<Integer> createFilterStart(Integer value) {
        return new JAXBElement<Integer>(_SearchMembersRequestStart_QNAME, Integer.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "audio", scope = ParticipantStatus.class)
    public JAXBElement<Boolean> createParticipantStatusAudio(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAudio_QNAME, Boolean.class, ParticipantStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "video", scope = ParticipantStatus.class)
    public JAXBElement<Boolean> createParticipantStatusVideo(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsVideo_QNAME, Boolean.class, ParticipantStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "appshare", scope = ParticipantStatus.class)
    public JAXBElement<Boolean> createParticipantStatusAppshare(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAppshare_QNAME, Boolean.class, ParticipantStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = UnlockRoomRequest.class)
    public JAXBElement<String> createUnlockRoomRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, UnlockRoomRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = StartRecordingRequest.class)
    public JAXBElement<String> createStartRecordingRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, StartRecordingRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "title", scope = Entity.class)
    public JAXBElement<String> createEntityTitle(String value) {
        return new JAXBElement<String>(_MemberTitle_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "instantMessagerID", scope = Entity.class)
    public JAXBElement<String> createEntityInstantMessagerID(String value) {
        return new JAXBElement<String>(_MemberInstantMessagerID_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "thumbnailUpdateTime", scope = Entity.class)
    public JAXBElement<XMLGregorianCalendar> createEntityThumbnailUpdateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MemberThumbnailUpdateTime_QNAME, XMLGregorianCalendar.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "department", scope = Entity.class)
    public JAXBElement<String> createEntityDepartment(String value) {
        return new JAXBElement<String>(_MemberDepartment_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "audio", scope = Entity.class)
    public JAXBElement<Boolean> createEntityAudio(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAudio_QNAME, Boolean.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "tenant", scope = Entity.class)
    public JAXBElement<String> createEntityTenant(String value) {
        return new JAXBElement<String>(_EntityDetailsTenant_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "ownerID", scope = Entity.class)
    public JAXBElement<String> createEntityOwnerID(String value) {
        return new JAXBElement<String>(_EntityDetailsOwnerID_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "location", scope = Entity.class)
    public JAXBElement<String> createEntityLocation(String value) {
        return new JAXBElement<String>(_MemberLocation_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone2", scope = Entity.class)
    public JAXBElement<String> createEntityPhone2(String value) {
        return new JAXBElement<String>(_MemberPhone2_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone3", scope = Entity.class)
    public JAXBElement<String> createEntityPhone3(String value) {
        return new JAXBElement<String>(_MemberPhone3_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "participantID", scope = Entity.class)
    public JAXBElement<String> createEntityParticipantID(String value) {
        return new JAXBElement<String>(_EntityDetailsParticipantID_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "emailAddress", scope = Entity.class)
    public JAXBElement<String> createEntityEmailAddress(String value) {
        return new JAXBElement<String>(_EntityDetailsEmailAddress_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "video", scope = Entity.class)
    public JAXBElement<Boolean> createEntityVideo(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsVideo_QNAME, Boolean.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "appshare", scope = Entity.class)
    public JAXBElement<Boolean> createEntityAppshare(Boolean value) {
        return new JAXBElement<Boolean>(_EntityDetailsAppshare_QNAME, Boolean.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "phone1", scope = Entity.class)
    public JAXBElement<String> createEntityPhone1(String value) {
        return new JAXBElement<String>(_MemberPhone1_QNAME, String.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = MuteAudioLocalRequest.class)
    public JAXBElement<String> createMuteAudioLocalRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, MuteAudioLocalRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "recorderID", scope = GetParticipantsResponse.class)
    public JAXBElement<Integer> createGetParticipantsResponseRecorderID(Integer value) {
        return new JAXBElement<Integer>(_GetLectureModeParticipantsResponseRecorderID_QNAME, Integer.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "webcast", scope = GetParticipantsResponse.class)
    public JAXBElement<Boolean> createGetParticipantsResponseWebcast(Boolean value) {
        return new JAXBElement<Boolean>(_GetLectureModeParticipantsResponseWebcast_QNAME, Boolean.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "recorderName", scope = GetParticipantsResponse.class)
    public JAXBElement<String> createGetParticipantsResponseRecorderName(String value) {
        return new JAXBElement<String>(_GetLectureModeParticipantsResponseRecorderName_QNAME, String.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "paused", scope = GetParticipantsResponse.class)
    public JAXBElement<Boolean> createGetParticipantsResponsePaused(Boolean value) {
        return new JAXBElement<Boolean>(_GetLectureModeParticipantsResponsePaused_QNAME, Boolean.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = DismissRaisedHandRequest.class)
    public JAXBElement<String> createDismissRaisedHandRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, DismissRaisedHandRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = StartVideoRequest.class)
    public JAXBElement<String> createStartVideoRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, StartVideoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "webCastURL", scope = GetWebcastURLResponse.class)
    public JAXBElement<String> createGetWebcastURLResponseWebCastURL(String value) {
        return new JAXBElement<String>(_GetWebcastURLResponseWebCastURL_QNAME, String.class, GetWebcastURLResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "limit", scope = SearchRoomsRequest.class)
    public JAXBElement<BigInteger> createSearchRoomsRequestLimit(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchMembersRequestLimit_QNAME, BigInteger.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "queryField", scope = SearchRoomsRequest.class)
    public JAXBElement<String> createSearchRoomsRequestQueryField(String value) {
        return new JAXBElement<String>(_SearchMembersRequestQueryField_QNAME, String.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "sortBy", scope = SearchRoomsRequest.class)
    public JAXBElement<String> createSearchRoomsRequestSortBy(String value) {
        return new JAXBElement<String>(_SearchMembersRequestSortBy_QNAME, String.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "start", scope = SearchRoomsRequest.class)
    public JAXBElement<BigInteger> createSearchRoomsRequestStart(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchMembersRequestStart_QNAME, BigInteger.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "roomType", scope = SearchRoomsRequest.class)
    public JAXBElement<String> createSearchRoomsRequestRoomType(String value) {
        return new JAXBElement<String>(_SearchRoomsRequestRoomType_QNAME, String.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "sortDir", scope = SearchRoomsRequest.class)
    public JAXBElement<SortDir> createSearchRoomsRequestSortDir(SortDir value) {
        return new JAXBElement<SortDir>(_SearchMembersRequestSortDir_QNAME, SortDir.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "referenceNumber", scope = JoinIPCConferenceRequest.class)
    public JAXBElement<String> createJoinIPCConferenceRequestReferenceNumber(String value) {
        return new JAXBElement<String>(_JoinIPCConferenceRequestReferenceNumber_QNAME, String.class, JoinIPCConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "PIN", scope = JoinIPCConferenceRequest.class)
    public JAXBElement<String> createJoinIPCConferenceRequestPIN(String value) {
        return new JAXBElement<String>(_JoinIPCConferenceRequestPIN_QNAME, String.class, JoinIPCConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = GetLectureModeParticipantsRequest.class)
    public JAXBElement<String> createGetLectureModeParticipantsRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, GetLectureModeParticipantsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "vmaddress", scope = LogInResponse.class)
    public JAXBElement<String> createLogInResponseVmaddress(String value) {
        return new JAXBElement<String>(_LogInResponseVmaddress_QNAME, String.class, LogInResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "loctag", scope = LogInResponse.class)
    public JAXBElement<String> createLogInResponseLoctag(String value) {
        return new JAXBElement<String>(_LogInResponseLoctag_QNAME, String.class, LogInResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "pak2", scope = LogInResponse.class)
    public JAXBElement<String> createLogInResponsePak2(String value) {
        return new JAXBElement<String>(_LogInResponsePak2_QNAME, String.class, LogInResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "proxyaddress", scope = LogInResponse.class)
    public JAXBElement<String> createLogInResponseProxyaddress(String value) {
        return new JAXBElement<String>(_LogInResponseProxyaddress_QNAME, String.class, LogInResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "referenceNumber", scope = JoinConferenceRequest.class)
    public JAXBElement<String> createJoinConferenceRequestReferenceNumber(String value) {
        return new JAXBElement<String>(_JoinIPCConferenceRequestReferenceNumber_QNAME, String.class, JoinConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "PIN", scope = JoinConferenceRequest.class)
    public JAXBElement<String> createJoinConferenceRequestPIN(String value) {
        return new JAXBElement<String>(_JoinIPCConferenceRequestPIN_QNAME, String.class, JoinConferenceRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = DisconnectConferenceAllRequest.class)
    public JAXBElement<String> createDisconnectConferenceAllRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, DisconnectConferenceAllRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = StopVideoRequest.class)
    public JAXBElement<String> createStopVideoRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, StopVideoRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = StopRecordingRequest.class)
    public JAXBElement<String> createStopRecordingRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, StopRecordingRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = CancelOutboundCallRequest.class)
    public JAXBElement<String> createCancelOutboundCallRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, CancelOutboundCallRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/user/v1_1", name = "moderatorPIN", scope = SetPresenterRequest.class)
    public JAXBElement<String> createSetPresenterRequestModeratorPIN(String value) {
        return new JAXBElement<String>(_MuteVideoClientAllRequestModeratorPIN_QNAME, String.class, SetPresenterRequest.class, value);
    }

}
