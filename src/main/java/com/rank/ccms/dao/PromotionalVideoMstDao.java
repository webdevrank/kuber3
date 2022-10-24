package com.rank.ccms.dao;

import com.rank.ccms.entities.PromotionalVideoMst;

public interface PromotionalVideoMstDao extends GenericDao<PromotionalVideoMst> {

    public PromotionalVideoMst findSelectedPromotionalVideo();

    public PromotionalVideoMst findByCaptionName(String caption);

    public PromotionalVideoMst findByVideoFileName(String fileName);

}
