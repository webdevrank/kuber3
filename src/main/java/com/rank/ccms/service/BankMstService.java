package com.rank.ccms.service;

import com.rank.ccms.entities.BankMst;
import java.io.Serializable;
import java.util.List;

public interface BankMstService extends Serializable {

    List<BankMst> findAllNonDeleted();
}
