package com.rank.ccms.service.impl;

import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.AgentFindingService;
import com.rank.ccms.service.EmployeeCallProficiencyService;
import com.rank.ccms.service.EmployeeCallStatusService;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rank.ccms.config.ccmstablerelation.xmlconfig.CCMSCallRoutingConfigUtil;
import org.apache.log4j.Logger;

@Service("agentFindingService")
public class AgentFindingServiceImpl implements AgentFindingService {

    Logger logger = Logger.getLogger(this.getClass());

    private String statusMessage;

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Autowired
    private EmployeeCallProficiencyService employeeCallProficiencyService;
    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;
    @Autowired
    private CCMSCallRoutingConfigUtil ccmsCallRoutingConfigUtil;

    @Override
    public Boolean isCcmsDown() {
        /*
        List<CcmsDownInfo> ccmsDnLst = ccmsDownInfoService.findAllNonDeletedCcmsDownInfo();
        Boolean retVal = false;

        for (int i = 0; i < ccmsDnLst.size(); i++) {
            short wkly = ccmsDnLst.get(i).getWeekly();
            if (wkly == 1) {
                if (dayCompare(ccmsDnLst.get(i).getDayOfTheWeek()) && timeCompare(ccmsDnLst.get(i).getStartTime(), ccmsDnLst.get(i).getDuration())) {
                    retVal = true;
                    break;
                }
            }
            short mntly = ccmsDnLst.get(i).getMonthly();
            if (!retVal) {
                if (mntly == 1) {
                    if (monCompare(ccmsDnLst.get(i).getDayOfMonth()) && timeCompare(ccmsDnLst.get(i).getStartTime(), ccmsDnLst.get(i).getDuration())) {
                        retVal = true;
                        break;
                    }
                }
            }
        }
         */
        return false;
    }

    @Override
    public synchronized Set<EmployeeMst> findAgents(String param1, String param2, String param3) {

        logger.info("=================findAgents called================");
        HashMap<String, String> agentOtherSkills;
        agentOtherSkills = ccmsCallRoutingConfigUtil.getCallRoutingConfigMap();

        Iterator iter = agentOtherSkills.keySet().iterator();
        String[] sequenceVals = new String[10];
        int i = 0;
        while (iter.hasNext()) {
            String key = (String) iter.next();
            sequenceVals[i] = agentOtherSkills.get(key);
            logger.info("pairs.getKey() = " + sequenceVals[i]);
            i++;
        }

        String lang = param3;
        /*
         List<String> lst = Arrays.asList(param3.split(";"));
         String lang = lst.get(0);
         List<String> sklLst = new ArrayList<String>();
         logger.info("====Size of lst ==== " + lst.size());
         for (int j = 0; j < lst.size() - 1; j++) {
         sklLst.add(j, lst.get(j + 1));
         logger.info("====Skill from sklLst ===== " + sklLst.get(j));
         }
         List listOfNames = new ArrayList();
         listOfNames.addAll(sklLst);
         */

        Set<EmployeeMst> agentSet = new HashSet<>();
        if (isCcmsDown()) {
            logger.info("Agent Routing is stopped due to system down time.");

        } else {
            if (sequenceVals[0].equals("Segment") && sequenceVals[1].equals("Service") && sequenceVals[2].equals("Language")) {
                agentSet = this.findAgentsSegmentLanguageService(param1, param2, lang); //===Done
            }
            if (sequenceVals[0].equals("Segment") && sequenceVals[1].equals("Language") && sequenceVals[2].equals("Service")) {
                agentSet = this.findAgentsSegmentServiceLanguage(param1, lang, param2); //===Done
            }
            if (sequenceVals[0].equals("Service") && sequenceVals[1].equals("Segment") && sequenceVals[2].equals("Language")) {
                agentSet = this.findAgentsServiceLanguageSegment(param2, param1, lang); //===Done
            }
            if (sequenceVals[0].equals("Service") && sequenceVals[1].equals("Language") && sequenceVals[2].equals("Segment")) {
                agentSet = this.findAgentsServiceSegmentLanguage(param2, lang, param1); //===Done
            }
            if (sequenceVals[0].equals("Language") && sequenceVals[1].equals("Segment") && sequenceVals[2].equals("Service")) {
                agentSet = this.findAgentsLanguageServiceSegment(lang, param1, param2); //===Done
            }
            if (sequenceVals[0].equals("Language") && sequenceVals[1].equals("Service") && sequenceVals[2].equals("Segment")) {
                agentSet = this.findAgentsLanguageSegmentService(lang, param2, param1); //===Done
            }
        }

        return agentSet;

    }

    public Set<EmployeeMst> findAgentsSegmentLanguageService(String segment, String service, String language) {

        logger.info("=================findAgentsSegmentLanguageService called================");
        logger.info("====segment:" + segment + "======service:" + service + "=====language:" + language);

        Set<EmployeeMst> employeelist = new HashSet<>();

        List<EmployeeCallStatus> onlineEmployeelist = employeeCallStatusService.findFreeOnlineAgents();
        employeelist.clear();
        for (EmployeeCallStatus onlineEmployeelist1 : onlineEmployeelist) {
            employeelist.add(onlineEmployeelist1.getEmpId());
        }

        if (employeelist.isEmpty()) {
            statusMessage = "No agent is found";
            logger.info("No agent is found");
        } else {
            logger.debug("Free agent found " + employeelist.size());
            logger.info("=====employeelist size ==========" + employeelist.size());
            List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);
            logger.info("======employeeCallProficiencyList size======" + employeeCallProficiencyList.size());
            if (employeeCallProficiencyList.isEmpty()) {
                //Agents with primary segment not found seaching for secondary segment
                logger.debug("Agents with primary segment not found seaching for secondary segment");
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with secondary segment not found
                    logger.debug("Agents with secondary segment not found");
                    this.statusMessage = "Agent not found";
                    employeelist.clear();
                } else {
                    //Agents with secondary segment found
                    logger.debug("Agents with secondary segment found");
                    employeelist.clear();

                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }

                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with secondary segment and primary language not found seaching for secondary language
                        logger.debug("Agents with secondary segment and primary language not found seaching for secondary language");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary segment and secondary language not found 
                            logger.debug("Agents with secondary segment and secondary language not found ");
                            this.statusMessage = "Agent not found";
                            employeelist.clear();
                        } else {

                            //Agents with secondary segment and secondary language found
                            logger.debug("Agents with secondary segment and secondary language found");
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary segment and secondary language and primary service not found searching for secondary service
                                logger.debug("Agents with secondary segment and secondary language and primary service not found searching for secondary service");
                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with secondary segment and secondary language and secondary service not found 
                                    logger.debug("Agents with secondary segment and secondary language and secondary service not found ");
                                    this.statusMessage = "No agent found";
                                    employeelist.clear();
                                } else {
                                    //Agents with secondary segment and secondary language and secondary service found 
                                    logger.debug("Agents with secondary segment and secondary language and secondary service found ");
                                    this.statusMessage = "Agents with secondary segment and secondary language and secondary service found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                    }

                                }

                            } else {
                                //Agents with secondary segment and secondary language and primary service found 
                                logger.debug("Agents with secondary segment and secondary language and primary service found ");
                                this.statusMessage = "Agents with secondary segment and secondary language and primary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        }

                    } else {
                        //Agents with secondary segment and primary language found
                        logger.debug("Agents with secondary segment and primary language found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary segment and primary language and primary service not found seaching for secondary service
                            logger.debug("Agents with secondary segment and primary language and primary service not found seaching for secondary service");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary segment and primary language and  secondary service not found
                                logger.debug("Agents with secondary segment and primary language and  secondary service not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with secondary segment and primary language and  secondary service found
                                logger.debug("Agents with secondary segment and primary language and  secondary service found");
                                this.statusMessage = "Agents with secondary segment and primary language and  secondary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        } else {
                            //Agents with secondary segment and primary language and primary service found
                            logger.debug("Agents with secondary segment and primary language and primary service found");
                            this.statusMessage = "Agents with secondary segment and primary language and primary service found";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                }

            } else {
                //Agents with primary segment found
                logger.debug("Agents with primary segment found " + employeeCallProficiencyList.size());
                employeelist.clear();
                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                }
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with primary segment and primary language not found seaching for secondary language
                    logger.debug("Agents with primary segment and primary language not found seaching for secondary language");
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary segment and secondary language not found
                        logger.debug("Agents with primary segment and secondary language not found");
                        this.statusMessage = "No agent found";
                        employeelist.clear();
                    } else {
                        //Agents with primary segment and secondary language found
                        logger.debug("Agents with primary segment and secondary language found");
                        logger.info("Agents with primary segment and secondary language found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary segment and secondary language and primary service not found searching for secodary service
                            logger.debug("Agents with primary segment and secondary language and primary service not found searching for secodary service");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with primary segment and secondary language and secondary service not found
                                logger.debug("Agents with primary segment and secondary language and secodary service not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with primary segment and secondary language and secodary service found
                                logger.debug("Agents with primary segment and secondary language and secodary service found");
                                this.statusMessage = "Agents with primary segment and secondary language and secodary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }
                        } else {
                            //Agents with primary segment and secondary language and primary service found 
                            logger.debug("Agents with primary segment and secondary language and primary service found ");
                            logger.info("Agents with primary segment and secondary language and primary service found");
                            this.statusMessage = "Agents with primary segment and secondary language and primary service found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                } else {
                    //Agents with primary segment and primary language found
                    logger.debug("Agents with primary segment and primary language found");
                    employeelist.clear();
                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary segment and primary language and primary service not found searching for secondary service 
                        logger.debug("Agents with primary segment and primary language and primary service not found searching for secondary service ");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary segment and primary language and secondary service not found 
                            logger.debug("Agents with primary segment and primary language and secondary service not found ");
                            this.statusMessage = "No agent found";
                            employeelist.clear();
                        } else {
                            //Agents with primary segment and primary language and secondary service found 
                            logger.debug("Agents with primary segment and primary language and secondary service found ");
                            this.statusMessage = "Agents with primary segment and primary language and secondary service found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }

                    } else {
                        //Agents with primary segment and primary language and primary service found 
                        logger.debug("Agents with primary segment and primary language and primary service found ");
                        this.statusMessage = "Agents with primary segment and primary language and primary service found ";
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }

                    }
                }
            }
        }
        logger.info("agent size===>" + employeelist.size());
        return employeelist;
    }

    public Set<EmployeeMst> findAgentsSegmentServiceLanguage(String segment, String language, String service) {
        {
            logger.debug("====segment:" + segment + "======service:" + service + "=====language:" + language);
            logger.info("========findAgentsSegmentServiceLanguage called ============");
            //HashMap<String, String> agentSerchSequenceProps = LoadCallRoutingProperties.agentSerchSequenceProperties;

            // List<EmployeeMst> employeelist = new ArrayList<>();
            Set<EmployeeMst> employeelist = new HashSet<>();

            List<EmployeeCallStatus> onlineEmployeelist = employeeCallStatusService.findFreeOnlineAgents();
            logger.info("===onlineEmployeelist size====" + onlineEmployeelist.size());
            employeelist.clear();
            for (int i = 0; i < onlineEmployeelist.size(); i++) {
                employeelist.add(onlineEmployeelist.get(i).getEmpId());
                logger.info("====On line employee name =====" + i + " : " + onlineEmployeelist.get(i).getEmpId().getFirstName());
            }

            if (employeelist.isEmpty()) {
                statusMessage = "No agent is found";
                logger.info("No agent is found");
            } else {
                logger.debug("Free agent found " + employeelist.size());
                logger.info("=======employeelist size=====" + employeelist.size());
                logger.info("Segment Value = " + segment);
                List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);
                logger.info("===employeeCallProficiencyList size===" + employeeCallProficiencyList.size());

                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with primary segment not found seaching for secondary segment
                    logger.debug("Agents with primary segment not found seaching for secondary segment");
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with secondary segment not found
                        logger.debug("Agents with secondary segment not found");
                        this.statusMessage = "Agent not found";
                        employeelist.clear();
                    } else {
                        //Agents with secondary segment found
                        logger.debug("======Agents with secondary segment found=====");
                        logger.info("Agents with secondary segment found");
                        employeelist.clear();

                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                            logger.info("===employeeCallProficiencyList Name ===  " + employeeCallProficiencyList1.getEmpId());
                        }

                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary segment and primary service not found seaching for secondary service
                            logger.debug("Agents with secondary segment and primary service not found seaching for secondary srvice");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary segment and secondary service not found 
                                logger.debug("Agents with secondary segment and secondary service not found ");
                                this.statusMessage = "Agent not found";
                                employeelist.clear();
                            } else {
                                //Agents with secondary segment and secondary service found
                                logger.debug("Agents with secondary segment and secondary service found");
                                logger.info("Agents with secondary segment and secondary service found");
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with secondary segment and secondary service and primary language not found searching for secondary language
                                    logger.debug("Agents with secondary segment and secondary service and primary language not found searching for secondary language");

                                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                                    if (employeeCallProficiencyList.isEmpty()) {
                                        //Agents with secondary segment and secondary service and secondary language not found 
                                        logger.debug("Agents with secondary segment and secondary language and secondary service not found ");
                                        this.statusMessage = "No agent found";
                                        employeelist.clear();
                                    } else {
                                        //Agents with secondary segment and secondary language and secondary service found 
                                        logger.debug("Agents with secondary segment and secondary language and secondary service found ");
                                        logger.info("Agents with secondary segment and secondary language and secondary service found");
                                        this.statusMessage = "Agents with secondary segment and secondary language and secondary service found";
                                        employeelist.clear();
                                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                                        }

                                    }

                                } else {
                                    //Agents with secondary segment and secondary service and primary language found 
                                    logger.debug("Agents with secondary segment and secondary service and primary language found ");
                                    logger.info("Agents with secondary segment and secondary service and primary language found");
                                    this.statusMessage = "Agents with secondary segment and secondary language and primary service found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                        logger.info("Agents with secondary segment and secondary language and primary service found emp id : " + employeeCallProficiencyList1.getEmpId());
                                    }

                                }

                            }

                        } else {
                            //Agents with secondary segment and primary service found
                            logger.debug("Agents with secondary segment and primary service found");
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary segment and primary language and primary service not found seaching for secondary language
                                logger.debug("Agents with secondary segment and primary language and primary service not found seaching for secondary language");
                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with secondary segment and primary service and  secondary language not found
                                    logger.debug("Agents with secondary segment and primary service and  secondary language not found");
                                    this.statusMessage = "No agent found";
                                    employeelist.clear();
                                } else {
                                    //Agents with secondary segment and primary service and  secondary language found
                                    logger.debug("Agents with secondary segment and primary service and  secondary language found");
                                    this.statusMessage = "Agents with secondary segment and primary service and  secondary language found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                    }

                                }

                            } else {
                                //Agents with secondary segment and primary language and primary service found
                                logger.debug("Agents with secondary segment and primary language and primary service found");
                                this.statusMessage = "Agents with secondary segment and primary language and primary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }
                        }

                    }

                } else {
                    //Agents with primary segment found
                    logger.debug("Agents with primary segment found " + employeeCallProficiencyList.size());
                    logger.info("Agents with primary segment found");
                    employeelist.clear();
                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary segment and primary service not found searching for secondary service
                        logger.debug("Agents with primary segment and primary service not found seaching for secondary service");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary segment and secondary service not found
                            logger.debug("Agents with primary segment and secondary language not found");
                            this.statusMessage = "No agent found";
                            employeelist.clear();
                        } else {
                            //Agents with primary segment and secondary service found
                            logger.debug("=======Agents with primary segment and secondary service found");
                            logger.info("Agents with primary segment and secondary service found");
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with primary segment and secondary service and primary language not found searching for secodary language
                                logger.debug("Agents with primary segment and secondary service and primary language not found searching for secodary language");
                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with primary segment and secondary service and secondary language not found
                                    logger.debug("Agents with primary segment and secondary service and secondary language not found");
                                    this.statusMessage = "No agent found";
                                    employeelist.clear();
                                } else {
                                    //Agents with primary segment and secondary service and secodary language found
                                    logger.debug("Agents with primary segment and secondary service and secodary language found");
                                    logger.info("Agents with primary segment and secondary service and secodary language found");
                                    this.statusMessage = "Agents with primary segment and secondary service and secodary language found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                    }

                                }
                            } else {
                                //Agents with primary segment and secondary service and primary language found 
                                logger.debug("Agents with primary segment and secondary service and primary language found");
                                logger.info("Agents with primary segment and secondary service and primary language found");
                                this.statusMessage = "Agents with primary segment and secondary service and primary language found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }
                        }

                    } else {
                        //Agents with primary segment and primary service found
                        logger.debug("Agents with primary segment and primary service found");
                        logger.info("Agents with primary segment and primary service found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                            logger.info("====Agents with primary segment and primary service found====" + employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary segment and primary language and primary service not found searching for secondary service 
                            logger.debug("Agents with primary segment and primary language and primary service not found searching for secondary language ");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with primary segment and primary service and secondary language not found 
                                logger.debug("Agents with primary segment and primary service and secondary language not found ");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with primary segment and primary service and secondary language found 
                                logger.debug("Agents with primary segment and primary service and secondary language found ");
                                logger.info("===========Agents with primary segment and primary service and secondary language found=========");
                                this.statusMessage = "Agents with primary segment and primary language and secondary service found ";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        } else {
                            //Agents with primary segment and primary language and primary service found 
                            logger.debug("Agents with primary segment and primary language and primary service found ");
                            logger.info("Agents with primary segment and primary language and primary service found");
                            this.statusMessage = "Agents with primary segment and primary language and primary service found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }
                }
            }
            logger.info("agent size===>" + employeelist.size());

            return employeelist;
        }
    }

    public Set<EmployeeMst> findAgentsServiceLanguageSegment(String service, String segment, String language) {
        logger.debug("====segment:" + segment + "======service:" + service + "=====language:" + language);
        logger.info("=================findAgentsServiceLanguageSegment called====================");
        //HashMap<String, String> agentSerchSequenceProps = LoadCallRoutingProperties.agentSerchSequenceProperties;

        // List<EmployeeMst> employeelist = new ArrayList<>();
        Set<EmployeeMst> employeelist = new HashSet<>();

        List<EmployeeCallStatus> onlineEmployeelist = employeeCallStatusService.findFreeOnlineAgents();
        employeelist.clear();
        for (EmployeeCallStatus onlineEmployeelist1 : onlineEmployeelist) {
            employeelist.add(onlineEmployeelist1.getEmpId());
        }

        if (employeelist.isEmpty()) {
            statusMessage = "No agent is found";
            logger.info("No agent is found");
        } else {
            logger.debug("Free agent found " + employeelist.size());
            List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

            if (employeeCallProficiencyList.isEmpty()) {
                //Agents with primary service not found seaching for secondary service
                logger.debug("Agents with primary service not found seaching for secondary service");
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with secondary service not found
                    logger.debug("Agents with secondary segment not found");
                    this.statusMessage = "Agent not found";
                    employeelist.clear();
                } else {
                    //Agents with secondary service found
                    logger.debug("Agents with secondary segment found");
                    employeelist.clear();

                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }

                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with secondary service and primary language not found seaching for secondary language
                        logger.debug("Agents with secondary service and primary language not found seaching for secondary language");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary service and secondary language not found 
                            logger.debug("Agents with secondary service and secondary language not found ");
                            this.statusMessage = "Agent not found";
                            employeelist.clear();
                        } else {

                            //Agents with secondary service and secondary language found
                            logger.debug("Agents with secondary service and secondary language found");
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);

                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary service and secondary language and primary segment not found searching for secondary segment
                                logger.debug("Agents with secondary service and secondary language and primary segment not found searching for secondary segment");
                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with secondary service and secondary language and secondary segment not found 
                                    logger.debug("Agents with secondary segment and secondary language and secondary service not found ");
                                    this.statusMessage = "No agent found";
                                    employeelist.clear();
                                } else {
                                    //Agents with secondary service and secondary language and secondary segment found 
                                    logger.debug("Agents with secondary service and secondary language and secondary segment found");
                                    this.statusMessage = "Agents with secondary service and secondary language and secondary segment found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                    }

                                }

                            } else {
                                //Agents with secondary service and secondary language and primary segment found 
                                logger.debug("Agents with secondary service and secondary language and primary segment found");
                                this.statusMessage = "Agents with secondary service and secondary language and primary segment found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        }

                    } else {
                        //Agents with secondary service and primary language found
                        logger.debug("Agents with secondary service and primary language found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary service and primary language and primary segment not found seaching for secondary segment
                            logger.debug("Agents with secondary service and primary language and primary segment not found seaching for secondary segment");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary service and primary language and  secondary segment not found
                                logger.debug("Agents with secondary segment and primary language and  secondary service not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with secondary service and primary language and  secondary segment found
                                logger.debug("Agents with secondary segment and primary language and  secondary service found");
                                this.statusMessage = "Agents with secondary segment and primary language and  secondary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        } else {
                            //Agents with secondary service and primary language and primary segment found
                            logger.debug("Agents with secondary service and primary language and primary segment found");
                            this.statusMessage = "Agents with secondary service and primary language and primary segment found";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                }

            } else {
                //Agents with primary service found
                logger.debug("Agents with primary service found " + employeeCallProficiencyList.size());
                employeelist.clear();
                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                }
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with primary service and primary language not found seaching for secondary language
                    logger.debug("Agents with primary service and primary language not found seaching for secondary language");
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary service and secondary language not found
                        logger.debug("Agents with primary service and secondary language not found");
                        this.statusMessage = "No agent found";
                        employeelist.clear();
                    } else {
                        //Agents with primary service and secondary language found
                        logger.debug("Agents with primary service and secondary language found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary service and secondary language and primary segment not found searching for secondary segment
                            logger.debug("Agents with primary service and secondary language and primary segment not found searching for secondary segment");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(service, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with primary service and secondary language and secondary service not found
                                logger.debug("Agents with primary service and secondary language and secondary service not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with primary service and secondary language and secodary service found
                                logger.debug("Agents with primary service and secondary language and secodary service found");
                                this.statusMessage = "Agents with primary service and secondary language and secodary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }
                        } else {
                            //Agents with primary service and secondary language and primary segment found 
                            logger.debug("Agents with primary service and secondary language and primary segment found ");
                            this.statusMessage = "Agents with primary service and secondary language and primary segment found";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                } else {
                    //Agents with primary service and primary language found
                    logger.debug("Agents with primary service and primary language found");
                    employeelist.clear();
                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);

                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary service and primary language and primary segment not found searching for secondary segment 
                        logger.debug("Agents with primary service and primary language and primary segment not found searching for secondary segment ");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary service and primary language and secondary segment not found 
                            logger.debug("Agents with primary service and primary language and secondary segment not found ");
                            this.statusMessage = "No agent found";
                            employeelist.clear();
                        } else {
                            //Agents with primary service and primary language and secondary segment found 
                            logger.debug("Agents with primary service and primary language and secondary segment found ");
                            this.statusMessage = "Agents with primary service and primary language and secondary segment found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }

                    } else {
                        //Agents with primary service and primary language and primary segment found 
                        logger.debug("Agents with primary service and primary language and primary segment found ");
                        this.statusMessage = "Agents with primary service and primary language and primary segment found ";
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }

                    }

                }

            }
        }
        logger.info("agent size===>" + employeelist.size());
        return employeelist;
    }

    public Set<EmployeeMst> findAgentsServiceSegmentLanguage(String service, String language, String segment) {
        logger.debug("====segment:" + segment + "======service:" + service + "=====language:" + language);
        logger.info("==================findAgentsServiceSegmentLanguage called================");
        //HashMap<String, String> agentSerchSequenceProps = LoadCallRoutingProperties.agentSerchSequenceProperties;
        // List<EmployeeMst> employeelist = new ArrayList<>();
        Set<EmployeeMst> employeelist = new HashSet<>();

        List<EmployeeCallStatus> onlineEmployeelist = employeeCallStatusService.findFreeOnlineAgents();
        employeelist.clear();
        for (EmployeeCallStatus onlineEmployeelist1 : onlineEmployeelist) {
            employeelist.add(onlineEmployeelist1.getEmpId());
        }

        if (employeelist.isEmpty()) {
            statusMessage = "No agent is found";
            logger.info("No agent is found");
        } else {
            logger.debug("Free agent found " + employeelist.size());
            List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

            if (employeeCallProficiencyList.isEmpty()) {
                //Agents with primary service not found seaching for secondary service
                logger.debug("Agents with primary service not found seaching for secondary service");
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with secondary service not found
                    logger.debug("Agents with secondary segment not found");
                    this.statusMessage = "Agent not found";
                    employeelist.clear();
                } else {
                    //Agents with secondary service found
                    logger.debug("Agents with secondary segment found");
                    employeelist.clear();

                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }

                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with secondary service and primary segment not found seaching for secondary segment
                        logger.debug("Agents with secondary service and primary segment not found seaching for secondary segment");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary service and secondary segment not found 
                            logger.debug("Agents with secondary segment and secondary language not found ");
                            this.statusMessage = "Agent not found";
                            employeelist.clear();
                        } else {

                            //Agents with secondary service and secondary segment found
                            logger.debug("Agents with secondary segment and secondary language found");
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary service and secondary segment and primary language not found searching for secondary language
                                logger.debug("Agents with secondary service and secondary segment and primary language not found searching for secondary language");
                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with secondary service and secondary segment and secondary language not found 
                                    logger.debug("Agents with secondary service and secondary segment and secondary language not found ");
                                    this.statusMessage = "No agent found";
                                    employeelist.clear();
                                } else {
                                    //Agents with secondary service and secondary segment and secondary language found 
                                    logger.debug("Agents with secondary segment and secondary language and secondary service found ");
                                    this.statusMessage = "Agents with secondary segment and secondary language and secondary service found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                    }

                                }

                            } else {
                                //Agents with secondary service and secondary segment and primary language found 
                                logger.debug("Agents with secondary segment and secondary language and primary service found ");
                                this.statusMessage = "Agents with secondary segment and secondary language and primary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        }

                    } else {
                        //Agents with secondary service and primary segment found
                        logger.debug("Agents with secondary service and primary language found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary service and primary segment and primary language not found seaching for secondary service
                            logger.debug("Agents with secondary service and primary segment and primary language not found seaching for secondary service");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary service and primary segment and  secondary language not found
                                logger.debug("Agents with secondary service and primary segment and  secondary language not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with secondary service and primary segment and  secondary language found
                                logger.debug("Agents with secondary service and primary segment and  secondary language found");
                                this.statusMessage = "Agents with secondary service and primary segment and  secondary language found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        } else {
                            //Agents with secondary service and primary segment and primary language found
                            logger.debug("Agents with secondary service and primary segment and primary language found");
                            this.statusMessage = "Agents with secondary service and primary segment and primary language found";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                }

            } else {
                //Agents with primary service found
                logger.debug("Agents with primary service found " + employeeCallProficiencyList.size());
                employeelist.clear();
                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                }
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);

                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with primary service and primary segment not found seaching for secondaryg
                    logger.debug("Agents with primary service and primary language not found seaching for secondary segment");
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary service and secondary segment not found
                        logger.debug("Agents with primary service and secondary language not found");
                        this.statusMessage = "No agent found";
                        employeelist.clear();
                    } else {
                        //Agents with primary service and secondary segment found
                        logger.debug("Agents with primary service and secondary segment found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary service and secondary segment and primary language not found searching for secondary segment
                            logger.debug("Agents with primary service and secondary language and primary segment not found searching for secondary segment");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with primary service and secondary language and secondary segment not found
                                logger.debug("Agents with primary service and secondary language and secondary segment not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with primary service and secondary language and secodary segment found
                                logger.debug("Agents with primary service and secondary language and secodary segment found");
                                this.statusMessage = "Agents with primary service and secondary language and secodary segment found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }
                        } else {
                            //Agents with primary service and secondary segment and primary language found 
                            logger.debug("Agents with primary service and secondary language and primary segment found ");
                            this.statusMessage = "Agents with primary service and secondary language and primary segment found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                } else {
                    //Agents with primary service and primary segment found
                    logger.debug("Agents with primary service and primary segment found");
                    employeelist.clear();
                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary service and primary language and primary segment not found searching for secondary segment 
                        logger.debug("Agents with primary service and primary language and primary segment not found searching for secondary segment ");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary service and primary language and secondary segment not found 
                            logger.debug("Agents with primary service and primary language and secondary segment not found ");
                            this.statusMessage = "No agent found";
                            employeelist.clear();
                        } else {
                            //Agents with primary service and primary language and secondary segment found 
                            logger.debug("gents with primary service and primary language and secondary segment found ");
                            this.statusMessage = "gents with primary service and primary language and secondary segment found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }

                    } else {
                        //Agents with primary service and primary language and primary segment found 
                        logger.debug("Agents with primary service and primary language and primary segment found ");
                        this.statusMessage = "Agents with primary service and primary language and primary segment foundK ";
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }

                    }
                }
            }
        }
        logger.info("agent size===>" + employeelist.size());
        return employeelist;
    }

    public Set<EmployeeMst> findAgentsLanguageServiceSegment(String language, String segment, String service) {
        logger.debug("====segment:" + segment + "======service:" + service + "=====language:" + language);
        logger.info("========findAgentsLanguageServiceSegment called ==================");

        // List<EmployeeMst> employeelist = new ArrayList<>();
        Set<EmployeeMst> employeelist = new HashSet<>();

        List<EmployeeCallStatus> onlineEmployeelist = employeeCallStatusService.findFreeOnlineAgents();
        employeelist.clear();
        for (EmployeeCallStatus onlineEmployeelist1 : onlineEmployeelist) {
            employeelist.add(onlineEmployeelist1.getEmpId());
        }

        if (employeelist.isEmpty()) {
            statusMessage = "No agent is found";
            logger.info("No agent is found");
        } else {
            logger.debug("Free agent found " + employeelist.size());
            List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

            if (employeeCallProficiencyList.isEmpty()) {
                //Agents with primary language not found seaching for secondary language
                logger.debug("Agents with primary language not found seaching for secondary language");
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with secondary language not found
                    logger.debug("Agents with secondary language not found");
                    this.statusMessage = "Agent not found";
                    employeelist.clear();
                } else {
                    //Agents with secondary language found
                    logger.debug("Agents with secondary language found");
                    employeelist.clear();

                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }

                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with secondary language and primary service not found seaching for secondary language
                        logger.debug("Agents with secondary language and primary service not found seaching for secondary language");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary language and secondary service not found 
                            logger.debug("Agents with secondary language and secondary service not found ");
                            this.statusMessage = "Agent not found";
                            employeelist.clear();
                        } else {

                            //Agents with secondary language and secondary service found
                            logger.debug("Agents with secondary language and secondary service found");
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);

                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary language and secondary service and primary segment not found searching for secondary segment
                                logger.debug("Agents with secondary language and secondary service and primary segment not found searching for secondary segment");
                                logger.info("Agents with secondary language and secondary service and primary segment not found");
                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with secondary language and secondary service and secondary segment not found 
                                    logger.debug("Agents with secondary language and secondary service and secondary segment not found ");
                                    this.statusMessage = "No agent found";
                                    logger.info("==findEligibleAgents calling : Agents with secondary language and secondary service and secondary segment not found");
                                    employeelist.clear();
                                } else {
                                    //Agents with secondary language and secondary service and secondary segment found 
                                    logger.debug("Agents with secondary language and secondary service and secondary segment found ");
                                    this.statusMessage = "Agents with secondary language and secondary service and secondary segment found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                    }
                                    logger.info("==findEligibleAgents calling : Agents with secondary language and secondary service and secondary segment found");

                                }

                            } else {
                                //Agents with secondary language and secondary service and primary segment found 
                                logger.debug("Agents with secondary language and secondary service and primary segment found ");
                                this.statusMessage = "Agents with secondary language and secondary service and primary segment found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        }

                    } else {
                        //Agents with secondary language and primary service found
                        logger.debug("Agents with secondary language and primary service found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary language and primary service and primary segment not found seaching for secondary segment
                            logger.debug("Agents with secondary language and primary service and primary segment not found seaching for secondary segment");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary language and primary service and  secondary segment not found
                                logger.debug("Agents with secondary language and primary service and  secondary segment not found");
                                this.statusMessage = "No agent found";
                            } else {
                                //Agents with secondary language and primary service and  secondary segment found
                                logger.debug("Agents with secondary segment and primary language and  secondary service found");
                                this.statusMessage = "Agents with secondary segment and primary language and  secondary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }
                                logger.info("Agents with secondary language and primary service and  secondary segment not found");

                            }

                        } else {
                            //Agents with secondary language and primary service and primary segment found
                            logger.debug("Agents with secondary language and primary service and primary segment found");
                            this.statusMessage = "Agents with secondary language and primary service and primary segment found";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                }

            } else {
                //Agents with primary language found
                logger.debug("Agents with primary language found " + employeeCallProficiencyList.size());
                employeelist.clear();
                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                }
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with primary language and primary service not found seaching for secondary service
                    logger.debug("Agents with primary language and primary segment not found seaching for secondary segment");
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary language and secondary service not found
                        logger.debug("Agents with primary language and secondary segment not found");
                        employeelist.clear();
                        this.statusMessage = "No agent found";
//                        employeelist.clear();
                    } else {
                        //Agents with primary language and secondary service found
                        logger.debug("Agents with primary language and secondary segment found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary language and secondary service and primary segment not found searching for secodary service
                            logger.debug("Agents with primary language and secondary segment and primary service not found searching for secodary service");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                            employeelist.clear();
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with primary language and secondary service and secondary segment not found
                                logger.debug("Agents with primary language and secondary segment and secondary service not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with primary language and secondary service and secondary segment found
                                logger.debug("Agents with primary language and secondary segment and secondary service found");
                                this.statusMessage = "Agents with primary language and secondary segment and secondary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }
                                logger.info("==findEligibleAgents calling : Agents with primary language and secondary service and secondary segment not found");

                            }
                        } else {
                            //Agents with primary language and secondary service and primary segment found 
                            logger.debug("Agents with primary segment and secondary language and primary service found ");
                            this.statusMessage = "Agents with primary segment and secondary language and primary service found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                } else {
                    //Agents with primary language and primary service found
                    logger.debug("Agents with primary language and primary segment found");
                    employeelist.clear();
                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);

                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary language and primary service and primary segment not found searching for secondary segment 
                        logger.debug("Agents with primary language and primary segment and primary service not found searching for secondary service ");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary language and primary segment and secondary service not found 
                            logger.debug("Agents with primary language and primary segment and secondary service not found ");

                            this.statusMessage = "No agent found";
                            employeelist.clear();
                        } else {
                            //Agents with primary language and primary segment and secondary service found 
                            logger.debug("Agents with primary language and primary segment and secondary service found ");
                            this.statusMessage = "Agents with primary language and primary segment and secondary service found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }
                            logger.info("==findEligibleAgents calling : Agents with primary language and primary segment and secondary service not found");

                        }

                    } else {
                        //Agents with primary language and primary service primary segment found 
                        logger.debug("Agents with primary language and primary segment and primary service foundd ");
                        this.statusMessage = "Agents with primary language and primary segment and primary service found ";
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }

                    }

                }

            }
        }
        logger.info("agent size===>" + employeelist.size());
        return employeelist;
    }

    public synchronized Set<EmployeeMst> findAgentsLanguageSegmentService(String language, String service, String segment) {
        logger.debug("====segment:" + segment + "======service:" + service + "=====language:" + language);
        logger.info("====================findAgentsLanguageSegmentService called====================");
        //HashMap<String, String> agentSerchSequenceProps = LoadCallRoutingProperties.agentSerchSequenceProperties;

        // List<EmployeeMst> employeelist = new ArrayList<>();
        Set<EmployeeMst> employeelist = new HashSet<>();

        List<EmployeeCallStatus> onlineEmployeelist = employeeCallStatusService.findFreeOnlineAgents();
        employeelist.clear();
        for (EmployeeCallStatus onlineEmployeelist1 : onlineEmployeelist) {
            employeelist.add(onlineEmployeelist1.getEmpId());
        }

        if (employeelist.isEmpty()) {
            statusMessage = "No agent is found";
            logger.info("No agent is found");
        } else {
            logger.debug("Free agent found " + employeelist.size());
            List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(language, employeelist);

            if (employeeCallProficiencyList.isEmpty()) {
                //Agents with primary language not found seaching for secondary language
                logger.debug("Agents with primary language not found seaching for secondary language");
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(language, employeelist);
                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with secondary language not found
                    logger.debug("Agents with secondary language not found");
                    this.statusMessage = "Agent not found";
                    employeelist.clear();
                } else {
                    //Agents with secondary language found
                    logger.debug("Agents with secondary language found");
                    employeelist.clear();

                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                    }

                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with secondary language and primary segment not found seaching for secondary segment
                        logger.debug("Agents with secondary language and primary segment not found seaching for secondary segment");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary language and secondary segment not found 
                            logger.debug("Agents with secondary segment and secondary language not found ");
                            this.statusMessage = "Agent not found";
                            employeelist.clear();
                        } else {

                            //Agents with secondary language and secondary segment found
                            logger.debug("Agents with secondary segment and secondary language found");
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary language and secondary segment and primary service not found searching for secondary service
                                logger.debug("Agents with secondary language and secondary segment and primary service not found searching for secondary service");
                                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                                if (employeeCallProficiencyList.isEmpty()) {
                                    //Agents with secondary language and secondary segment and secondary service not found 
                                    logger.debug("Agents with secondary language and secondary segment and secondary service not found ");
                                    this.statusMessage = "No agent found";
                                    employeelist.clear();
                                } else {
                                    //Agents with secondary language and secondary segment and secondary service found 
                                    logger.debug("Agents with secondary language and secondary segment and secondary service found ");
                                    this.statusMessage = "Agents with secondary language and secondary segment and secondary service found";
                                    employeelist.clear();
                                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                                    }

                                }

                            } else {
                                //Agents with secondary language and secondary segment and primary service found 
                                logger.debug("Agents with secondary language and secondary segment and primary service found ");
                                this.statusMessage = "Agents with secondary language and secondary segment and primary service found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }
                        }

                    } else {
                        //Agents with secondary language and primary segment found
                        logger.debug("Agents with secondary language and primary segment found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with secondary language and primary segment and primary service not found seaching for secondary service
                            logger.debug("Agents with secondary language and primary segment and primary service not found seaching for secondary service");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with secondary language and primary segment and  secondary service not found
                                logger.debug("Agents with secondary language and primary segment and  secondary service not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with secondary language and primary segment and  secondary service found
                                logger.debug("Agents with secondary language and primary segment and  secondary service found");
                                this.statusMessage = "Agents with secondary language and primary service and  secondary segment found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }

                        } else {
                            //Agents with secondary language and primary segment and primary service found
                            logger.debug("Agents with secondary language and primary segment and primary service found");
                            this.statusMessage = "Agents with secondary language and primary segment and primary service found";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                }

            } else {
                //Agents with primary language found
                logger.debug("Agents with primary language found " + employeeCallProficiencyList.size());
                employeelist.clear();
                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                }
                employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(segment, employeelist);

                if (employeeCallProficiencyList.isEmpty()) {
                    //Agents with primary language and primary segment not found seaching for secondary service
                    logger.debug("Agents with primary language and primary service not found seaching for secondary service");
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(segment, employeelist);
                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary language and secondary segment not found
                        logger.debug("Agents with primary language and secondary service not found");
                        this.statusMessage = "No agent found";
                        employeelist.clear();
                    } else {
                        //Agents with primary language and secondary segment found
                        logger.debug("Agents with primary language and secondary service found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                        }
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary language and secondary segment and primary service not found searching for secodary service
                            logger.debug("Agents with primary language and secondary service and primary segment not found searching for secodary service");
                            employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                            if (employeeCallProficiencyList.isEmpty()) {
                                //Agents with primary language and secondary segment and secondary service not found
                                logger.debug("Agents with primary language and secondary service and secondary segment not found");
                                this.statusMessage = "No agent found";
                                employeelist.clear();
                            } else {
                                //Agents with primary language and secondary segment and secondary service found
                                logger.debug("Agents with primary language and secondary servic and secodary segment found");
                                this.statusMessage = "Agents with primary language and secondary servic and secodary segment found";
                                employeelist.clear();
                                for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                    employeelist.add(employeeCallProficiencyList1.getEmpId());
                                }

                            }
                        } else {
                            //Agents with primary language and secondary segment and primary service found 
                            logger.debug("Agents with primary language and secondary service and primary segment found ");
                            this.statusMessage = "Agents with primary language and secondary service and primary segment found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }
                    }

                } else {
                    //Agents with primary language and primary segment found
                    logger.debug("Agents with primary language and primary segment found");
                    logger.info("Agents with primary language and primary segment found " + employeelist.size());

                    employeelist.clear();
                    for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                        employeelist.add(employeeCallProficiencyList1.getEmpId());
                        logger.info("====Agents with primary language and primary segment found Emp Id=====  " + employeeCallProficiencyList1.getEmpId());
                    }
                    employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(service, employeelist);

                    if (employeeCallProficiencyList.isEmpty()) {
                        //Agents with primary language and primary segment and primary service not found searching for secondary service 
                        logger.debug("Agents with primary language and primary service and primary segment not found searching for secondary segment ");
                        employeeCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(service, employeelist);
                        if (employeeCallProficiencyList.isEmpty()) {
                            //Agents with primary language and primary service and secondary segment not found 
                            logger.debug("Agents with primary language and primary service and secondary segment not found  ");
                            this.statusMessage = "No agent found";
                            employeelist.clear();
                        } else {
                            //Agents with primary language and primary service and secondary segment found 
                            logger.debug("Agents with primary language and primary service and secondary segment found ");
                            this.statusMessage = "Agents with primary language and primary service and secondary segment found ";
                            employeelist.clear();
                            for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                                employeelist.add(employeeCallProficiencyList1.getEmpId());
                            }

                        }

                    } else {
                        //Agents with primary language and primary segment and primary service found 
                        logger.debug("Agents with primary language and primary service and primary segment found ");
                        this.statusMessage = "Agents with primary language and primary service and primary segment found ";
                        logger.info("Agents with primary language and primary service and primary segment found");
                        employeelist.clear();
                        for (EmployeeCallProficiency employeeCallProficiencyList1 : employeeCallProficiencyList) {
                            employeelist.add(employeeCallProficiencyList1.getEmpId());
                            logger.info("Agents with primary language and primary service and primary segment found--Emp Id " + employeeCallProficiencyList1.getEmpId());
                        }

                    }
                }
            }
        }
        logger.info("agent size===>" + employeelist.size());

        return employeelist;
    }

    private Set<EmployeeMst> findEligibleAgents(List<String> skillLst, Set<EmployeeMst> empLst) {

        Set<EmployeeMst> employeelist = new HashSet<>();
        Set<EmployeeMst> mstEmpList = new HashSet<>();
        logger.info("====skillLst size() === " + skillLst.size());
        for (String skl : skillLst) {
            logger.info("=====SKILL IS===== " + skl);
            List<EmployeeCallProficiency> agentCallProficiencyList;

            agentCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySkillAndEmployeeList(skl, empLst);
            if (agentCallProficiencyList.isEmpty()) {
                // Agents with given primary skill not found

                agentCallProficiencyList = employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySkillAndEmployeeList(skl, empLst);
                if (agentCallProficiencyList.isEmpty()) {
                    // Agents with given secondary skill not found  
                    this.statusMessage = "Agents not found ";
                    logger.info("Agents with given secondary skill " + skl + " not found ");
                } else {
                    // Agents with given secondary skill found   
                    this.statusMessage = "Agents with given secondary skill " + skl + " found ";
                    logger.info("Agents with given secondary skill " + skl + " found");
                    employeelist.clear();
                    for (EmployeeCallProficiency agentCallProficiencyList1 : agentCallProficiencyList) {
                        employeelist.add(agentCallProficiencyList1.getEmpId());
                    }
                }
            } else {
                // Agents with given primary skill found   
                logger.info("Agents with given primary skill " + skl + " found");
                employeelist.clear();
                for (EmployeeCallProficiency agentCallProficiencyList1 : agentCallProficiencyList) {
                    employeelist.add(agentCallProficiencyList1.getEmpId());
                }
            }
            for (EmployeeMst empMaster : employeelist) {
                mstEmpList.add(empMaster);
            }
        }
        logger.info("======Agent size in findEligibleAgents : ======" + employeelist.size());
        return mstEmpList;
    }

}
