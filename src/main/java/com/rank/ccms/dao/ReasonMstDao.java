package com.rank.ccms.dao;

import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.rest.response.ReasonDto;
import java.util.List;

public interface ReasonMstDao extends GenericDao<ReasonMst> {

    public List<ReasonMst> findAllActivenNonDeletedReasonMsts();

    public List<ReasonMst> findAllActivenNonDeletedReasonMsts(String reasonType);

    public List<ReasonDto> findAllNonDeletedReasonsAsReasonDtoList();

    public ReasonMst findReasonMstByReasonCode(String reasonCode);

    public ReasonMst findReasonMstByReasonType(String reasonType);

}
