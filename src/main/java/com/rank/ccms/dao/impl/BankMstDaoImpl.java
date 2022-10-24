package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.BankMstDao;
import com.rank.ccms.entities.BankMst;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("bankMstDao")
@Transactional
public class BankMstDaoImpl extends GenericDaoImpl<BankMst> implements BankMstDao {

}
