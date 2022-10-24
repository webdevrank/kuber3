package com.rank.ccms.util;

public class SqlConstant {

    public static final String CUSTOMER_ACCOUNT_DETAIL_LIST = "select * from("
            + "select customer_mst.first_name,customer_mst.national_id as national_id_no ,"
            + "customer_dtl.dob, customer_dtl.nationality,customer_mst.id as cust_id, "
            + "customer_dtl.gender, customer_dtl.maritail_status, customer_dtl.email, "
            + "customer_dtl.phone_no, customer_dtl.address as cus_address, customer_dtl.occupation, "
            + "customer_dtl.salary, customer_dtl.education,bank_mst.bank_name, customer_dtl.customer_type, "
            + "bank_mst.branch_code, bank_mst.address as bnk_address, bank_mst.ifsc_code,customer_acc_dtl.acc_no, "
            + "customer_acc_dtl.balance_amt, customer_acc_dtl.effective_date,"
            + "customer_dtl.customer_image,customer_dtl.customer_sign,customer_dtl.utility_bill,customer_dtl.national_id,customer_mst.account_no, "
            + "row_number() OVER(partition by customer_acc_dtl.acc_no "
            + "order by customer_acc_dtl.effective_date DESC) as rn from "
            + "customer_mst INNER JOIN customer_dtl ON customer_mst.cell_phone=cast(customer_dtl.phone_no as double precision)"
            + "INNER JOIN bank_mst on bank_mst.id=customer_dtl.bank_mst_id "
            + "INNER JOIN customer_acc_dtl on customer_acc_dtl.customer_dtl_id=customer_dtl.id "
            + "where customer_sign is not null) "
            + "tt where rn =1 order by effective_date desc";
}
