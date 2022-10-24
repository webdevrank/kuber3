package com.rank.ccms.vidyo.util;

import com.rank.ccms.ws.contentmanage.DeleteRecordRequest;
import com.rank.ccms.ws.contentmanage.DeleteRecordResponse;
import com.rank.ccms.ws.contentmanage.RecordsSearchRequest;
import com.rank.ccms.ws.contentmanage.RecordsSearchResponse;
import com.rank.ccms.ws.contentmanage.SortBy;
import com.rank.ccms.ws.contentmanage.VidyoReplayContentManagementService;
import com.rank.ccms.ws.contentmanage.VidyoReplayContentManagementServicePortType;

import java.io.Serializable;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

public class VidyoAccessReplay implements Serializable {

    VidyoReplayContentManagementService vidyoReplayContentManagementService;
    VidyoReplayContentManagementServicePortType vidyoReplayContentManagementServicePortType;
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VidyoAccessReplay.class);

    public VidyoAccessReplay(String portalUrl) {
        try {
            if (!portalUrl.contains("?wsdl")) {
                portalUrl = portalUrl + "?wsdl";
            }
            vidyoReplayContentManagementService = new VidyoReplayContentManagementService(new URL(portalUrl));
            vidyoReplayContentManagementServicePortType = vidyoReplayContentManagementService.getVidyoReplayContentManagementServicePort();
        } catch (MalformedURLException e) {
            logger.error("Error:" + e.getMessage());
        }
    }

    public VidyoAccessReplay() {

    }

    public RecordsSearchResponse searchRecord(String userId, String password, String replayportalurl, String roomName) throws Exception {

        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setRoomFilter(roomName);
        searchRequest.setUsernameFilter(userId);
        searchRequest.setSortBy(SortBy.DATE);
        searchRequest.setLimit(1);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
        logger.info("After authentication in searchResponse search record" + searchResponse);
        return searchResponse;
    }

    public RecordsSearchResponse searchRecordStarting(String userId, String password, String replayportalurl) throws Exception {
        logger.info(" searchRecordStarting ");
        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        logger.info(" After Authentication");
        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setUsernameFilter(userId);
        searchRequest.setSortBy(SortBy.DATE);
        searchRequest.setLimit(1);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
        logger.info(" searchResponse " + searchResponse);
        return searchResponse;
    }

    public RecordsSearchResponse searchRecordSchedule(String userId, String password, String replayportalurl, String roomName) throws Exception {
        logger.info("in searchRecordSchedule userId " + userId + " password " + password + " replayportalurl " + replayportalurl + " roomName " + roomName);
        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }
        logger.info("searchRecordSchedule New Portal Url " + portalUrl1);
        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        logger.info("After authentication in searchRecordSchedule");
        RecordsSearchRequest parameter = new RecordsSearchRequest();
        parameter.setRoomFilter(roomName);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(parameter);
        logger.info("in searchRecordSchedule searchResponse" + searchResponse);
        return searchResponse;
    }

    public DeleteRecordResponse deleteRecord(String userId, String password, String replayportalurl, int Id) throws Exception {

        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        DeleteRecordRequest deleteRequest = new DeleteRecordRequest();
        deleteRequest.setId(Id);
        DeleteRecordResponse deleteRecordResponse = vidyoReplayContentManagementServicePortType.deleteRecord(deleteRequest);

        return deleteRecordResponse;
    }

    public RecordsSearchResponse searchRecordScheduleByRoomOwner(String userId, String password, String replayportalurl, String roomOwner) throws Exception {

        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setUsernameFilter(roomOwner);
        searchRequest.setLimit(1000);
        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);

        return searchResponse;
    }

}
