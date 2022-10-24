package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import java.io.Serializable;
import java.util.Set;

public interface AgentFindingService extends Serializable {

    Set<EmployeeMst> findAgents(String segment, String service, String language);

    Boolean isCcmsDown();

}
