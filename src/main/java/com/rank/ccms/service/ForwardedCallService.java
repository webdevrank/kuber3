package com.rank.ccms.service;

import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import java.io.Serializable;
import java.util.List;

public interface ForwardedCallService extends Serializable {

    ForwardedCall saveForwardedCalls(ForwardedCall forwardedCall, EmployeeMst employeeMst);

    List<ForwardedCall> findAllForwardedCalls();

    List<ForwardedCall> findAllNonDeletedForwardedCalls();

    ForwardedCall findForwardedCallById(Integer callId);

    ForwardedCall findNonDeletedForwardedCallById(Long callid);

    List<ForwardedCall> findAllActivenNonDeletedForwardedCalls();

    ForwardedCall findActiveForwardedCallByEmployeeMst(EmployeeMst employeeMst);

    List<ForwardedCall> findActiveForwardedCallByEmployeeMstList(EmployeeMst employeeMst);

    ForwardedCall findForwardedCallByCallIdAndEmpId(CallMst callmst);

    List<ForwardedCall> findForwardedCallByCallId(CallMst callmst);

    List<ForwardedCall> findForwardedCallByCallIdAndEmpId(CallMst callmst, EmployeeMst employeeMst);

    List<ForwardedCall> findForwardedCallByCallDtlId(Long calldtlId);

}
