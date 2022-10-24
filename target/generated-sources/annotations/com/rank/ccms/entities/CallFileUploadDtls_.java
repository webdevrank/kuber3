package com.rank.ccms.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CallFileUploadDtls.class)
public abstract class CallFileUploadDtls_ {

	public static volatile SingularAttribute<CallFileUploadDtls, String> fileSentbyType;
	public static volatile SingularAttribute<CallFileUploadDtls, String> fileReceivedbyType;
	public static volatile SingularAttribute<CallFileUploadDtls, CallMst> callMstId;
	public static volatile SingularAttribute<CallFileUploadDtls, Long> fileSentBy;
	public static volatile SingularAttribute<CallFileUploadDtls, Long> createdBy;
	public static volatile SingularAttribute<CallFileUploadDtls, String> filePath;
	public static volatile SingularAttribute<CallFileUploadDtls, Long> fileReceivedBy;
	public static volatile SingularAttribute<CallFileUploadDtls, Date> createdDateTime;
	public static volatile SingularAttribute<CallFileUploadDtls, String> docTitle;
	public static volatile SingularAttribute<CallFileUploadDtls, Long> id;

}

