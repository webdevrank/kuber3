
package com.rank.ccms.ws.admin;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rank.ibl.ws.admin package. 
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

    private final static QName _RoomStatus_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "RoomStatus");
    private final static QName _MemberMode_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "MemberMode");
    private final static QName _GetRecordingProfilesRequest_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "GetRecordingProfilesRequest");
    private final static QName _MemberStatus_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "MemberStatus");
    private final static QName _ScheduledRoomEnabledRequest_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "ScheduledRoomEnabledRequest");
    private final static QName _GetRoomProfilesRequest_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "GetRoomProfilesRequest");
    private final static QName _Language_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "Language");
    private final static QName _EntityType_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "EntityType");
    private final static QName _OK_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "OK");
    private final static QName _GetTenantDetailsRequest_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "GetTenantDetailsRequest");
    private final static QName _RoleName_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "RoleName");
    private final static QName _GetLicenseDataRequest_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "GetLicenseDataRequest");
    private final static QName _ErrorMessage_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "ErrorMessage");
    private final static QName _RoomType_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "RoomType");
    private final static QName _GetPortalVersionRequest_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "GetPortalVersionRequest");
    private final static QName _LectureModeParticipantHandRaised_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "handRaised");
    private final static QName _LectureModeParticipantPresenter_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "presenter");
    private final static QName _LectureModeParticipantVideo_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "video");
    private final static QName _LectureModeParticipantAppshare_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "appshare");
    private final static QName _LectureModeParticipantAudio_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "audio");
    private final static QName _SearchRoomsRequestSortDir_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "sortDir");
    private final static QName _SearchRoomsRequestQueryField_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "queryField");
    private final static QName _SearchRoomsRequestLimit_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "limit");
    private final static QName _SearchRoomsRequestStart_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "start");
    private final static QName _SearchRoomsRequestRoomType_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "roomType");
    private final static QName _SearchRoomsRequestSortBy_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "sortBy");
    private final static QName _SetLayoutRequestLayout_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "layout");
    private final static QName _SetLayoutRequestMaxParticipants_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "maxParticipants");
    private final static QName _EntityParticipantID_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "participantID");
    private final static QName _TransferParticipantRequestRoomPIN_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "roomPIN");
    private final static QName _GetConferenceIDResponseConferenceID_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "conferenceID");
    private final static QName _MemberPhone3_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "phone3");
    private final static QName _MemberPhone2_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "phone2");
    private final static QName _MemberPhone1_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "phone1");
    private final static QName _MemberPassword_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "password");
    private final static QName _MemberTitle_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "title");
    private final static QName _MemberThumbnailUpdateTime_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "thumbnailUpdateTime");
    private final static QName _MemberInstantMessagerID_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "instantMessagerID");
    private final static QName _MemberDepartment_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "department");
    private final static QName _MemberLocation_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "location");
    private final static QName _MemberFilterRoomExtension_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "roomExtension");
    private final static QName _MemberFilterMemberName_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "memberName");
    private final static QName _MemberFilterGroupName_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "groupName");
    private final static QName _MemberFilterStatus_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "status");
    private final static QName _GetConferenceIDRequestUserName_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "userName");
    private final static QName _GetWebcastURLResponseWebCastURL_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "webCastURL");
    private final static QName _RoomModeModeratorPIN_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "moderatorPIN");
    private final static QName _RoomModeRoomURL_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "roomURL");
    private final static QName _FilterQuery_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "query");
    private final static QName _FilterDir_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "dir");
    private final static QName _GetParticipantsResponseRecorderName_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "recorderName");
    private final static QName _GetParticipantsResponsePaused_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "paused");
    private final static QName _GetParticipantsResponseRecorderID_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "recorderID");
    private final static QName _GetParticipantsResponseWebcast_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "webcast");
    private final static QName _GetLectureModeParticipantsResponseLectureMode_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "lectureMode");
    private final static QName _AddClientVersionRequestSetActive_QNAME = new QName("http://portal.vidyo.com/admin/v1_1", "setActive");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rank.ibl.ws.admin
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateMemberResponse }
     * 
     */
    public UpdateMemberResponse createUpdateMemberResponse() {
        return new UpdateMemberResponse();
    }

    /**
     * Create an instance of {@link GetConferenceIDResponse }
     * 
     */
    public GetConferenceIDResponse createGetConferenceIDResponse() {
        return new GetConferenceIDResponse();
    }

    /**
     * Create an instance of {@link DeleteRoomResponse }
     * 
     */
    public DeleteRoomResponse createDeleteRoomResponse() {
        return new DeleteRoomResponse();
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
     * Create an instance of {@link MemberFilter }
     * 
     */
    public MemberFilter createMemberFilter() {
        return new MemberFilter();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
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
     * Create an instance of {@link StopVideoResponse }
     * 
     */
    public StopVideoResponse createStopVideoResponse() {
        return new StopVideoResponse();
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
     * Create an instance of {@link DisableScheduledRoomRequest }
     * 
     */
    public DisableScheduledRoomRequest createDisableScheduledRoomRequest() {
        return new DisableScheduledRoomRequest();
    }

    /**
     * Create an instance of {@link EnableScheduledRoomResponse }
     * 
     */
    public EnableScheduledRoomResponse createEnableScheduledRoomResponse() {
        return new EnableScheduledRoomResponse();
    }

    /**
     * Create an instance of {@link DismissAllRaisedHandResponse }
     * 
     */
    public DismissAllRaisedHandResponse createDismissAllRaisedHandResponse() {
        return new DismissAllRaisedHandResponse();
    }

    /**
     * Create an instance of {@link StartVideoResponse }
     * 
     */
    public StartVideoResponse createStartVideoResponse() {
        return new StartVideoResponse();
    }

    /**
     * Create an instance of {@link ScheduledRoomIsEnabledRequest }
     * 
     */
    public ScheduledRoomIsEnabledRequest createScheduledRoomIsEnabledRequest() {
        return new ScheduledRoomIsEnabledRequest();
    }

    /**
     * Create an instance of {@link ScheduledRoomCreationFault }
     * 
     */
    public ScheduledRoomCreationFault createScheduledRoomCreationFault() {
        return new ScheduledRoomCreationFault();
    }

    /**
     * Create an instance of {@link GetEndpointBehaviorResponse }
     * 
     */
    public GetEndpointBehaviorResponse createGetEndpointBehaviorResponse() {
        return new GetEndpointBehaviorResponse();
    }

    /**
     * Create an instance of {@link EndpointBehaviorDataType }
     * 
     */
    public EndpointBehaviorDataType createEndpointBehaviorDataType() {
        return new EndpointBehaviorDataType();
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
     * Create an instance of {@link RemoveRoomURLResponse }
     * 
     */
    public RemoveRoomURLResponse createRemoveRoomURLResponse() {
        return new RemoveRoomURLResponse();
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
     * Create an instance of {@link InviteToConferenceRequest }
     * 
     */
    public InviteToConferenceRequest createInviteToConferenceRequest() {
        return new InviteToConferenceRequest();
    }

    /**
     * Create an instance of {@link MuteAudioRequest }
     * 
     */
    public MuteAudioRequest createMuteAudioRequest() {
        return new MuteAudioRequest();
    }

    /**
     * Create an instance of {@link RemoveWebcastPINRequest }
     * 
     */
    public RemoveWebcastPINRequest createRemoveWebcastPINRequest() {
        return new RemoveWebcastPINRequest();
    }

    /**
     * Create an instance of {@link SetLayoutRequest }
     * 
     */
    public SetLayoutRequest createSetLayoutRequest() {
        return new SetLayoutRequest();
    }

    /**
     * Create an instance of {@link UpdateLanguageResponse }
     * 
     */
    public UpdateLanguageResponse createUpdateLanguageResponse() {
        return new UpdateLanguageResponse();
    }

    /**
     * Create an instance of {@link CreateEndpointBehaviorResponse }
     * 
     */
    public CreateEndpointBehaviorResponse createCreateEndpointBehaviorResponse() {
        return new CreateEndpointBehaviorResponse();
    }

    /**
     * Create an instance of {@link RoomNotFoundFault }
     * 
     */
    public RoomNotFoundFault createRoomNotFoundFault() {
        return new RoomNotFoundFault();
    }

    /**
     * Create an instance of {@link LeaveConferenceRequest }
     * 
     */
    public LeaveConferenceRequest createLeaveConferenceRequest() {
        return new LeaveConferenceRequest();
    }

    /**
     * Create an instance of {@link UpdateEndpointBehaviorResponse }
     * 
     */
    public UpdateEndpointBehaviorResponse createUpdateEndpointBehaviorResponse() {
        return new UpdateEndpointBehaviorResponse();
    }

    /**
     * Create an instance of {@link RoomAlreadyExistsFault }
     * 
     */
    public RoomAlreadyExistsFault createRoomAlreadyExistsFault() {
        return new RoomAlreadyExistsFault();
    }

    /**
     * Create an instance of {@link DismissRaisedHandRequest }
     * 
     */
    public DismissRaisedHandRequest createDismissRaisedHandRequest() {
        return new DismissRaisedHandRequest();
    }

    /**
     * Create an instance of {@link GetLocationTagsRequest }
     * 
     */
    public GetLocationTagsRequest createGetLocationTagsRequest() {
        return new GetLocationTagsRequest();
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link CreateRoomPINRequest }
     * 
     */
    public CreateRoomPINRequest createCreateRoomPINRequest() {
        return new CreateRoomPINRequest();
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
     * Create an instance of {@link UpdateMemberRequest }
     * 
     */
    public UpdateMemberRequest createUpdateMemberRequest() {
        return new UpdateMemberRequest();
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
     * Create an instance of {@link GetRoomsRequest }
     * 
     */
    public GetRoomsRequest createGetRoomsRequest() {
        return new GetRoomsRequest();
    }

    /**
     * Create an instance of {@link StartRecordingRequest }
     * 
     */
    public StartRecordingRequest createStartRecordingRequest() {
        return new StartRecordingRequest();
    }

    /**
     * Create an instance of {@link AddRoomResponse }
     * 
     */
    public AddRoomResponse createAddRoomResponse() {
        return new AddRoomResponse();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link TransferParticipantResponse }
     * 
     */
    public TransferParticipantResponse createTransferParticipantResponse() {
        return new TransferParticipantResponse();
    }

    /**
     * Create an instance of {@link UpdateLanguageRequest }
     * 
     */
    public UpdateLanguageRequest createUpdateLanguageRequest() {
        return new UpdateLanguageRequest();
    }

    /**
     * Create an instance of {@link CreateWebcastPINRequest }
     * 
     */
    public CreateWebcastPINRequest createCreateWebcastPINRequest() {
        return new CreateWebcastPINRequest();
    }

    /**
     * Create an instance of {@link ScheduledRoomIsEnabledResponse }
     * 
     */
    public ScheduledRoomIsEnabledResponse createScheduledRoomIsEnabledResponse() {
        return new ScheduledRoomIsEnabledResponse();
    }

    /**
     * Create an instance of {@link ResourceNotAvailableFault }
     * 
     */
    public ResourceNotAvailableFault createResourceNotAvailableFault() {
        return new ResourceNotAvailableFault();
    }

    /**
     * Create an instance of {@link GroupNotFoundFault }
     * 
     */
    public GroupNotFoundFault createGroupNotFoundFault() {
        return new GroupNotFoundFault();
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
     * Create an instance of {@link DeleteScheduledRoomRequest }
     * 
     */
    public DeleteScheduledRoomRequest createDeleteScheduledRoomRequest() {
        return new DeleteScheduledRoomRequest();
    }

    /**
     * Create an instance of {@link GetParticipantsRequest }
     * 
     */
    public GetParticipantsRequest createGetParticipantsRequest() {
        return new GetParticipantsRequest();
    }

    /**
     * Create an instance of {@link RemoveWebcastPINResponse }
     * 
     */
    public RemoveWebcastPINResponse createRemoveWebcastPINResponse() {
        return new RemoveWebcastPINResponse();
    }

    /**
     * Create an instance of {@link CreateWebcastPINResponse }
     * 
     */
    public CreateWebcastPINResponse createCreateWebcastPINResponse() {
        return new CreateWebcastPINResponse();
    }

    /**
     * Create an instance of {@link CreateRoomPINResponse }
     * 
     */
    public CreateRoomPINResponse createCreateRoomPINResponse() {
        return new CreateRoomPINResponse();
    }

    /**
     * Create an instance of {@link RoomMode }
     * 
     */
    public RoomMode createRoomMode() {
        return new RoomMode();
    }

    /**
     * Create an instance of {@link AddClientVersionRequest }
     * 
     */
    public AddClientVersionRequest createAddClientVersionRequest() {
        return new AddClientVersionRequest();
    }

    /**
     * Create an instance of {@link SetTenantRoomAttributesResponse }
     * 
     */
    public SetTenantRoomAttributesResponse createSetTenantRoomAttributesResponse() {
        return new SetTenantRoomAttributesResponse();
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
     * Create an instance of {@link DeleteGroupRequest }
     * 
     */
    public DeleteGroupRequest createDeleteGroupRequest() {
        return new DeleteGroupRequest();
    }

    /**
     * Create an instance of {@link AddGroupRequest }
     * 
     */
    public AddGroupRequest createAddGroupRequest() {
        return new AddGroupRequest();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link GetGroupsRequest }
     * 
     */
    public GetGroupsRequest createGetGroupsRequest() {
        return new GetGroupsRequest();
    }

    /**
     * Create an instance of {@link EnableRoomRequest }
     * 
     */
    public EnableRoomRequest createEnableRoomRequest() {
        return new EnableRoomRequest();
    }

    /**
     * Create an instance of {@link DisableScheduledRoomResponse }
     * 
     */
    public DisableScheduledRoomResponse createDisableScheduledRoomResponse() {
        return new DisableScheduledRoomResponse();
    }

    /**
     * Create an instance of {@link CreateModeratorPINResponse }
     * 
     */
    public CreateModeratorPINResponse createCreateModeratorPINResponse() {
        return new CreateModeratorPINResponse();
    }

    /**
     * Create an instance of {@link StartLectureModeResponse }
     * 
     */
    public StartLectureModeResponse createStartLectureModeResponse() {
        return new StartLectureModeResponse();
    }

    /**
     * Create an instance of {@link ScheduledRoomEnabledResponse }
     * 
     */
    public ScheduledRoomEnabledResponse createScheduledRoomEnabledResponse() {
        return new ScheduledRoomEnabledResponse();
    }

    /**
     * Create an instance of {@link RemoveRoomPINRequest }
     * 
     */
    public RemoveRoomPINRequest createRemoveRoomPINRequest() {
        return new RemoveRoomPINRequest();
    }

    /**
     * Create an instance of {@link RoomIsEnabledResponse }
     * 
     */
    public RoomIsEnabledResponse createRoomIsEnabledResponse() {
        return new RoomIsEnabledResponse();
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
     * Create an instance of {@link DeleteGroupResponse }
     * 
     */
    public DeleteGroupResponse createDeleteGroupResponse() {
        return new DeleteGroupResponse();
    }

    /**
     * Create an instance of {@link CreateWebcastURLResponse }
     * 
     */
    public CreateWebcastURLResponse createCreateWebcastURLResponse() {
        return new CreateWebcastURLResponse();
    }

    /**
     * Create an instance of {@link DeleteScheduledRoomResponse }
     * 
     */
    public DeleteScheduledRoomResponse createDeleteScheduledRoomResponse() {
        return new DeleteScheduledRoomResponse();
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
     * Create an instance of {@link RemoveModeratorPINResponse }
     * 
     */
    public RemoveModeratorPINResponse createRemoveModeratorPINResponse() {
        return new RemoveModeratorPINResponse();
    }

    /**
     * Create an instance of {@link GetRoomProfileRequest }
     * 
     */
    public GetRoomProfileRequest createGetRoomProfileRequest() {
        return new GetRoomProfileRequest();
    }

    /**
     * Create an instance of {@link UpdateRoomResponse }
     * 
     */
    public UpdateRoomResponse createUpdateRoomResponse() {
        return new UpdateRoomResponse();
    }

    /**
     * Create an instance of {@link DismissRaisedHandResponse }
     * 
     */
    public DismissRaisedHandResponse createDismissRaisedHandResponse() {
        return new DismissRaisedHandResponse();
    }

    /**
     * Create an instance of {@link CancelOutboundCallRequest }
     * 
     */
    public CancelOutboundCallRequest createCancelOutboundCallRequest() {
        return new CancelOutboundCallRequest();
    }

    /**
     * Create an instance of {@link UnmuteAudioRequest }
     * 
     */
    public UnmuteAudioRequest createUnmuteAudioRequest() {
        return new UnmuteAudioRequest();
    }

    /**
     * Create an instance of {@link RoomIsEnabledRequest }
     * 
     */
    public RoomIsEnabledRequest createRoomIsEnabledRequest() {
        return new RoomIsEnabledRequest();
    }

    /**
     * Create an instance of {@link AddMemberResponse }
     * 
     */
    public AddMemberResponse createAddMemberResponse() {
        return new AddMemberResponse();
    }

    /**
     * Create an instance of {@link GetRoomResponse }
     * 
     */
    public GetRoomResponse createGetRoomResponse() {
        return new GetRoomResponse();
    }

    /**
     * Create an instance of {@link NotAllowedToCreateFault }
     * 
     */
    public NotAllowedToCreateFault createNotAllowedToCreateFault() {
        return new NotAllowedToCreateFault();
    }

    /**
     * Create an instance of {@link RemoveRoomPINResponse }
     * 
     */
    public RemoveRoomPINResponse createRemoveRoomPINResponse() {
        return new RemoveRoomPINResponse();
    }

    /**
     * Create an instance of {@link InPointToPointCallFault }
     * 
     */
    public InPointToPointCallFault createInPointToPointCallFault() {
        return new InPointToPointCallFault();
    }

    /**
     * Create an instance of {@link NoEndpointBehaviorExistsFault }
     * 
     */
    public NoEndpointBehaviorExistsFault createNoEndpointBehaviorExistsFault() {
        return new NoEndpointBehaviorExistsFault();
    }

    /**
     * Create an instance of {@link PublicRoomCreationFault }
     * 
     */
    public PublicRoomCreationFault createPublicRoomCreationFault() {
        return new PublicRoomCreationFault();
    }

    /**
     * Create an instance of {@link AddClientVersionResponse }
     * 
     */
    public AddClientVersionResponse createAddClientVersionResponse() {
        return new AddClientVersionResponse();
    }

    /**
     * Create an instance of {@link CreateWebcastURLRequest }
     * 
     */
    public CreateWebcastURLRequest createCreateWebcastURLRequest() {
        return new CreateWebcastURLRequest();
    }

    /**
     * Create an instance of {@link GetMemberRequest }
     * 
     */
    public GetMemberRequest createGetMemberRequest() {
        return new GetMemberRequest();
    }

    /**
     * Create an instance of {@link SetPresenterResponse }
     * 
     */
    public SetPresenterResponse createSetPresenterResponse() {
        return new SetPresenterResponse();
    }

    /**
     * Create an instance of {@link RemoveModeratorPINRequest }
     * 
     */
    public RemoveModeratorPINRequest createRemoveModeratorPINRequest() {
        return new RemoveModeratorPINRequest();
    }

    /**
     * Create an instance of {@link PauseRecordingRequest }
     * 
     */
    public PauseRecordingRequest createPauseRecordingRequest() {
        return new PauseRecordingRequest();
    }

    /**
     * Create an instance of {@link GetGroupRequest }
     * 
     */
    public GetGroupRequest createGetGroupRequest() {
        return new GetGroupRequest();
    }

    /**
     * Create an instance of {@link DeleteEndpointBehaviorResponse }
     * 
     */
    public DeleteEndpointBehaviorResponse createDeleteEndpointBehaviorResponse() {
        return new DeleteEndpointBehaviorResponse();
    }

    /**
     * Create an instance of {@link RemoveWebcastURLRequest }
     * 
     */
    public RemoveWebcastURLRequest createRemoveWebcastURLRequest() {
        return new RemoveWebcastURLRequest();
    }

    /**
     * Create an instance of {@link ChatNotAvailableInSuperFault }
     * 
     */
    public ChatNotAvailableInSuperFault createChatNotAvailableInSuperFault() {
        return new ChatNotAvailableInSuperFault();
    }

    /**
     * Create an instance of {@link MemberNotFoundFault }
     * 
     */
    public MemberNotFoundFault createMemberNotFoundFault() {
        return new MemberNotFoundFault();
    }

    /**
     * Create an instance of {@link GetTenantDetailsRequest }
     * 
     */
    public GetTenantDetailsRequest createGetTenantDetailsRequest() {
        return new GetTenantDetailsRequest();
    }

    /**
     * Create an instance of {@link EndpointBehaviorDisabledFault }
     * 
     */
    public EndpointBehaviorDisabledFault createEndpointBehaviorDisabledFault() {
        return new EndpointBehaviorDisabledFault();
    }

    /**
     * Create an instance of {@link UpdateGroupRequest }
     * 
     */
    public UpdateGroupRequest createUpdateGroupRequest() {
        return new UpdateGroupRequest();
    }

    /**
     * Create an instance of {@link RemoveWebcastURLResponse }
     * 
     */
    public RemoveWebcastURLResponse createRemoveWebcastURLResponse() {
        return new RemoveWebcastURLResponse();
    }

    /**
     * Create an instance of {@link UpdateEndpointBehaviorRequest }
     * 
     */
    public UpdateEndpointBehaviorRequest createUpdateEndpointBehaviorRequest() {
        return new UpdateEndpointBehaviorRequest();
    }

    /**
     * Create an instance of {@link GetTenantDetailsResponse }
     * 
     */
    public GetTenantDetailsResponse createGetTenantDetailsResponse() {
        return new GetTenantDetailsResponse();
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
     * Create an instance of {@link DeleteMemberResponse }
     * 
     */
    public DeleteMemberResponse createDeleteMemberResponse() {
        return new DeleteMemberResponse();
    }

    /**
     * Create an instance of {@link CreateRoomURLRequest }
     * 
     */
    public CreateRoomURLRequest createCreateRoomURLRequest() {
        return new CreateRoomURLRequest();
    }

    /**
     * Create an instance of {@link UpdateRoomRequest }
     * 
     */
    public UpdateRoomRequest createUpdateRoomRequest() {
        return new UpdateRoomRequest();
    }

    /**
     * Create an instance of {@link GetGroupResponse }
     * 
     */
    public GetGroupResponse createGetGroupResponse() {
        return new GetGroupResponse();
    }

    /**
     * Create an instance of {@link SearchRoomsResponse }
     * 
     */
    public SearchRoomsResponse createSearchRoomsResponse() {
        return new SearchRoomsResponse();
    }

    /**
     * Create an instance of {@link RoomDetail }
     * 
     */
    public RoomDetail createRoomDetail() {
        return new RoomDetail();
    }

    /**
     * Create an instance of {@link CancelOutboundCallResponse }
     * 
     */
    public CancelOutboundCallResponse createCancelOutboundCallResponse() {
        return new CancelOutboundCallResponse();
    }

    /**
     * Create an instance of {@link MuteAudioResponse }
     * 
     */
    public MuteAudioResponse createMuteAudioResponse() {
        return new MuteAudioResponse();
    }

    /**
     * Create an instance of {@link DismissAllRaisedHandRequest }
     * 
     */
    public DismissAllRaisedHandRequest createDismissAllRaisedHandRequest() {
        return new DismissAllRaisedHandRequest();
    }

    /**
     * Create an instance of {@link GetLocationTagsResponse }
     * 
     */
    public GetLocationTagsResponse createGetLocationTagsResponse() {
        return new GetLocationTagsResponse();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link AddRoomRequest }
     * 
     */
    public AddRoomRequest createAddRoomRequest() {
        return new AddRoomRequest();
    }

    /**
     * Create an instance of {@link UpdatePasswordRequest }
     * 
     */
    public UpdatePasswordRequest createUpdatePasswordRequest() {
        return new UpdatePasswordRequest();
    }

    /**
     * Create an instance of {@link ResumeRecordingResponse }
     * 
     */
    public ResumeRecordingResponse createResumeRecordingResponse() {
        return new ResumeRecordingResponse();
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
     * Create an instance of {@link GetParticipantsResponse }
     * 
     */
    public GetParticipantsResponse createGetParticipantsResponse() {
        return new GetParticipantsResponse();
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link RemoveRoomProfileRequest }
     * 
     */
    public RemoveRoomProfileRequest createRemoveRoomProfileRequest() {
        return new RemoveRoomProfileRequest();
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
     * Create an instance of {@link UpdateGroupResponse }
     * 
     */
    public UpdateGroupResponse createUpdateGroupResponse() {
        return new UpdateGroupResponse();
    }

    /**
     * Create an instance of {@link SetLayoutResponse }
     * 
     */
    public SetLayoutResponse createSetLayoutResponse() {
        return new SetLayoutResponse();
    }

    /**
     * Create an instance of {@link InvalidModeratorPINFormatFault }
     * 
     */
    public InvalidModeratorPINFormatFault createInvalidModeratorPINFormatFault() {
        return new InvalidModeratorPINFormatFault();
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
     * Create an instance of {@link GetLicenseDataResponse }
     * 
     */
    public GetLicenseDataResponse createGetLicenseDataResponse() {
        return new GetLicenseDataResponse();
    }

    /**
     * Create an instance of {@link LicenseFeatureData }
     * 
     */
    public LicenseFeatureData createLicenseFeatureData() {
        return new LicenseFeatureData();
    }

    /**
     * Create an instance of {@link GetMembersResponse }
     * 
     */
    public GetMembersResponse createGetMembersResponse() {
        return new GetMembersResponse();
    }

    /**
     * Create an instance of {@link EnableRoomResponse }
     * 
     */
    public EnableRoomResponse createEnableRoomResponse() {
        return new EnableRoomResponse();
    }

    /**
     * Create an instance of {@link CreateScheduledRoomRequest }
     * 
     */
    public CreateScheduledRoomRequest createCreateScheduledRoomRequest() {
        return new CreateScheduledRoomRequest();
    }

    /**
     * Create an instance of {@link DeleteMemberRequest }
     * 
     */
    public DeleteMemberRequest createDeleteMemberRequest() {
        return new DeleteMemberRequest();
    }

    /**
     * Create an instance of {@link AddMemberRequest }
     * 
     */
    public AddMemberRequest createAddMemberRequest() {
        return new AddMemberRequest();
    }

    /**
     * Create an instance of {@link SetTenantRoomAttributesRequest }
     * 
     */
    public SetTenantRoomAttributesRequest createSetTenantRoomAttributesRequest() {
        return new SetTenantRoomAttributesRequest();
    }

    /**
     * Create an instance of {@link GetLicenseDataRequest }
     * 
     */
    public GetLicenseDataRequest createGetLicenseDataRequest() {
        return new GetLicenseDataRequest();
    }

    /**
     * Create an instance of {@link ExternalModeFault }
     * 
     */
    public ExternalModeFault createExternalModeFault() {
        return new ExternalModeFault();
    }

    /**
     * Create an instance of {@link StopRecordingRequest }
     * 
     */
    public StopRecordingRequest createStopRecordingRequest() {
        return new StopRecordingRequest();
    }

    /**
     * Create an instance of {@link GetEndpointBehaviorRequest }
     * 
     */
    public GetEndpointBehaviorRequest createGetEndpointBehaviorRequest() {
        return new GetEndpointBehaviorRequest();
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
     * Create an instance of {@link GetGroupsResponse }
     * 
     */
    public GetGroupsResponse createGetGroupsResponse() {
        return new GetGroupsResponse();
    }

    /**
     * Create an instance of {@link CreateEndpointBehaviorRequest }
     * 
     */
    public CreateEndpointBehaviorRequest createCreateEndpointBehaviorRequest() {
        return new CreateEndpointBehaviorRequest();
    }

    /**
     * Create an instance of {@link GroupAlreadyExistsFault }
     * 
     */
    public GroupAlreadyExistsFault createGroupAlreadyExistsFault() {
        return new GroupAlreadyExistsFault();
    }

    /**
     * Create an instance of {@link NotLicensedFault }
     * 
     */
    public NotLicensedFault createNotLicensedFault() {
        return new NotLicensedFault();
    }

    /**
     * Create an instance of {@link StopLectureModeResponse }
     * 
     */
    public StopLectureModeResponse createStopLectureModeResponse() {
        return new StopLectureModeResponse();
    }

    /**
     * Create an instance of {@link SetChatStateAdminResponse }
     * 
     */
    public SetChatStateAdminResponse createSetChatStateAdminResponse() {
        return new SetChatStateAdminResponse();
    }

    /**
     * Create an instance of {@link AddGroupResponse }
     * 
     */
    public AddGroupResponse createAddGroupResponse() {
        return new AddGroupResponse();
    }

    /**
     * Create an instance of {@link MemberAlreadyExistsFault }
     * 
     */
    public MemberAlreadyExistsFault createMemberAlreadyExistsFault() {
        return new MemberAlreadyExistsFault();
    }

    /**
     * Create an instance of {@link EnableScheduledRoomRequest }
     * 
     */
    public EnableScheduledRoomRequest createEnableScheduledRoomRequest() {
        return new EnableScheduledRoomRequest();
    }

    /**
     * Create an instance of {@link TransferParticipantRequest }
     * 
     */
    public TransferParticipantRequest createTransferParticipantRequest() {
        return new TransferParticipantRequest();
    }

    /**
     * Create an instance of {@link GetRoomRequest }
     * 
     */
    public GetRoomRequest createGetRoomRequest() {
        return new GetRoomRequest();
    }

    /**
     * Create an instance of {@link SetChatStateAdminRequest }
     * 
     */
    public SetChatStateAdminRequest createSetChatStateAdminRequest() {
        return new SetChatStateAdminRequest();
    }

    /**
     * Create an instance of {@link DeleteRoomRequest }
     * 
     */
    public DeleteRoomRequest createDeleteRoomRequest() {
        return new DeleteRoomRequest();
    }

    /**
     * Create an instance of {@link DeleteEndpointBehaviorRequest }
     * 
     */
    public DeleteEndpointBehaviorRequest createDeleteEndpointBehaviorRequest() {
        return new DeleteEndpointBehaviorRequest();
    }

    /**
     * Create an instance of {@link CreateRoomURLResponse }
     * 
     */
    public CreateRoomURLResponse createCreateRoomURLResponse() {
        return new CreateRoomURLResponse();
    }

    /**
     * Create an instance of {@link RemoveRoomURLRequest }
     * 
     */
    public RemoveRoomURLRequest createRemoveRoomURLRequest() {
        return new RemoveRoomURLRequest();
    }

    /**
     * Create an instance of {@link GetRoomsResponse }
     * 
     */
    public GetRoomsResponse createGetRoomsResponse() {
        return new GetRoomsResponse();
    }

    /**
     * Create an instance of {@link GetMembersRequest }
     * 
     */
    public GetMembersRequest createGetMembersRequest() {
        return new GetMembersRequest();
    }

    /**
     * Create an instance of {@link InvalidArgumentFault }
     * 
     */
    public InvalidArgumentFault createInvalidArgumentFault() {
        return new InvalidArgumentFault();
    }

    /**
     * Create an instance of {@link EndpointBehaviorAlreadyExistsFault }
     * 
     */
    public EndpointBehaviorAlreadyExistsFault createEndpointBehaviorAlreadyExistsFault() {
        return new EndpointBehaviorAlreadyExistsFault();
    }

    /**
     * Create an instance of {@link CreatePublicRoomRequest }
     * 
     */
    public CreatePublicRoomRequest createCreatePublicRoomRequest() {
        return new CreatePublicRoomRequest();
    }

    /**
     * Create an instance of {@link GetMemberResponse }
     * 
     */
    public GetMemberResponse createGetMemberResponse() {
        return new GetMemberResponse();
    }

    /**
     * Create an instance of {@link GetConferenceIDRequest }
     * 
     */
    public GetConferenceIDRequest createGetConferenceIDRequest() {
        return new GetConferenceIDRequest();
    }

    /**
     * Create an instance of {@link GetRoomProfileResponse }
     * 
     */
    public GetRoomProfileResponse createGetRoomProfileResponse() {
        return new GetRoomProfileResponse();
    }

    /**
     * Create an instance of {@link Tenant }
     * 
     */
    public Tenant createTenant() {
        return new Tenant();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "RoomStatus")
    public JAXBElement<String> createRoomStatus(String value) {
        return new JAXBElement<String>(_RoomStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "MemberMode")
    public JAXBElement<String> createMemberMode(String value) {
        return new JAXBElement<String>(_MemberMode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "GetRecordingProfilesRequest")
    public JAXBElement<Object> createGetRecordingProfilesRequest(Object value) {
        return new JAXBElement<Object>(_GetRecordingProfilesRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "MemberStatus")
    public JAXBElement<String> createMemberStatus(String value) {
        return new JAXBElement<String>(_MemberStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "ScheduledRoomEnabledRequest")
    public JAXBElement<Object> createScheduledRoomEnabledRequest(Object value) {
        return new JAXBElement<Object>(_ScheduledRoomEnabledRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "GetRoomProfilesRequest")
    public JAXBElement<Object> createGetRoomProfilesRequest(Object value) {
        return new JAXBElement<Object>(_GetRoomProfilesRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "Language")
    public JAXBElement<String> createLanguage(String value) {
        return new JAXBElement<String>(_Language_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "EntityType")
    public JAXBElement<String> createEntityType(String value) {
        return new JAXBElement<String>(_EntityType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "OK")
    public JAXBElement<String> createOK(String value) {
        return new JAXBElement<String>(_OK_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTenantDetailsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "GetTenantDetailsRequest")
    public JAXBElement<GetTenantDetailsRequest> createGetTenantDetailsRequest(GetTenantDetailsRequest value) {
        return new JAXBElement<GetTenantDetailsRequest>(_GetTenantDetailsRequest_QNAME, GetTenantDetailsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "RoleName")
    public JAXBElement<String> createRoleName(String value) {
        return new JAXBElement<String>(_RoleName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLicenseDataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "GetLicenseDataRequest")
    public JAXBElement<GetLicenseDataRequest> createGetLicenseDataRequest(GetLicenseDataRequest value) {
        return new JAXBElement<GetLicenseDataRequest>(_GetLicenseDataRequest_QNAME, GetLicenseDataRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "ErrorMessage")
    public JAXBElement<String> createErrorMessage(String value) {
        return new JAXBElement<String>(_ErrorMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "RoomType")
    public JAXBElement<String> createRoomType(String value) {
        return new JAXBElement<String>(_RoomType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "GetPortalVersionRequest")
    public JAXBElement<Object> createGetPortalVersionRequest(Object value) {
        return new JAXBElement<Object>(_GetPortalVersionRequest_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "handRaised", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantHandRaised(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantHandRaised_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "presenter", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantPresenter(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantPresenter_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "video", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantVideo(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantVideo_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "appshare", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantAppshare(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantAppshare_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "audio", scope = LectureModeParticipant.class)
    public JAXBElement<Boolean> createLectureModeParticipantAudio(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantAudio_QNAME, Boolean.class, LectureModeParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "sortDir", scope = SearchRoomsRequest.class)
    public JAXBElement<SortDir> createSearchRoomsRequestSortDir(SortDir value) {
        return new JAXBElement<SortDir>(_SearchRoomsRequestSortDir_QNAME, SortDir.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "queryField", scope = SearchRoomsRequest.class)
    public JAXBElement<String> createSearchRoomsRequestQueryField(String value) {
        return new JAXBElement<String>(_SearchRoomsRequestQueryField_QNAME, String.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "limit", scope = SearchRoomsRequest.class)
    public JAXBElement<BigInteger> createSearchRoomsRequestLimit(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchRoomsRequestLimit_QNAME, BigInteger.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "start", scope = SearchRoomsRequest.class)
    public JAXBElement<BigInteger> createSearchRoomsRequestStart(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchRoomsRequestStart_QNAME, BigInteger.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "roomType", scope = SearchRoomsRequest.class)
    public JAXBElement<String> createSearchRoomsRequestRoomType(String value) {
        return new JAXBElement<String>(_SearchRoomsRequestRoomType_QNAME, String.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "sortBy", scope = SearchRoomsRequest.class)
    public JAXBElement<String> createSearchRoomsRequestSortBy(String value) {
        return new JAXBElement<String>(_SearchRoomsRequestSortBy_QNAME, String.class, SearchRoomsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "layout", scope = SetLayoutRequest.class)
    public JAXBElement<String> createSetLayoutRequestLayout(String value) {
        return new JAXBElement<String>(_SetLayoutRequestLayout_QNAME, String.class, SetLayoutRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "maxParticipants", scope = SetLayoutRequest.class)
    public JAXBElement<BigInteger> createSetLayoutRequestMaxParticipants(BigInteger value) {
        return new JAXBElement<BigInteger>(_SetLayoutRequestMaxParticipants_QNAME, BigInteger.class, SetLayoutRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "participantID", scope = Entity.class)
    public JAXBElement<Integer> createEntityParticipantID(Integer value) {
        return new JAXBElement<Integer>(_EntityParticipantID_QNAME, Integer.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "video", scope = Entity.class)
    public JAXBElement<Boolean> createEntityVideo(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantVideo_QNAME, Boolean.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "appshare", scope = Entity.class)
    public JAXBElement<Boolean> createEntityAppshare(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantAppshare_QNAME, Boolean.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "audio", scope = Entity.class)
    public JAXBElement<Boolean> createEntityAudio(Boolean value) {
        return new JAXBElement<Boolean>(_LectureModeParticipantAudio_QNAME, Boolean.class, Entity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "roomPIN", scope = TransferParticipantRequest.class)
    public JAXBElement<String> createTransferParticipantRequestRoomPIN(String value) {
        return new JAXBElement<String>(_TransferParticipantRequestRoomPIN_QNAME, String.class, TransferParticipantRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "conferenceID", scope = GetConferenceIDResponse.class)
    public JAXBElement<Integer> createGetConferenceIDResponseConferenceID(Integer value) {
        return new JAXBElement<Integer>(_GetConferenceIDResponseConferenceID_QNAME, Integer.class, GetConferenceIDResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "phone3", scope = Member.class)
    public JAXBElement<String> createMemberPhone3(String value) {
        return new JAXBElement<String>(_MemberPhone3_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "phone2", scope = Member.class)
    public JAXBElement<String> createMemberPhone2(String value) {
        return new JAXBElement<String>(_MemberPhone2_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "phone1", scope = Member.class)
    public JAXBElement<String> createMemberPhone1(String value) {
        return new JAXBElement<String>(_MemberPhone1_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "password", scope = Member.class)
    public JAXBElement<String> createMemberPassword(String value) {
        return new JAXBElement<String>(_MemberPassword_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "title", scope = Member.class)
    public JAXBElement<String> createMemberTitle(String value) {
        return new JAXBElement<String>(_MemberTitle_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "thumbnailUpdateTime", scope = Member.class)
    public JAXBElement<XMLGregorianCalendar> createMemberThumbnailUpdateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MemberThumbnailUpdateTime_QNAME, XMLGregorianCalendar.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "instantMessagerID", scope = Member.class)
    public JAXBElement<String> createMemberInstantMessagerID(String value) {
        return new JAXBElement<String>(_MemberInstantMessagerID_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "department", scope = Member.class)
    public JAXBElement<String> createMemberDepartment(String value) {
        return new JAXBElement<String>(_MemberDepartment_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "location", scope = Member.class)
    public JAXBElement<String> createMemberLocation(String value) {
        return new JAXBElement<String>(_MemberLocation_QNAME, String.class, Member.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "roomExtension", scope = MemberFilter.class)
    public JAXBElement<String> createMemberFilterRoomExtension(String value) {
        return new JAXBElement<String>(_MemberFilterRoomExtension_QNAME, String.class, MemberFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "memberName", scope = MemberFilter.class)
    public JAXBElement<String> createMemberFilterMemberName(String value) {
        return new JAXBElement<String>(_MemberFilterMemberName_QNAME, String.class, MemberFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "groupName", scope = MemberFilter.class)
    public JAXBElement<String> createMemberFilterGroupName(String value) {
        return new JAXBElement<String>(_MemberFilterGroupName_QNAME, String.class, MemberFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "status", scope = MemberFilter.class)
    public JAXBElement<String> createMemberFilterStatus(String value) {
        return new JAXBElement<String>(_MemberFilterStatus_QNAME, String.class, MemberFilter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "userName", scope = GetConferenceIDRequest.class)
    public JAXBElement<String> createGetConferenceIDRequestUserName(String value) {
        return new JAXBElement<String>(_GetConferenceIDRequestUserName_QNAME, String.class, GetConferenceIDRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "webCastURL", scope = GetWebcastURLResponse.class)
    public JAXBElement<String> createGetWebcastURLResponseWebCastURL(String value) {
        return new JAXBElement<String>(_GetWebcastURLResponseWebCastURL_QNAME, String.class, GetWebcastURLResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "moderatorPIN", scope = RoomMode.class)
    public JAXBElement<String> createRoomModeModeratorPIN(String value) {
        return new JAXBElement<String>(_RoomModeModeratorPIN_QNAME, String.class, RoomMode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "roomPIN", scope = RoomMode.class)
    public JAXBElement<String> createRoomModeRoomPIN(String value) {
        return new JAXBElement<String>(_TransferParticipantRequestRoomPIN_QNAME, String.class, RoomMode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "roomURL", scope = RoomMode.class)
    public JAXBElement<String> createRoomModeRoomURL(String value) {
        return new JAXBElement<String>(_RoomModeRoomURL_QNAME, String.class, RoomMode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "limit", scope = Filter.class)
    public JAXBElement<Integer> createFilterLimit(Integer value) {
        return new JAXBElement<Integer>(_SearchRoomsRequestLimit_QNAME, Integer.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "query", scope = Filter.class)
    public JAXBElement<String> createFilterQuery(String value) {
        return new JAXBElement<String>(_FilterQuery_QNAME, String.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "dir", scope = Filter.class)
    public JAXBElement<SortDir> createFilterDir(SortDir value) {
        return new JAXBElement<SortDir>(_FilterDir_QNAME, SortDir.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "start", scope = Filter.class)
    public JAXBElement<Integer> createFilterStart(Integer value) {
        return new JAXBElement<Integer>(_SearchRoomsRequestStart_QNAME, Integer.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "sortBy", scope = Filter.class)
    public JAXBElement<String> createFilterSortBy(String value) {
        return new JAXBElement<String>(_SearchRoomsRequestSortBy_QNAME, String.class, Filter.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "recorderName", scope = GetParticipantsResponse.class)
    public JAXBElement<String> createGetParticipantsResponseRecorderName(String value) {
        return new JAXBElement<String>(_GetParticipantsResponseRecorderName_QNAME, String.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "paused", scope = GetParticipantsResponse.class)
    public JAXBElement<Boolean> createGetParticipantsResponsePaused(Boolean value) {
        return new JAXBElement<Boolean>(_GetParticipantsResponsePaused_QNAME, Boolean.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "recorderID", scope = GetParticipantsResponse.class)
    public JAXBElement<Integer> createGetParticipantsResponseRecorderID(Integer value) {
        return new JAXBElement<Integer>(_GetParticipantsResponseRecorderID_QNAME, Integer.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "webcast", scope = GetParticipantsResponse.class)
    public JAXBElement<Boolean> createGetParticipantsResponseWebcast(Boolean value) {
        return new JAXBElement<Boolean>(_GetParticipantsResponseWebcast_QNAME, Boolean.class, GetParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDir }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "sortDir", scope = SearchMembersRequest.class)
    public JAXBElement<SortDir> createSearchMembersRequestSortDir(SortDir value) {
        return new JAXBElement<SortDir>(_SearchRoomsRequestSortDir_QNAME, SortDir.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "limit", scope = SearchMembersRequest.class)
    public JAXBElement<BigInteger> createSearchMembersRequestLimit(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchRoomsRequestLimit_QNAME, BigInteger.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "start", scope = SearchMembersRequest.class)
    public JAXBElement<BigInteger> createSearchMembersRequestStart(BigInteger value) {
        return new JAXBElement<BigInteger>(_SearchRoomsRequestStart_QNAME, BigInteger.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "sortBy", scope = SearchMembersRequest.class)
    public JAXBElement<String> createSearchMembersRequestSortBy(String value) {
        return new JAXBElement<String>(_SearchRoomsRequestSortBy_QNAME, String.class, SearchMembersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "recorderName", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<String> createGetLectureModeParticipantsResponseRecorderName(String value) {
        return new JAXBElement<String>(_GetParticipantsResponseRecorderName_QNAME, String.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "paused", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Boolean> createGetLectureModeParticipantsResponsePaused(Boolean value) {
        return new JAXBElement<Boolean>(_GetParticipantsResponsePaused_QNAME, Boolean.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "lectureMode", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Boolean> createGetLectureModeParticipantsResponseLectureMode(Boolean value) {
        return new JAXBElement<Boolean>(_GetLectureModeParticipantsResponseLectureMode_QNAME, Boolean.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "recorderID", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Integer> createGetLectureModeParticipantsResponseRecorderID(Integer value) {
        return new JAXBElement<Integer>(_GetParticipantsResponseRecorderID_QNAME, Integer.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "webcast", scope = GetLectureModeParticipantsResponse.class)
    public JAXBElement<Boolean> createGetLectureModeParticipantsResponseWebcast(Boolean value) {
        return new JAXBElement<Boolean>(_GetParticipantsResponseWebcast_QNAME, Boolean.class, GetLectureModeParticipantsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://portal.vidyo.com/admin/v1_1", name = "setActive", scope = AddClientVersionRequest.class, defaultValue = "0")
    public JAXBElement<Boolean> createAddClientVersionRequestSetActive(Boolean value) {
        return new JAXBElement<Boolean>(_AddClientVersionRequestSetActive_QNAME, Boolean.class, AddClientVersionRequest.class, value);
    }

}
