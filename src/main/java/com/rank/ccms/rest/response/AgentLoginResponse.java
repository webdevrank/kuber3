package com.rank.ccms.rest.response;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-14T07:52:19.544+05:30")
@ApiModel("AgentLoginResponse Model")
@JsonInclude(Include.NON_NULL)
public class AgentLoginResponse implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 9098326662734700367L;
    @ApiModelProperty(value = "the id of the employee")
    private Long empId;
    private String validateMsg;
    private String Firstname;
    private String lastName;
    private String loginId;
    private String empType;
    private String socketHostPublic;
    @ApiModelProperty(value = "Contain the success message if the call is successful")
    private String succMsg;
    private String roomLink;
    private String roomName;
    private String entityId;
    private String downTimeMessage;
    private String downTimeCheck;
    private String downTimeStart;
    private String downTimeEnd;
    private String serviceTimeText;

    public String getServiceTimeText() {
        return serviceTimeText;
    }

    public void setServiceTimeText(String serviceTimeText) {
        this.serviceTimeText = serviceTimeText;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getValidateMsg() {
        return validateMsg;
    }

    public void setValidateMsg(String validateMsg) {
        this.validateMsg = validateMsg;
    }

    public String getSocketHostPublic() {
        return socketHostPublic;
    }

    public void setSocketHostPublic(String socketHostPublic) {
        this.socketHostPublic = socketHostPublic;
    }

    public String getSuccMsg() {
        return succMsg;
    }

    public void setSuccMsg(String succMsg) {
        this.succMsg = succMsg;
    }

    public String getRoomLink() {
        return roomLink;
    }

    public void setRoomLink(String roomLink) {
        this.roomLink = roomLink;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the downTimeMessage
     */
    public String getDownTimeMessage() {
        return downTimeMessage;
    }

    /**
     * @param downTimeMessage the downTimeMessage to set
     */
    public void setDownTimeMessage(String downTimeMessage) {
        this.downTimeMessage = downTimeMessage;
    }

    /**
     * @return the downTimeCheck
     */
    public String getDownTimeCheck() {
        return downTimeCheck;
    }

    /**
     * @param downTimeCheck the downTimeCheck to set
     */
    public void setDownTimeCheck(String downTimeCheck) {
        this.downTimeCheck = downTimeCheck;
    }

    /**
     * @return the downTimeStart
     */
    public String getDownTimeStart() {
        return downTimeStart;
    }

    /**
     * @param downTimeStart the downTimeStart to set
     */
    public void setDownTimeStart(String downTimeStart) {
        this.downTimeStart = downTimeStart;
    }

    /**
     * @return the downTimeEnd
     */
    public String getDownTimeEnd() {
        return downTimeEnd;
    }

    /**
     * @param downTimeEnd the downTimeEnd to set
     */
    public void setDownTimeEnd(String downTimeEnd) {
        this.downTimeEnd = downTimeEnd;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

}
