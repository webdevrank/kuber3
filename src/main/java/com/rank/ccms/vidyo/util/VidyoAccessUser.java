package com.rank.ccms.vidyo.util;

import com.rank.ccms.util.Constants;
import com.rank.ccms.ws.user.ControlMeetingFault_Exception;
import com.rank.ccms.ws.user.CreateRoomRequest;
import com.rank.ccms.ws.user.CreateRoomResponse;
import com.rank.ccms.ws.user.CreateRoomURLRequest;
import com.rank.ccms.ws.user.DeleteRoomRequest;
import com.rank.ccms.ws.user.Filter;
import com.rank.ccms.ws.user.Entity;
import com.rank.ccms.ws.user.GeneralFault_Exception;
import com.rank.ccms.ws.user.GetConferenceIDRequest;
import com.rank.ccms.ws.user.GetConferenceIDResponse;
import com.rank.ccms.ws.user.GetEntityByRoomKeyRequest;
import com.rank.ccms.ws.user.GetEntityByRoomKeyResponse;
import com.rank.ccms.ws.user.GetParticipantsRequest;
import com.rank.ccms.ws.user.GetParticipantsResponse;
import com.rank.ccms.ws.user.GetRecordingProfilesRequest;
import com.rank.ccms.ws.user.InPointToPointCallFault_Exception;
import com.rank.ccms.ws.user.InvalidArgumentFault_Exception;
import com.rank.ccms.ws.user.JoinConferenceRequest;
import com.rank.ccms.ws.user.JoinConferenceResponse;
import com.rank.ccms.ws.user.LeaveConferenceRequest;
import com.rank.ccms.ws.user.LockRoomRequest;
import com.rank.ccms.ws.user.LogInRequest;
import com.rank.ccms.ws.user.LogInResponse;
import com.rank.ccms.ws.user.MyAccountRequest;
import com.rank.ccms.ws.user.MyAccountResponse;
import com.rank.ccms.ws.user.NotLicensedFault_Exception;
import com.rank.ccms.ws.user.ResourceNotAvailableFault_Exception;
import com.rank.ccms.ws.user.SearchByEntityIDRequest;
import com.rank.ccms.ws.user.SearchByEntityIDResponse;
import com.rank.ccms.ws.user.SearchMyContactsRequest;
import com.rank.ccms.ws.user.SearchRequest;
import com.rank.ccms.ws.user.SearchResponse;
import com.rank.ccms.ws.user.SeatLicenseExpiredFault_Exception;
import com.rank.ccms.ws.user.StartRecordingRequest;
import com.rank.ccms.ws.user.StartRecordingResponse;
import com.rank.ccms.ws.user.StopRecordingRequest;
import com.rank.ccms.ws.user.StopRecordingResponse;
import com.rank.ccms.ws.user.UnlockRoomRequest;
import com.rank.ccms.ws.user.VidyoPortalUserService;
import com.rank.ccms.ws.user.VidyoPortalUserServicePortType;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

public class VidyoAccessUser implements Serializable {

    VidyoPortalUserService vidyoUserService;
    VidyoPortalUserServicePortType vidyoPortalUserServicePort;
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VidyoAccessUser.class);

    public VidyoAccessUser(String portalUrl) {
        try {
            if (!portalUrl.contains("?wsdl")) {
                portalUrl = portalUrl + "?wsdl";
            }

            vidyoUserService = new VidyoPortalUserService(new URL(portalUrl));
            vidyoPortalUserServicePort = vidyoUserService.getVidyoPortalUserServicePort();
        } catch (MalformedURLException e) {
            logger.error("Error VidyoAccessUser:" + e.getMessage());
        }
    }

    public VidyoAccessUser() {

    }

    public String getRoomLinkUrl(String userId, String password, String portalUrl) throws Exception {

        String roomURL;

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        if (portalUrl.contains("?wsdl")) {
            portalUrl = portalUrl.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        MyAccountRequest myAccountRequest = new MyAccountRequest();
        MyAccountResponse response = vidyoPortalUserServicePort.myAccount(myAccountRequest);
        roomURL = response.getEntity().getRoomMode().getRoomURL().getValue();

        return roomURL;

    }

    public MyAccountResponse getMyaccount(String userId, String password, String portalUrl) throws Exception {

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        if (portalUrl.contains("?wsdl")) {
            portalUrl = portalUrl.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        MyAccountRequest myAccountRequest = new MyAccountRequest();
        MyAccountResponse response = vidyoPortalUserServicePort.myAccount(myAccountRequest);

        return response;

    }

    public boolean vidyoPortalLogin(String userId, String password, String portalurl) {
        boolean logged = false;
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LogInRequest logInRequest = new LogInRequest();
            LogInResponse response = vidyoPortalUserServicePort.logIn(logInRequest);

            if (response != null) {
                logged = true;
            }

        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception | WebServiceException e) {
            logger.error("Error vidyoPortalLogin:" + e.getMessage());
            logged = false;
        }
        return logged;
    }

    public String getEntityID(String roomURL, String userId, String password, String portalurl) throws Exception {
        String strEntityID;

        String[] tempArr = roomURL.split("=");
        String roomKey;
        roomKey = tempArr[1];

        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        GetEntityByRoomKeyRequest getEntityByRoomKeyRequest = new GetEntityByRoomKeyRequest();
        getEntityByRoomKeyRequest.setRoomKey(roomKey);
        GetEntityByRoomKeyResponse roomKeyResponse = vidyoPortalUserServicePort.getEntityByRoomKey(getEntityByRoomKeyRequest);

        strEntityID = roomKeyResponse.getEntity().getEntityID();

        return strEntityID;

    }

    public int startRecording(String entityID, String userId, String password, String portalurl) throws ControlMeetingFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, ResourceNotAvailableFault_Exception, SeatLicenseExpiredFault_Exception, MalformedURLException {
        int recorder;
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        StartRecordingRequest startRecordingRequest = new StartRecordingRequest();
        startRecordingRequest.setConferenceID(entityID);
        startRecordingRequest.setRecorderPrefix(Constants.recorderPrefix);
        startRecordingRequest.setWebcast(true);

        StartRecordingResponse recordingResponse = vidyoPortalUserServicePort.startRecording(startRecordingRequest);
        recordingResponse.getOK();
        recorder = 1;

        return recorder;
    }

    public String stopRecording(String roomURL, String userId, String password, String portalurl, String entityID) throws MalformedURLException, ControlMeetingFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception {
        String strOK = "";

        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
        getParticipantsRequest.setConferenceID(entityID);

        GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);

        Integer recorderID = null;
        if (participantResponse.getRecorderID() != null) {
            recorderID = participantResponse.getRecorderID().getValue();
        }
        if (recorderID != null) {
            if (recorderID != 0) {
                stop(userId, password, portalurl, entityID, recorderID);
            }
        }

        return strOK;

    }

    public void stop(String userId, String password, String portalurl, String entityID, int recorderID) {
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            StopRecordingRequest stopRecordingRequest = new StopRecordingRequest();
            stopRecordingRequest.setConferenceID(entityID);
            stopRecordingRequest.setRecorderID(recorderID);

            StopRecordingResponse response = vidyoPortalUserServicePort.stopRecording(stopRecordingRequest);
            String strOK = response.getOK();
        } catch (ControlMeetingFault_Exception | MalformedURLException | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int vidyoPortalOnlineUserCount(String portalUrl, String adminid, String adminpassword) throws Exception {
        int count = 0;

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);

        SearchRequest searchRequest = new SearchRequest();
        Filter filter = new Filter();

        searchRequest.setFilter(filter);
        SearchResponse response = vidyoPortalUserServicePort.search(searchRequest);
        List<Entity> entityList = response.getEntity();
        for (Entity entityList1 : entityList) {
            if (entityList1.getMemberStatus().equalsIgnoreCase("Online")) {
                count++;
            }
        }

        return count;
    }

    public String join(String userId, String password, String conferenceid, String portalurl) throws Exception {
        String returnStatus;

        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        JoinConferenceRequest joinConferenceRequest = new JoinConferenceRequest();
        joinConferenceRequest.setConferenceID(conferenceid);

        JoinConferenceResponse joinConferenceResponse = vidyoPortalUserServicePort.joinConference(joinConferenceRequest);
        returnStatus = joinConferenceResponse.getOK();
        logger.info("Join conference successfully==========>" + conferenceid);
        return returnStatus;
    }

    public boolean leaveConference(String userId, String password, String portalurl, String conferenceid) throws Exception {
        boolean logged = false;
        try {

            String participantid = getParticipantid(userId, password, portalurl, conferenceid);

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LeaveConferenceRequest leaveConferenceRequest = new LeaveConferenceRequest();
            leaveConferenceRequest.setConferenceID(conferenceid);
            leaveConferenceRequest.setParticipantID(participantid);
            vidyoPortalUserServicePort.leaveConference(leaveConferenceRequest);
        } catch (WebServiceException e) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, e);
            logged = false;
        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logged;
    }

    public String getParticipantid(String userId, String password, String portalurl, String conferenceid) throws Exception {
        String participantid = "";
        String extension = this.getMyaccount(userId, password, portalurl).getEntity().getExtension();
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(conferenceid);
            GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);
            List<Entity> participantlist = participantResponse.getEntity();

            for (Entity participantlist1 : participantlist) {
                if (participantlist1.getExtension().equalsIgnoreCase(extension)) {
                    participantid = (String) participantlist1.getParticipantID().getValue();
                }
            }

        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participantid;
    }

    public void createRoomURL(String userId, String password, String roomId, String portalurl) throws Exception {

        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        CreateRoomURLRequest createRoomURLRequest = new CreateRoomURLRequest();
        createRoomURLRequest.setRoomID(roomId);
        vidyoPortalUserServicePort.createRoomURL(createRoomURLRequest);

    }

    public String createRoom(String userId, String password, String roomName, String portalUrl) throws Exception {
        String roomlink;
        String entityid;
        String ret;

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        CreateRoomRequest createRoomRequest = new CreateRoomRequest();
        Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
        createRoomRequest.setName(roomName);
        String extension = "";
        if (portalUrl.trim().equalsIgnoreCase(Constants.vidyoportalUserServiceWSDL.trim())) {
            extension = Constants.portalExtention.concat(number.toString());
        }

        createRoomRequest.setExtension(extension);

        CreateRoomResponse createRoomResponse = vidyoPortalUserServicePort.createRoom(createRoomRequest);
        roomlink = (String) createRoomResponse.getEntity().getRoomMode().getRoomURL().getValue();
        entityid = createRoomResponse.getEntity().getEntityID();

        ret = roomlink + "," + entityid + "," + userId + "" + number;

        return ret;
    }

    public void deleteRoom(String userId, String password, String portalUrl, String roomId) {

        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            DeleteRoomRequest deleteRoomRequest = new DeleteRoomRequest();
            deleteRoomRequest.setRoomID(roomId);

            vidyoPortalUserServicePort.deleteRoom(deleteRoomRequest);

        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Entity> getAllRoomsByOwnerId(String userId, String password, String portalUrl, String ownerid) throws Exception {

        List<Entity> entityList;

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        SearchByEntityIDRequest searchByEntityIDRequest = new SearchByEntityIDRequest();
        searchByEntityIDRequest.setEntityID(ownerid);

        SearchByEntityIDResponse searchByEntityIDResponse = vidyoPortalUserServicePort.searchByEntityID(searchByEntityIDRequest);
        entityList = searchByEntityIDResponse.getEntity();

        return entityList;

    }

    public String getConferenceId(String userId, String password, String portalUrl) {

        String conferenceId = "";
        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetConferenceIDRequest getConferenceIDRequest = new GetConferenceIDRequest();

            GetConferenceIDResponse getConferenceIDResponse;
            try {
                getConferenceIDResponse = vidyoPortalUserServicePort.getConferenceID(getConferenceIDRequest);
                conferenceId = (String) getConferenceIDResponse.getConferenceID().getValue();
            } catch (InPointToPointCallFault_Exception ex) {
                Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (GeneralFault_Exception | MalformedURLException | NotLicensedFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conferenceId;

    }

    public void getRecordingProfiles(String userId, String password, String portalUrl) {

        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetRecordingProfilesRequest getRecordingProfilesRequest = new GetRecordingProfilesRequest();

            vidyoPortalUserServicePort.getRecordingProfiles(getRecordingProfilesRequest);

        } catch (GeneralFault_Exception | MalformedURLException | NotLicensedFault_Exception | InvalidArgumentFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public GetParticipantsResponse getParticipants(String entityID, String userId, String password, String portalurl) {

        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(entityID);
            GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);

            return participantResponse;
        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception | ControlMeetingFault_Exception e) {
            logger.error("Error:getParticipants==============>" + e.getMessage());
            return null;
        }

    }

    public void seachMyContacts(String userId, String password, String portalurl) {
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            SearchMyContactsRequest searchMyContactsRequest = new SearchMyContactsRequest();

            vidyoPortalUserServicePort.searchMyContacts(searchMyContactsRequest);
        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void lockRoom(String userId, String password, String portalurl, String roomId) throws Exception {
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        LockRoomRequest lockRoomRequest = new LockRoomRequest();
        lockRoomRequest.setRoomID(roomId);
        vidyoPortalUserServicePort.lockRoom(lockRoomRequest);

    }

    public void unLockRoom(String userId, String password, String portalurl, String roomId) throws Exception {
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        UnlockRoomRequest unlockRoomRequest = new UnlockRoomRequest();
        unlockRoomRequest.setRoomID(roomId);

        vidyoPortalUserServicePort.unlockRoom(unlockRoomRequest);

    }

    public List<Entity> getParticipantidList(String userId, String password, String portalurl, String conferenceid) throws Exception {
        List<Entity> participantlist = new ArrayList<>();
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(conferenceid);
            GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);
            participantlist = participantResponse.getEntity();

        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participantlist;
    }

    public boolean forceLeaveConference(String userId, String password, String portalurl, String conferenceid, String participantid) throws Exception {
        boolean logged = false;
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LeaveConferenceRequest leaveConferenceRequest = new LeaveConferenceRequest();
            leaveConferenceRequest.setConferenceID(conferenceid);
            leaveConferenceRequest.setParticipantID(participantid);
            vidyoPortalUserServicePort.leaveConference(leaveConferenceRequest);
        } catch (WebServiceException e) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, e);
            logged = false;
        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logged;
    }
}
