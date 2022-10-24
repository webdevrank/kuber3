package com.rank.ccms.dto;

import java.io.Serializable;

public class PerticipentDto implements Serializable {

    private Long id;
    private String perticipentId;
    private String name;
    private String loginId;
    private String nameAndLoginId;
    private String participantType;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAndLoginId() {
        return nameAndLoginId;
    }

    public void setNameAndLoginId(String nameAndLoginId) {
        this.nameAndLoginId = nameAndLoginId;
    }

    public String getPerticipentId() {
        return perticipentId;
    }

    public void setPerticipentId(String perticipentId) {
        this.perticipentId = perticipentId;
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

}
