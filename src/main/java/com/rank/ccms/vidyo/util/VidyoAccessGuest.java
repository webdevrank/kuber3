package com.rank.ccms.vidyo.util;

import com.rank.ccms.ws.guest.AllLinesInUseFault_Exception;
import com.rank.ccms.ws.guest.ConferenceLockedFault_Exception;
import com.rank.ccms.ws.guest.GeneralFault_Exception;
import com.rank.ccms.ws.guest.GuestJoinConferenceRequest;
import com.rank.ccms.ws.guest.GuestJoinConferenceResponse;
import com.rank.ccms.ws.guest.InvalidArgumentFault_Exception;
import com.rank.ccms.ws.guest.LogInAsGuestRequest;
import com.rank.ccms.ws.guest.LogInAsGuestResponse;
import com.rank.ccms.ws.guest.ResourceNotAvailableFault_Exception;
import com.rank.ccms.ws.guest.RoomIsFullFault_Exception;
import com.rank.ccms.ws.guest.VidyoPortalGuestService;
import com.rank.ccms.ws.guest.VidyoPortalGuestServicePortType;
import com.rank.ccms.ws.guest.WrongPinFault_Exception;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

public class VidyoAccessGuest implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VidyoAccessGuest.class);
    VidyoPortalGuestService vidyoPortalGuestService;
    VidyoPortalGuestServicePortType vidyoPortalGuestServicePort;

    public VidyoAccessGuest(String portalUrl) {
        try {
            if (!portalUrl.contains("?wsdl")) {
                portalUrl = portalUrl + "?wsdl";
            }
            vidyoPortalGuestService = new VidyoPortalGuestService(new URL(portalUrl));
            vidyoPortalGuestServicePort = vidyoPortalGuestService.getVidyoPortalGuestServicePort();
        } catch (MalformedURLException e) {

        }
    }

    public VidyoAccessGuest() {

    }

    public void guestLogin(String userId, String password, String portalurl, String roomkey) {
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/guest", "VidyoPortalGuestService");
            Service service = Service.create(url, qname);
            vidyoPortalGuestServicePort = service.getPort(VidyoPortalGuestServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalGuestServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LogInAsGuestRequest logInAsGuestRequest = new LogInAsGuestRequest();
            logInAsGuestRequest.setRoomKey(roomkey);
            logInAsGuestRequest.setGuestName("guest");

            LogInAsGuestResponse logInAsGuestResponse = vidyoPortalGuestServicePort.logInAsGuest(logInAsGuestRequest);
            joinConference(logInAsGuestResponse.getGuestID(), portalurl, userId, password);
        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | ResourceNotAvailableFault_Exception | RoomIsFullFault_Exception ex) {
            Logger.getLogger(VidyoAccessGuest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String joinConference(int guestID, String portalurl, String userId, String password) {

        String ret = "";
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/guest", "VidyoPortalGuestService");
            Service service = Service.create(url, qname);
            vidyoPortalGuestServicePort = service.getPort(VidyoPortalGuestServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalGuestServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GuestJoinConferenceRequest gjcr = new GuestJoinConferenceRequest();
            gjcr.setGuestID(guestID);

            GuestJoinConferenceResponse conferenceResponse = vidyoPortalGuestServicePort.guestJoinConference(gjcr);
            ret = conferenceResponse.getOK();
        } catch (AllLinesInUseFault_Exception | MalformedURLException | ConferenceLockedFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | ResourceNotAvailableFault_Exception | RoomIsFullFault_Exception | WrongPinFault_Exception ex) {
            Logger.getLogger(VidyoAccessGuest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;
    }

}
