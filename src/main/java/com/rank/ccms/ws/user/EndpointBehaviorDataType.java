
package com.rank.ccms.ws.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 		            The EndpointBehaviorDataType will hold the parameters for EndpointBehavior configurations.
 * 		          
 * 
 * <p>Java class for EndpointBehaviorDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EndpointBehaviorDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="windowSizeHeight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="windowSizeWidth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="windowPositionTop" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="windowPositionBottom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="windowPositionLeft" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="windowPositionRight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="welcomePage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="beautyScreen" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="loginModule" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="publicChat" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="leftPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inCallSearch" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inviteParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="contentSharing" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="shareDialogOnJoin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="displayLabels" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remoteContentAccess" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cameraMuteControl" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="muteCameraOnEntry" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="audioMuteControl" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="muteAudioOnEntry" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="deviceSettings" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pinnedParticipant" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recordConference" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recordingRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exitOnUserHangup" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="automaticallyUpdate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lockUserName" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="enableAutoAnswer" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="participantNotification" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fullScreenVideo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="preIframeUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preIframeSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="topIframeUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="topIframeSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="leftIframeUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leftIframeSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rightIframeUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rightIframeSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bottomIframeUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bottomIframeSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="postIframeUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postIframeSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="endpointBehaviorKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndpointBehaviorDataType", propOrder = {
    "windowSizeHeight",
    "windowSizeWidth",
    "windowPositionTop",
    "windowPositionBottom",
    "windowPositionLeft",
    "windowPositionRight",
    "welcomePage",
    "beautyScreen",
    "loginModule",
    "publicChat",
    "leftPanel",
    "inCallSearch",
    "inviteParticipants",
    "contentSharing",
    "shareDialogOnJoin",
    "displayLabels",
    "remoteContentAccess",
    "cameraMuteControl",
    "muteCameraOnEntry",
    "audioMuteControl",
    "muteAudioOnEntry",
    "deviceSettings",
    "pinnedParticipant",
    "recordConference",
    "recordingRole",
    "exitOnUserHangup",
    "automaticallyUpdate",
    "lockUserName",
    "enableAutoAnswer",
    "participantNotification",
    "fullScreenVideo",
    "preIframeUrl",
    "preIframeSize",
    "topIframeUrl",
    "topIframeSize",
    "leftIframeUrl",
    "leftIframeSize",
    "rightIframeUrl",
    "rightIframeSize",
    "bottomIframeUrl",
    "bottomIframeSize",
    "postIframeUrl",
    "postIframeSize",
    "endpointBehaviorKey"
})
public class EndpointBehaviorDataType {

    protected Integer windowSizeHeight;
    protected Integer windowSizeWidth;
    protected Integer windowPositionTop;
    protected Integer windowPositionBottom;
    protected Integer windowPositionLeft;
    protected Integer windowPositionRight;
    @XmlElement(defaultValue = "1")
    protected int welcomePage;
    @XmlElement(defaultValue = "1")
    protected int beautyScreen;
    @XmlElement(defaultValue = "1")
    protected int loginModule;
    @XmlElement(defaultValue = "1")
    protected int publicChat;
    @XmlElement(defaultValue = "1")
    protected int leftPanel;
    @XmlElement(defaultValue = "1")
    protected int inCallSearch;
    @XmlElement(defaultValue = "1")
    protected int inviteParticipants;
    @XmlElement(defaultValue = "1")
    protected int contentSharing;
    @XmlElement(defaultValue = "0")
    protected int shareDialogOnJoin;
    @XmlElement(defaultValue = "1")
    protected int displayLabels;
    @XmlElement(defaultValue = "1")
    protected int remoteContentAccess;
    @XmlElement(defaultValue = "1")
    protected int cameraMuteControl;
    @XmlElement(defaultValue = "1")
    protected int muteCameraOnEntry;
    @XmlElement(defaultValue = "1")
    protected int audioMuteControl;
    @XmlElement(defaultValue = "1")
    protected int muteAudioOnEntry;
    @XmlElement(defaultValue = "1")
    protected int deviceSettings;
    @XmlElement(defaultValue = "0")
    protected int pinnedParticipant;
    @XmlElement(defaultValue = "0")
    protected int recordConference;
    protected String recordingRole;
    @XmlElement(defaultValue = "0")
    protected int exitOnUserHangup;
    @XmlElement(defaultValue = "1")
    protected int automaticallyUpdate;
    @XmlElement(defaultValue = "0")
    protected int lockUserName;
    @XmlElement(defaultValue = "0")
    protected int enableAutoAnswer;
    @XmlElement(defaultValue = "1")
    protected int participantNotification;
    @XmlElement(defaultValue = "0")
    protected int fullScreenVideo;
    protected String preIframeUrl;
    protected Integer preIframeSize;
    protected String topIframeUrl;
    protected Integer topIframeSize;
    protected String leftIframeUrl;
    protected Integer leftIframeSize;
    protected String rightIframeUrl;
    protected Integer rightIframeSize;
    protected String bottomIframeUrl;
    protected Integer bottomIframeSize;
    protected String postIframeUrl;
    protected Integer postIframeSize;
    protected String endpointBehaviorKey;

    /**
     * Gets the value of the windowSizeHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowSizeHeight() {
        return windowSizeHeight;
    }

    /**
     * Sets the value of the windowSizeHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowSizeHeight(Integer value) {
        this.windowSizeHeight = value;
    }

    /**
     * Gets the value of the windowSizeWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowSizeWidth() {
        return windowSizeWidth;
    }

    /**
     * Sets the value of the windowSizeWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowSizeWidth(Integer value) {
        this.windowSizeWidth = value;
    }

    /**
     * Gets the value of the windowPositionTop property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowPositionTop() {
        return windowPositionTop;
    }

    /**
     * Sets the value of the windowPositionTop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowPositionTop(Integer value) {
        this.windowPositionTop = value;
    }

    /**
     * Gets the value of the windowPositionBottom property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowPositionBottom() {
        return windowPositionBottom;
    }

    /**
     * Sets the value of the windowPositionBottom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowPositionBottom(Integer value) {
        this.windowPositionBottom = value;
    }

    /**
     * Gets the value of the windowPositionLeft property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowPositionLeft() {
        return windowPositionLeft;
    }

    /**
     * Sets the value of the windowPositionLeft property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowPositionLeft(Integer value) {
        this.windowPositionLeft = value;
    }

    /**
     * Gets the value of the windowPositionRight property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWindowPositionRight() {
        return windowPositionRight;
    }

    /**
     * Sets the value of the windowPositionRight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWindowPositionRight(Integer value) {
        this.windowPositionRight = value;
    }

    /**
     * Gets the value of the welcomePage property.
     * 
     */
    public int getWelcomePage() {
        return welcomePage;
    }

    /**
     * Sets the value of the welcomePage property.
     * 
     */
    public void setWelcomePage(int value) {
        this.welcomePage = value;
    }

    /**
     * Gets the value of the beautyScreen property.
     * 
     */
    public int getBeautyScreen() {
        return beautyScreen;
    }

    /**
     * Sets the value of the beautyScreen property.
     * 
     */
    public void setBeautyScreen(int value) {
        this.beautyScreen = value;
    }

    /**
     * Gets the value of the loginModule property.
     * 
     */
    public int getLoginModule() {
        return loginModule;
    }

    /**
     * Sets the value of the loginModule property.
     * 
     */
    public void setLoginModule(int value) {
        this.loginModule = value;
    }

    /**
     * Gets the value of the publicChat property.
     * 
     */
    public int getPublicChat() {
        return publicChat;
    }

    /**
     * Sets the value of the publicChat property.
     * 
     */
    public void setPublicChat(int value) {
        this.publicChat = value;
    }

    /**
     * Gets the value of the leftPanel property.
     * 
     */
    public int getLeftPanel() {
        return leftPanel;
    }

    /**
     * Sets the value of the leftPanel property.
     * 
     */
    public void setLeftPanel(int value) {
        this.leftPanel = value;
    }

    /**
     * Gets the value of the inCallSearch property.
     * 
     */
    public int getInCallSearch() {
        return inCallSearch;
    }

    /**
     * Sets the value of the inCallSearch property.
     * 
     */
    public void setInCallSearch(int value) {
        this.inCallSearch = value;
    }

    /**
     * Gets the value of the inviteParticipants property.
     * 
     */
    public int getInviteParticipants() {
        return inviteParticipants;
    }

    /**
     * Sets the value of the inviteParticipants property.
     * 
     */
    public void setInviteParticipants(int value) {
        this.inviteParticipants = value;
    }

    /**
     * Gets the value of the contentSharing property.
     * 
     */
    public int getContentSharing() {
        return contentSharing;
    }

    /**
     * Sets the value of the contentSharing property.
     * 
     */
    public void setContentSharing(int value) {
        this.contentSharing = value;
    }

    /**
     * Gets the value of the shareDialogOnJoin property.
     * 
     */
    public int getShareDialogOnJoin() {
        return shareDialogOnJoin;
    }

    /**
     * Sets the value of the shareDialogOnJoin property.
     * 
     */
    public void setShareDialogOnJoin(int value) {
        this.shareDialogOnJoin = value;
    }

    /**
     * Gets the value of the displayLabels property.
     * 
     */
    public int getDisplayLabels() {
        return displayLabels;
    }

    /**
     * Sets the value of the displayLabels property.
     * 
     */
    public void setDisplayLabels(int value) {
        this.displayLabels = value;
    }

    /**
     * Gets the value of the remoteContentAccess property.
     * 
     */
    public int getRemoteContentAccess() {
        return remoteContentAccess;
    }

    /**
     * Sets the value of the remoteContentAccess property.
     * 
     */
    public void setRemoteContentAccess(int value) {
        this.remoteContentAccess = value;
    }

    /**
     * Gets the value of the cameraMuteControl property.
     * 
     */
    public int getCameraMuteControl() {
        return cameraMuteControl;
    }

    /**
     * Sets the value of the cameraMuteControl property.
     * 
     */
    public void setCameraMuteControl(int value) {
        this.cameraMuteControl = value;
    }

    /**
     * Gets the value of the muteCameraOnEntry property.
     * 
     */
    public int getMuteCameraOnEntry() {
        return muteCameraOnEntry;
    }

    /**
     * Sets the value of the muteCameraOnEntry property.
     * 
     */
    public void setMuteCameraOnEntry(int value) {
        this.muteCameraOnEntry = value;
    }

    /**
     * Gets the value of the audioMuteControl property.
     * 
     */
    public int getAudioMuteControl() {
        return audioMuteControl;
    }

    /**
     * Sets the value of the audioMuteControl property.
     * 
     */
    public void setAudioMuteControl(int value) {
        this.audioMuteControl = value;
    }

    /**
     * Gets the value of the muteAudioOnEntry property.
     * 
     */
    public int getMuteAudioOnEntry() {
        return muteAudioOnEntry;
    }

    /**
     * Sets the value of the muteAudioOnEntry property.
     * 
     */
    public void setMuteAudioOnEntry(int value) {
        this.muteAudioOnEntry = value;
    }

    /**
     * Gets the value of the deviceSettings property.
     * 
     */
    public int getDeviceSettings() {
        return deviceSettings;
    }

    /**
     * Sets the value of the deviceSettings property.
     * 
     */
    public void setDeviceSettings(int value) {
        this.deviceSettings = value;
    }

    /**
     * Gets the value of the pinnedParticipant property.
     * 
     */
    public int getPinnedParticipant() {
        return pinnedParticipant;
    }

    /**
     * Sets the value of the pinnedParticipant property.
     * 
     */
    public void setPinnedParticipant(int value) {
        this.pinnedParticipant = value;
    }

    /**
     * Gets the value of the recordConference property.
     * 
     */
    public int getRecordConference() {
        return recordConference;
    }

    /**
     * Sets the value of the recordConference property.
     * 
     */
    public void setRecordConference(int value) {
        this.recordConference = value;
    }

    /**
     * Gets the value of the recordingRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordingRole() {
        return recordingRole;
    }

    /**
     * Sets the value of the recordingRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordingRole(String value) {
        this.recordingRole = value;
    }

    /**
     * Gets the value of the exitOnUserHangup property.
     * 
     */
    public int getExitOnUserHangup() {
        return exitOnUserHangup;
    }

    /**
     * Sets the value of the exitOnUserHangup property.
     * 
     */
    public void setExitOnUserHangup(int value) {
        this.exitOnUserHangup = value;
    }

    /**
     * Gets the value of the automaticallyUpdate property.
     * 
     */
    public int getAutomaticallyUpdate() {
        return automaticallyUpdate;
    }

    /**
     * Sets the value of the automaticallyUpdate property.
     * 
     */
    public void setAutomaticallyUpdate(int value) {
        this.automaticallyUpdate = value;
    }

    /**
     * Gets the value of the lockUserName property.
     * 
     */
    public int getLockUserName() {
        return lockUserName;
    }

    /**
     * Sets the value of the lockUserName property.
     * 
     */
    public void setLockUserName(int value) {
        this.lockUserName = value;
    }

    /**
     * Gets the value of the enableAutoAnswer property.
     * 
     */
    public int getEnableAutoAnswer() {
        return enableAutoAnswer;
    }

    /**
     * Sets the value of the enableAutoAnswer property.
     * 
     */
    public void setEnableAutoAnswer(int value) {
        this.enableAutoAnswer = value;
    }

    /**
     * Gets the value of the participantNotification property.
     * 
     */
    public int getParticipantNotification() {
        return participantNotification;
    }

    /**
     * Sets the value of the participantNotification property.
     * 
     */
    public void setParticipantNotification(int value) {
        this.participantNotification = value;
    }

    /**
     * Gets the value of the fullScreenVideo property.
     * 
     */
    public int getFullScreenVideo() {
        return fullScreenVideo;
    }

    /**
     * Sets the value of the fullScreenVideo property.
     * 
     */
    public void setFullScreenVideo(int value) {
        this.fullScreenVideo = value;
    }

    /**
     * Gets the value of the preIframeUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreIframeUrl() {
        return preIframeUrl;
    }

    /**
     * Sets the value of the preIframeUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreIframeUrl(String value) {
        this.preIframeUrl = value;
    }

    /**
     * Gets the value of the preIframeSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPreIframeSize() {
        return preIframeSize;
    }

    /**
     * Sets the value of the preIframeSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPreIframeSize(Integer value) {
        this.preIframeSize = value;
    }

    /**
     * Gets the value of the topIframeUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopIframeUrl() {
        return topIframeUrl;
    }

    /**
     * Sets the value of the topIframeUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopIframeUrl(String value) {
        this.topIframeUrl = value;
    }

    /**
     * Gets the value of the topIframeSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTopIframeSize() {
        return topIframeSize;
    }

    /**
     * Sets the value of the topIframeSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTopIframeSize(Integer value) {
        this.topIframeSize = value;
    }

    /**
     * Gets the value of the leftIframeUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeftIframeUrl() {
        return leftIframeUrl;
    }

    /**
     * Sets the value of the leftIframeUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeftIframeUrl(String value) {
        this.leftIframeUrl = value;
    }

    /**
     * Gets the value of the leftIframeSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLeftIframeSize() {
        return leftIframeSize;
    }

    /**
     * Sets the value of the leftIframeSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLeftIframeSize(Integer value) {
        this.leftIframeSize = value;
    }

    /**
     * Gets the value of the rightIframeUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightIframeUrl() {
        return rightIframeUrl;
    }

    /**
     * Sets the value of the rightIframeUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightIframeUrl(String value) {
        this.rightIframeUrl = value;
    }

    /**
     * Gets the value of the rightIframeSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRightIframeSize() {
        return rightIframeSize;
    }

    /**
     * Sets the value of the rightIframeSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRightIframeSize(Integer value) {
        this.rightIframeSize = value;
    }

    /**
     * Gets the value of the bottomIframeUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBottomIframeUrl() {
        return bottomIframeUrl;
    }

    /**
     * Sets the value of the bottomIframeUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBottomIframeUrl(String value) {
        this.bottomIframeUrl = value;
    }

    /**
     * Gets the value of the bottomIframeSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBottomIframeSize() {
        return bottomIframeSize;
    }

    /**
     * Sets the value of the bottomIframeSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBottomIframeSize(Integer value) {
        this.bottomIframeSize = value;
    }

    /**
     * Gets the value of the postIframeUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostIframeUrl() {
        return postIframeUrl;
    }

    /**
     * Sets the value of the postIframeUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostIframeUrl(String value) {
        this.postIframeUrl = value;
    }

    /**
     * Gets the value of the postIframeSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPostIframeSize() {
        return postIframeSize;
    }

    /**
     * Sets the value of the postIframeSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPostIframeSize(Integer value) {
        this.postIframeSize = value;
    }

    /**
     * Gets the value of the endpointBehaviorKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointBehaviorKey() {
        return endpointBehaviorKey;
    }

    /**
     * Sets the value of the endpointBehaviorKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointBehaviorKey(String value) {
        this.endpointBehaviorKey = value;
    }

}
