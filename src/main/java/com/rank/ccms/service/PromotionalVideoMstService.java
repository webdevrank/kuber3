package com.rank.ccms.service;

import com.rank.ccms.entities.PromotionalVideoMst;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public interface PromotionalVideoMstService extends Serializable {

    PromotionalVideoMst findByCaptionName(String caption);

    PromotionalVideoMst findByVideoFileName(String fileName);

    PromotionalVideoMst save(PromotionalVideoMst promotionalVideoMst);

    PromotionalVideoMst findPromotionalVideoMstById(Long id);

    List<PromotionalVideoMst> findActivePromotionalVideo();

    void deletePromotionalVideoMst(PromotionalVideoMst promotionalVideoMst);

    PromotionalVideoMst findSelectedPromotionalVideo();

    String getVideoFileUrl(String filePath, HttpServletRequest request, ServletContext ctx);
}
