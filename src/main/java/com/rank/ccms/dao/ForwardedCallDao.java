package com.rank.ccms.dao;

import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import java.util.List;

public interface ForwardedCallDao extends GenericDao<ForwardedCall> {

    public List<ForwardedCall> findAllActivenNonDeletedForwardedCalls();

    public List<ForwardedCall> findActiveForwardedCallByEmployeeMst(EmployeeMst employee);

    public ForwardedCall findForwardedCallByCallIdAndEmpId(CallMst callmst);

    public List<ForwardedCall> findForwardedCallByCallId(CallMst callmst);

    public List<ForwardedCall> findForwardedCallByCallIdAndEmpId(CallMst callmst, EmployeeMst employee);

    public ForwardedCall findByIdAll(Integer id);

    public List<ForwardedCall> findForwardedCallByCallDtlId(Long calldtlId);

}
