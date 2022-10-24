package com.rank.ccms.vidyo.util;

import com.rank.ccms.util.Constants;
import com.rank.ccms.ws.admin.AddMemberRequest;
import com.rank.ccms.ws.admin.AddMemberResponse;
import com.rank.ccms.ws.admin.DeleteMemberRequest;
import com.rank.ccms.ws.admin.DeleteMemberResponse;
import com.rank.ccms.ws.admin.DeleteRoomRequest;
import com.rank.ccms.ws.admin.DeleteRoomResponse;
import com.rank.ccms.ws.admin.Entity;
import com.rank.ccms.ws.admin.Filter;
import com.rank.ccms.ws.admin.GeneralFault_Exception;
import com.rank.ccms.ws.admin.GetMembersRequest;
import com.rank.ccms.ws.admin.GetMembersResponse;
import com.rank.ccms.ws.admin.GetParticipantsRequest;
import com.rank.ccms.ws.admin.GetParticipantsResponse;
import com.rank.ccms.ws.admin.InvalidArgumentFault_Exception;
import com.rank.ccms.ws.admin.LeaveConferenceRequest;
import com.rank.ccms.ws.admin.Member;
import com.rank.ccms.ws.admin.MemberAlreadyExistsFault_Exception;
import com.rank.ccms.ws.admin.MemberNotFoundFault_Exception;
import com.rank.ccms.ws.admin.NotLicensedFault_Exception;
import com.rank.ccms.ws.admin.RoomDetail;
import com.rank.ccms.ws.admin.SearchRoomsRequest;
import com.rank.ccms.ws.admin.SearchRoomsResponse;
import com.rank.ccms.ws.admin.StartRecordingRequest;
import com.rank.ccms.ws.admin.StartRecordingResponse;
import com.rank.ccms.ws.admin.StopRecordingRequest;
import com.rank.ccms.ws.admin.StopRecordingResponse;
import com.rank.ccms.ws.admin.VidyoPortalAdminService;
import com.rank.ccms.ws.admin.VidyoPortalAdminServicePortType;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import javax.xml.namespace.QName;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

public class VidyoAccessAdmin implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VidyoAccessAdmin.class);

    VidyoPortalAdminService vidyoPortalAdminService;
    VidyoPortalAdminServicePortType vidyoPortalAdminServicePort;

    public VidyoAccessAdmin(String portalUrl) {
        try {
            if (!portalUrl.contains("?wsdl")) {
                portalUrl = portalUrl + "?wsdl";
            }
            vidyoPortalAdminService = new VidyoPortalAdminService(new URL(portalUrl));
            vidyoPortalAdminServicePort = vidyoPortalAdminService.getVidyoPortalAdminServicePort();
        } catch (MalformedURLException e) {
            logger.error("Error:" + e.getMessage());

        }
    }

    public VidyoAccessAdmin() {

    }

    public String vidyoPortalRegistration(String name, String displayName, String email, String portalUrl, String adminid, String adminpassword, Long numberPassword) {
        String password = "";
        String extension;
        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);
            AddMemberRequest addMemberRequest = new AddMemberRequest();
            Member member = new Member();
            member.setName(name);
            com.rank.ccms.ws.admin.ObjectFactory objectFactory = new com.rank.ccms.ws.admin.ObjectFactory();

            javax.xml.bind.JAXBElement pass = objectFactory.createMemberPassword(numberPassword + "");
            member.setPassword(pass);
            member.setDisplayName(displayName);
            Random randomGenerator = new Random();

            extension = Constants.portalExtention;

            Integer randomPin = randomGenerator.nextInt(89999999) + 10000000;
            logger.info("In registration vidyo-------------" + extension + randomPin.toString());
            member.setExtension(extension + randomPin.toString());
            member.setLanguage("en");
            member.setRoleName("Normal");
            member.setGroupName("Default");
            member.setProxyName("No Proxy");
            member.setEmailAddress(email);
            member.setDescription("true");
            member.setAllowCallDirect(true);
            member.setAllowPersonalMeeting(true);
            member.setLocationTag("Default");

            addMemberRequest.setMember(member);

            try {

                AddMemberResponse response = vidyoPortalAdminServicePort.addMember(addMemberRequest);
                if (response != null) {
                    password = numberPassword + "";
                } else {
                    password = "";
                }
            } catch (com.rank.ccms.ws.admin.GeneralFault_Exception | com.rank.ccms.ws.admin.InvalidArgumentFault_Exception | MemberAlreadyExistsFault_Exception | com.rank.ccms.ws.admin.NotLicensedFault_Exception e) {
                logger.error("Error vidyoPortalRegistration:" + e.getMessage());

                return "";
            }
        } catch (Exception e) {
            logger.info("Exception " + e);
        }
        return password;
    }

    public boolean leaveConference(String userId, String password, String portalurl, String conferenceid, String participantid) {
        boolean logged = false;
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LeaveConferenceRequest leaveConferenceRequest = new LeaveConferenceRequest();
            leaveConferenceRequest.setConferenceID(Integer.parseInt(conferenceid));
            leaveConferenceRequest.setParticipantID(Integer.parseInt(participantid));
            vidyoPortalAdminServicePort.leaveConference(leaveConferenceRequest);
        } catch (WebServiceException | MalformedURLException e) {
            logger.error("Error:" + e.getMessage());

            logged = false;
        } catch (GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception ex) {
            logger.error("Error:" + ex.getMessage());
        }
        return logged;
    }

    public Integer getParticipantid(String userId, String password, String portalurl, int entityId) {
        Integer participantid = null;

        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(entityId);
            GetParticipantsResponse participantResponse = vidyoPortalAdminServicePort.getParticipants(getParticipantsRequest);
            List<Entity> participantlist = participantResponse.getEntity();

            for (Entity participantlist1 : participantlist) {
                if (participantlist1.getEntityID() == entityId) {
                    participantid = participantlist1.getParticipantID().getValue();
                }
            }

        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception ex) {
            logger.error("Error getParticipantid:" + ex.getMessage());
        }
        return participantid;
    }

    public int getMemberID(String userId, String password, String portalurl, String loginId) {

        int memberid = 0;
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetMembersRequest getMembersRequest = new GetMembersRequest();
            Filter filter = new Filter();
            com.rank.ccms.ws.admin.ObjectFactory objectFactory = new com.rank.ccms.ws.admin.ObjectFactory();
            javax.xml.bind.JAXBElement logid = objectFactory.createFilterQuery(loginId);
            filter.setQuery(logid);
            getMembersRequest.setFilter(filter);

            GetMembersResponse getMembersResponse = vidyoPortalAdminServicePort.getMembers(getMembersRequest);

            if (getMembersResponse.getMember().size() > 0) {
                for (int i = 0; i < getMembersResponse.getMember().size(); i++) {
                    if (getMembersResponse.getMember().get(i).getName().equals(loginId)) {
                        memberid = getMembersResponse.getMember().get(i).getMemberID();
                        break;
                    }
                }

            }
        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception ex) {
            logger.error("Error getMemberID:" + ex.getMessage());
        }
        return memberid;
    }

    public void deleteMember(String userId, String password, String portalurl, String loginId) {

        int memberid = getMemberID(userId, password, portalurl, loginId);

        if (memberid != 0) {
            try {
                URL url = new URL(portalurl);

                QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalAdminService");
                Service service = Service.create(url, qname);
                vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
                String portalUrl1 = portalurl;
                if (portalUrl1.contains("?wsdl")) {
                    portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
                }

                BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

                DeleteMemberRequest deleteMemberRequest = new DeleteMemberRequest();
                deleteMemberRequest.setMemberID(memberid);
                try {
                    DeleteMemberResponse deleteMemberResponse = vidyoPortalAdminServicePort.deleteMember(deleteMemberRequest);
                    logger.info("Delete Member==============>" + memberid + " " + deleteMemberResponse.getOK());

                } catch (GeneralFault_Exception | InvalidArgumentFault_Exception | MemberNotFoundFault_Exception | NotLicensedFault_Exception e) {
                }
            } catch (Exception ex) {
                logger.error("Error getMemberID:" + ex.getMessage());
            }
        } else {
            logger.info("Unable to delete employee....");
        }

    }

    public void deleteRoom(String adminid, String adminpassword, Integer entityId, String portalUrl) throws Exception {

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
        Service service = Service.create(url, qname);
        vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);

        DeleteRoomRequest deleteRoomRequest = new DeleteRoomRequest();
        deleteRoomRequest.setRoomID(entityId);
        DeleteRoomResponse deleteRoomResponse = vidyoPortalAdminServicePort.deleteRoom(deleteRoomRequest);
        logger.info("DeleteRoomResponse============>" + deleteRoomResponse.getOK());

    }

    public void searchRoomsAndDelete(String adminid, String adminpassword, String query, String portalUrl) throws Exception {
        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
        Service service = Service.create(url, qname);
        vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);

        SearchRoomsRequest searchRoomsRequest = new SearchRoomsRequest();
        searchRoomsRequest.setQuery(query);

        SearchRoomsResponse searchRoomsResponse = vidyoPortalAdminServicePort.searchRooms(searchRoomsRequest);
        int deleteCount = 0;
        if (null != searchRoomsResponse) {

            List<RoomDetail> roomDetailList = searchRoomsResponse.getRoomDetail();
            if (null != roomDetailList) {
                for (RoomDetail roomDetail : roomDetailList) {
                    deleteCount++;

                    DeleteRoomRequest deleteRoomRequest = new DeleteRoomRequest();
                    deleteRoomRequest.setRoomID(roomDetail.getEntityID());
                    DeleteRoomResponse deleteRoomResponse = vidyoPortalAdminServicePort.deleteRoom(deleteRoomRequest);

                }
            }

        }
        logger.info(deleteCount + "rooms get deleted....");

    }

    public int startRecording(Integer entityID, String adminid, String adminpassword, String portalUrl) throws Exception {
        int recorder = 0;
        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
        Service service = Service.create(url, qname);
        vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);

        StartRecordingRequest startRecordingRequest = new StartRecordingRequest();
        startRecordingRequest.setConferenceID(entityID);
        startRecordingRequest.setRecorderPrefix(Constants.recorderPrefix);
        startRecordingRequest.setWebcast(true);

        StartRecordingResponse recordingResponse = vidyoPortalAdminServicePort.startRecording(startRecordingRequest);
        if (recordingResponse.getOK() != null) {
            recorder = 1;
        }

        return recorder;
    }

    public String stopRecording(String adminid, String adminpassword, Integer entityID, String portalUrl) throws Exception {

        String strOK = "";

        URL url = new URL(portalUrl);
        QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
        Service service = Service.create(url, qname);
        vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);
        GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();

        getParticipantsRequest.setConferenceID(entityID);
        GetParticipantsResponse participantResponse = vidyoPortalAdminServicePort.getParticipants(getParticipantsRequest);

        Integer recorderID = null;
        if (null != participantResponse) {
            recorderID = participantResponse.getRecorderID().getValue();
        }

        if (null != recorderID && recorderID != 0) {
            strOK = stop(adminid, adminpassword, entityID, recorderID, portalUrl);
        }

        return strOK;

    }

    public String stop(String adminid, String adminpassword, Integer entityID, Integer recorderID, String portalUrl) throws Exception {
        String strOK = "";
        if (null != recorderID) {

            URL url = new URL(portalUrl);
            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);

            StopRecordingRequest stopRecordingRequest = new StopRecordingRequest();
            stopRecordingRequest.setConferenceID(entityID);
            stopRecordingRequest.setRecorderID(recorderID);

            StopRecordingResponse response = vidyoPortalAdminServicePort.stopRecording(stopRecordingRequest);
            strOK = response.getOK();
        }
        return strOK;
    }

}
