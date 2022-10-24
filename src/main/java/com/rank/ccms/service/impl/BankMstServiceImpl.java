package com.rank.ccms.service.impl;

import com.rank.ccms.dao.BankMstDao;
import com.rank.ccms.entities.BankMst;
import com.rank.ccms.service.BankMstService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bankMstService")
public class BankMstServiceImpl implements BankMstService {

    @Autowired
    private BankMstDao bankMstDao;

    @Override
    public List<BankMst> findAllNonDeleted() {
        return bankMstDao.findAll();
    }

}
