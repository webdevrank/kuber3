package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ReasonMst;
import java.io.Serializable;
import java.util.List;

public interface ReasonMstService extends Serializable {

    ReasonMst save(ReasonMst reasonMst, EmployeeMst employeeMst);

    List<ReasonMst> getAllNonDeletedReasons();

    List<ReasonMst> findAllActivenNonDeletedReasonMsts();

    List<ReasonMst> findAllActivenNonDeletedReasonMsts(String reasonType);

    ReasonMst findNonDeletedById(Long id);

    ReasonMst findReasonMstByReasonCode(String reasonCode);

    ReasonMst findReasonMstByReasonType(String reasonType);

}
