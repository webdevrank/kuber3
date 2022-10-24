package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerDeviceDtl.class)
public abstract class CustomerDeviceDtl_ {

	public static volatile SingularAttribute<CustomerDeviceDtl, String> deviceIp;
	public static volatile SingularAttribute<CustomerDeviceDtl, String> staticIp;
	public static volatile SingularAttribute<CustomerDeviceDtl, String> timezone;
	public static volatile SingularAttribute<CustomerDeviceDtl, Integer> otpVerified;
	public static volatile SingularAttribute<CustomerDeviceDtl, String> deviceId;
	public static volatile SingularAttribute<CustomerDeviceDtl, String> customerOtp;
	public static volatile SingularAttribute<CustomerDeviceDtl, Integer> audioVideo;
	public static volatile SingularAttribute<CustomerDeviceDtl, Integer> logout;
	public static volatile SingularAttribute<CustomerDeviceDtl, String> imeiNo;
	public static volatile SingularAttribute<CustomerDeviceDtl, Integer> hashCode;
	public static volatile SingularAttribute<CustomerDeviceDtl, Integer> loginInfo;
	public static volatile SingularAttribute<CustomerDeviceDtl, CustomerMst> customerId;
	public static volatile SingularAttribute<CustomerDeviceDtl, Long> id;
	public static volatile SingularAttribute<CustomerDeviceDtl, String> mobileOsType;
	public static volatile SingularAttribute<CustomerDeviceDtl, String> deviceBrand;

}

