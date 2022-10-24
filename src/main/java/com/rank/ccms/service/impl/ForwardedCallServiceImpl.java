package com.rank.ccms.service.impl;

import com.rank.ccms.dao.ForwardedCallDao;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.service.ForwardedCallService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("forwardedCallService")
public class ForwardedCallServiceImpl implements ForwardedCallService {

    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private ForwardedCallDao forwardedCallDao;

    @Override
    public ForwardedCall saveForwardedCalls(ForwardedCall forwardedCall, EmployeeMst employeeMst) {
        if (forwardedCall.getId() == null) {

            forwardedCall = forwardedCallDao.saveRow(forwardedCall);

        } else {
            logger.info("Forward call updating starts");
            ForwardedCall existingForwardedCall = forwardedCallDao.findById(forwardedCall.getId());
            if (existingForwardedCall != null) {
                if (existingForwardedCall != forwardedCall) {
                    existingForwardedCall.setDeleteFlg(forwardedCall.getDeleteFlg());
                    existingForwardedCall.setActiveFlg(forwardedCall.getActiveFlg());
                    existingForwardedCall.setForwardStatus(forwardedCall.getForwardStatus());
                    existingForwardedCall.setCallDtlId(forwardedCall.getCallDtlId());
                    existingForwardedCall.setRoomName(forwardedCall.getRoomName());
                    existingForwardedCall.setEntityId(forwardedCall.getEntityId());
                    existingForwardedCall.setRoomLink(forwardedCall.getRoomLink());
                    existingForwardedCall.setForwardType(forwardedCall.getForwardType());
                    existingForwardedCall.setEmpId(forwardedCall.getEmpId());
                    existingForwardedCall.setCallPickupTime(forwardedCall.getCallPickupTime());
                    existingForwardedCall.setCallId(forwardedCall.getCallId());
                    existingForwardedCall.setPrevEmpId(forwardedCall.getPrevEmpId());
                    existingForwardedCall.setSelectedServiceId(forwardedCall.getSelectedServiceId());
                    existingForwardedCall.setFromServiceId(forwardedCall.getFromServiceId());
                }
                try {
                    forwardedCall = forwardedCallDao.mergeRow(existingForwardedCall);

                } catch (Exception e) {
                    logger.info("Error:" + e.getMessage());
                }

            } else {

                forwardedCall = forwardedCallDao.saveRow(forwardedCall);

            }
        }
        logger.info("forwardedCall saved succesfully");
        return forwardedCall;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ForwardedCall> findAllForwardedCalls() {
        return new ArrayList<>(forwardedCallDao.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ForwardedCall> findAllNonDeletedForwardedCalls() {
        return new ArrayList<>(forwardedCallDao.findAllNonDeleted());
    }

    @Transactional(readOnly = true)
    @Override
    public ForwardedCall findForwardedCallById(Integer id) {
        return forwardedCallDao.findByIdAll(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ForwardedCall findNonDeletedForwardedCallById(Long id) {
        return forwardedCallDao.findNonDeletedById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ForwardedCall> findAllActivenNonDeletedForwardedCalls() {
        return forwardedCallDao.findAllActivenNonDeletedForwardedCalls();
    }

    @Override
    public ForwardedCall findActiveForwardedCallByEmployeeMst(EmployeeMst employeeMst) {
        List<ForwardedCall> forwardeCallList = forwardedCallDao.findActiveForwardedCallByEmployeeMst(employeeMst);
        if (null == forwardeCallList || forwardeCallList.isEmpty()) {
            return null;
        } else {
            return (ForwardedCall) forwardeCallList.get(0);
        }
    }

    @Override
    public List<ForwardedCall> findActiveForwardedCallByEmployeeMstList(EmployeeMst employeeMst) {
        return forwardedCallDao.findActiveForwardedCallByEmployeeMst(employeeMst);
    }

    @Override
    public ForwardedCall findForwardedCallByCallIdAndEmpId(CallMst callmst) {
        return forwardedCallDao.findForwardedCallByCallIdAndEmpId(callmst);
    }

    @Override
    public List<ForwardedCall> findForwardedCallByCallId(CallMst callmst) {
        return forwardedCallDao.findForwardedCallByCallId(callmst);
    }

    @Override
    public List<ForwardedCall> findForwardedCallByCallIdAndEmpId(CallMst callmst, EmployeeMst employeeMst) {
        return forwardedCallDao.findForwardedCallByCallIdAndEmpId(callmst, employeeMst);
    }

    @Override
    public List<ForwardedCall> findForwardedCallByCallDtlId(Long calldtlId) {
        return forwardedCallDao.findForwardedCallByCallDtlId(calldtlId);
    }

}
