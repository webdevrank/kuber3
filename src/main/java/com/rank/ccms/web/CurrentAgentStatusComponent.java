package com.rank.ccms.web;

import com.rank.ccms.dto.AgentStatusDto;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.dto.CurrentAgentStatusDto;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.util.CustomConvert;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CurrentAgentStatusComponent implements Serializable {

    private CurrentAgentStatusDto currentAgentStatusDto;
    private EmployeeMst employeeMst;
    private List<EmployeeMst> listAgent;

    private List<CurrentAgentStatusDto> listCurrentAgentStatusDto;
    private EmployeeTypeMst employeeTypeMst;

    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;

    public void agentStatusReportWithQuery() throws ParseException {
        listCurrentAgentStatusDto = new ArrayList<>();
        List<AgentStatusDto> agentStatusDtoList = employeeActivityDtlService.listCurrentAgentStatus();
        Timestamp currTime = CustomConvert.javaDateToTimeStamp(new Date());

        for (AgentStatusDto singleAgent : agentStatusDtoList) {
            currentAgentStatusDto = new CurrentAgentStatusDto();
            long diff1 = currTime.getTime() - singleAgent.getStarttime().getTime();

            currentAgentStatusDto.setAgentName(singleAgent.getAgentfullname());
            currentAgentStatusDto.setAgenyId(singleAgent.getLoginid());
            currentAgentStatusDto.setStatusAgent(singleAgent.getStatusagent());
            currentAgentStatusDto.setStatusTime(generateDiff(diff1));
            currentAgentStatusDto.setStatusReady(singleAgent.getStatusready());
            currentAgentStatusDto.setStatusAvailable(singleAgent.getStatusavailable());
            currentAgentStatusDto.setStatusBusy(singleAgent.getStatusbusy());
            currentAgentStatusDto.setStatusTimeAvailable("");
            currentAgentStatusDto.setStatusTimeBusy("");

            listCurrentAgentStatusDto.add(currentAgentStatusDto);

        }
    }

    public String generateDiff(long timeInMilliSeconds) {

        long hours, minutes, seconds;
        long timeInSeconds = timeInMilliSeconds / 1000;
        hours = timeInSeconds / 3600;
        String sHours;
        String sMinutes;
        String sSeconds;
        if (hours < 10) {
            sHours = "0" + String.valueOf(hours);
        } else {
            sHours = "" + String.valueOf(hours);
        }

        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        if (minutes < 10) {
            sMinutes = "0" + String.valueOf(minutes);
        } else {
            sMinutes = "" + String.valueOf(minutes);
        }
        timeInSeconds = timeInSeconds - (minutes * 60);
        seconds = timeInSeconds;
        if (seconds < 10) {
            sSeconds = "0" + String.valueOf(seconds);
        } else {
            sSeconds = "" + String.valueOf(seconds);
        }

        String callDate = sHours + ":" + sMinutes + ":" + sSeconds;
        return callDate;

    }

    public CurrentAgentStatusDto getCurrentAgentStatusDto() {
        return currentAgentStatusDto;
    }

    public void setCurrentAgentStatusDto(CurrentAgentStatusDto currentAgentStatusDto) {
        this.currentAgentStatusDto = currentAgentStatusDto;
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public List<EmployeeMst> getListAgent() {
        return listAgent;
    }

    public void setListAgent(List<EmployeeMst> listAgent) {
        this.listAgent = listAgent;
    }

    public List<CurrentAgentStatusDto> getListCurrentAgentStatusDto() {
        return listCurrentAgentStatusDto;
    }

    public void setListCurrentAgentStatusDto(List<CurrentAgentStatusDto> listCurrentAgentStatusDto) {
        this.listCurrentAgentStatusDto = listCurrentAgentStatusDto;
    }

    public EmployeeTypeMst getEmployeeTypeMst() {
        return employeeTypeMst;
    }

    public void setEmployeeTypeMst(EmployeeTypeMst employeeTypeMst) {
        this.employeeTypeMst = employeeTypeMst;
    }

}
