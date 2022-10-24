package com.rank.ccms.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rank.ccms.dto.CustomerRMMapDto;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.CustomerRmMap;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.CustomerRMMapService;
import com.rank.ccms.service.EmployeeMstService;

@Component
@Scope("session")
public class CustomerRMComponent implements Serializable {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    private Long rmId;
    private Long custId;
    private String rmName;
    private String customerName;

    private SortedMap<String, Long> customerMap = new TreeMap<>();
    private DualListModel<CustomerRMMapDto> customers = new DualListModel<>();
    private List<CustomerRMMapDto> source;
    private List<CustomerRMMapDto> target;

    List<CustomerRMMapDto> customerRMMapDtoList = new ArrayList<>();

    CustomerRMMapDto selectedcustomerRMMapDto;

    @Autowired
    EmployeeMstService employeeMstService;
    @Autowired
    CustomerRMMapService customerRMMapService;
    @Autowired
    CustomerMstService customerMstService;

    public CustomerRMComponent() {

    }

    public String onLoad() {
        logger.info("inside onLoad........");
        refresh();
        return "/pages/maps/customerRMMap.xhtml?faces-redirect=true";
    }

    public void refresh() {
        try {

            if (customerRMMapDtoList != null) {
                customerRMMapDtoList.clear();
            }

            selectedcustomerRMMapDto = null;
            EmployeeTypeMst employeeTypeMst = new EmployeeTypeMst();
            employeeTypeMst.setId(7l);
            List<EmployeeMst> employeeList = employeeMstService.findNonDeletedEmployeeByEmpType("RelationshipManager");
            for (EmployeeMst employeeList1 : employeeList) {
                CustomerRMMapDto custrmdto = new CustomerRMMapDto();
                custrmdto.setRmId(employeeList1.getId());
                custrmdto.setRmName(employeeList1.getFirstName() + employeeList1.getMidName() + employeeList1.getLastName());
                List<CustomerRmMap> customerRmMapList = customerRMMapService.getCustomersMappedWithEmployee(employeeList1.getId());
                String mappedCustomers = "";
                for (CustomerRmMap custRMMap : customerRmMapList) {
                    mappedCustomers = mappedCustomers + customerMstService.findCustomerMstById(custRMMap.getCustId().getId()).getFirstName() + ", ";
                }
                if (mappedCustomers.trim().length() > 0) {

                    mappedCustomers = mappedCustomers.substring(0, mappedCustomers.length() - 2);

                }
                custrmdto.setMappedCustomers(mappedCustomers);
                customerRMMapDtoList.add(custrmdto);
            }
            logger.info("customerRMMapDtoList size : " + customerRMMapDtoList.size());

        } catch (Exception e) {
            logger.error("refresh ERROR ", e);
        }
    }

    public void onBeforeEditTypeMapList(Long employeeId) {
        source = new ArrayList<>();
        target = new ArrayList<>();
        customers = new DualListModel<>(source, target);
        this.setCustomerMap(getCustomerList());
        populatePickList(employeeId);
        try {
            EmployeeMst emp = employeeMstService.findEmployeeMstById(employeeId);
            this.setRmId(emp.getId());
            this.setRmName(emp.getFirstName() + emp.getMidName() + emp.getLastName());

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CustomerRMComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestContext.getCurrentInstance().execute("PF('editCustomerRMMapDialogVar').show();");

    }

    public void populatePickList(Long employeeId) {

        source = new ArrayList<>();
        target = new ArrayList<>();

        if (employeeId != null && employeeId > 0) {

            try {
                //Get All the Roles
                for (CustomerMst cm : customerMstService.findAllRegisteredCustomerMasters()) {
                    CustomerRMMapDto customerRMMapDto = new CustomerRMMapDto();
                    List<CustomerRmMap> mappedList = customerRMMapService.findRMCustomerMapByRMandCustomer(employeeId, cm.getId());
                    if (mappedList.size() > 0) {
                        customerRMMapDto.setId(mappedList.get(0).getId());
                        customerRMMapDto.setRmId(employeeId);
                        customerRMMapDto.setCustId(cm.getId());
                        customerRMMapDto.setCustomerName(cm.getFirstName());
                        getTarget().add(customerRMMapDto);
                    } else {
                        List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cm.getId());
                        CustomerRmMap mappedRM = null;
                        if (!mappedRMList.isEmpty()) {
                            mappedRM = mappedRMList.get(0);
                        }
                        if (mappedRM == null) {
                            customerRMMapDto.setRmId(employeeId);
                            customerRMMapDto.setCustId(cm.getId());
                            customerRMMapDto.setCustomerName(cm.getFirstName());
                            getSource().add(customerRMMapDto);
                        }

                    }

                }

            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(CustomerRMComponent.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        customers = new DualListModel<>(source, target);

    }

    private TreeMap<String, Long> getCustomerList() {
        TreeMap<String, Long> allCustomers = new TreeMap();

        EmployeeTypeMst employeeTypeMst = new EmployeeTypeMst();
        employeeTypeMst.setId(7l);
        List<EmployeeMst> empMstList = new ArrayList<>();
        try {
            empMstList = employeeMstService.findNonDeletedEmployeeByEmpType("RelationshipManager");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CustomerRMComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (EmployeeMst empMst : empMstList) {
            allCustomers.put(empMst.getFirstName() + empMst.getMidName() + empMst.getLastName(), empMst.getId());

        }
        return allCustomers;
    }

    public void save() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            //First Delete All Roles of Employee Type From Source
            for (CustomerRMMapDto customerRMMapDto : customers.getSource()) {
                if (customerRMMapDto.getId() != null) {

                    if (customerRMMapDto.getRmId().equals(this.rmId)) {

                        List<CustomerRmMap> customerRmMapMapList = customerRMMapService.findRMCustomerMapByRMandCustomer(customerRMMapDto.getRmId(), customerRMMapDto.getCustId());
                        for (CustomerRmMap customerRmMap : customerRmMapMapList) {
                            customerRMMapService.deleteCustomerRmMap(customerRmMap);
                        }
                    }

                }
            }

            //Sava Data 
            for (CustomerRMMapDto customerRMMapDto : customers.getTarget()) {

                CustomerRmMap customerRmMap = geEntityFromDto(customerRMMapDto);
                customerRmMap.setActiveFlg(true);
                customerRmMap.setDeleteFlg(false);

                if (customerRMMapDto.getRmId().equals(this.rmId)) {

                    customerRMMapService.saveRMCustomerMap(customerRmMap);
                }

            }

            refresh();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mapped Successfully", "Mapped Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Mapping Failure");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    private CustomerRmMap geEntityFromDto(CustomerRMMapDto customerRMMapDto) {
        CustomerRmMap customerRmMap = new CustomerRmMap();

        customerRmMap.setId(customerRMMapDto.getId());
        try {
            customerRmMap.setRmId(employeeMstService.findEmployeeMstById(customerRMMapDto.getRmId()));

            customerRmMap.setCustId(customerMstService.findCustomerMstById(customerRMMapDto.getCustId()));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CustomerRMComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customerRmMap;
    }

    public Long getRmId() {
        return rmId;
    }

    public void setRmId(Long rmId) {
        this.rmId = rmId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public DualListModel<CustomerRMMapDto> getCustomers() {
        return customers;
    }

    public void setCustomers(DualListModel<CustomerRMMapDto> customers) {
        this.customers = customers;
    }

    public List<CustomerRMMapDto> getSource() {
        return source;
    }

    public void setSource(List<CustomerRMMapDto> source) {
        this.source = source;
    }

    public List<CustomerRMMapDto> getTarget() {
        return target;
    }

    public void setTarget(List<CustomerRMMapDto> target) {
        this.target = target;
    }

    public List<CustomerRMMapDto> getCustomerRMMapDtoList() {
        return customerRMMapDtoList;
    }

    public void setCustomerRMMapDtoList(List<CustomerRMMapDto> customerRMMapDtoList) {
        this.customerRMMapDtoList = customerRMMapDtoList;
    }

    public CustomerRMMapDto getSelectedcustomerRMMapDto() {
        return selectedcustomerRMMapDto;
    }

    public void setSelectedcustomerRMMapDto(CustomerRMMapDto selectedcustomerRMMapDto) {
        this.selectedcustomerRMMapDto = selectedcustomerRMMapDto;
    }

    public SortedMap<String, Long> getCustomerMap() {
        return customerMap;
    }

    public void setCustomerMap(SortedMap<String, Long> customerMap) {
        this.customerMap = customerMap;
    }

}
