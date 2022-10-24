package com.rank.ccms.util;

import java.io.*;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter(
        urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
)
public class MyFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = URLDecoder.decode(req.getRequestURI(), "UTF-8");
        Pattern pattern = Pattern.compile(".*(css|js|png|jpg|gif|jsp|jpeg|mp3|docx)");

        Matcher matcher = pattern.matcher(requestURI);

        if (matcher.matches()) {
            res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            res.setHeader("Access-Control-Max-Age", "3600");
            chain.doFilter(request, response);
            return;
        }

        String contextPath = req.getContextPath();
        if (requestURI.equals(contextPath)
                || requestURI.equals(contextPath + "/faces/loginEmployee.xhtml")
                || requestURI.equals(contextPath + "/faces/customerHome.xhtml")
                || requestURI.equals(contextPath + "/faces/customerList.xhtml")
                || requestURI.contains("/ws") || requestURI.contains("/files")
                || requestURI.equals(contextPath + "/faces/loginCustomer.xhtml")
                || requestURI.equals(contextPath + "/faces/pages/agent/cobrowse.xhtml")
                || requestURI.equals(contextPath + "/faces/pages/customer/viewCustomerFormAccount.xhtml")
                || requestURI.equals(contextPath + "/faces/pages/customer/viewCustomerFormLoan.xhtml")) {

        } else if ((requestURI.contains(contextPath + "/faces/pages/agent/agentDashboard.xhtml")
                || requestURI.contains(contextPath + "/faces/pages/supervisor/viewPreviousChat.xhtml")
                || requestURI.contains(contextPath + "/faces/pages/agent/specialistDashboard.xhtml"))
                && req.getSession().getAttribute("ormAgentMaster") == null) {

            req.getSession().setAttribute("ormAdminMaster", null);
            req.getSession().setAttribute("ormUserMaster", null);
            req.getSession().setAttribute("ormAgentMaster", null);

            res.sendRedirect(contextPath + "/login");

        } else if (((requestURI.contains("/customerVideoCall.xhtml")) || (requestURI.contains("/customerDashboard.xhtml"))) && req.getSession().getAttribute("ormCustomerName") == null) {
            res.sendRedirect(contextPath + "/loginCustomer");

        } else if ((requestURI.contains("/agent") || requestURI.contains("/auditTrail") || requestURI.contains("/category") || requestURI.contains("/employee") || requestURI.contains("/language") || requestURI.contains("/reason") || requestURI.contains("/reports") || requestURI.contains("/listCustomer") || requestURI.contains("/schedule") || requestURI.contains("/service") || requestURI.contains("/maps") || requestURI.contains("/promotionalVideo") || requestURI.contains("/configuration") || requestURI.contains("/supervisor")) && req.getSession().getAttribute("ormAdminMaster") == null) {

            if ((requestURI.contains(contextPath + "/faces/pages/agent/agentDashboard.xhtml")
                    || requestURI.contains(contextPath + "/faces/pages/supervisor/viewChat.xhtml")
                    || requestURI.contains(contextPath + "/faces/pages/reports/viewChatHistory.xhtml")
                    || requestURI.contains(contextPath + "/faces/pages/supervisor/viewPreviousChat.xhtml")
                    || requestURI.contains(contextPath + "/faces/pages/agent/specialistDashboard.xhtml"))) {
                if (req.getSession().getAttribute("ormAgentMaster") == null) {
                    req.getSession().setAttribute("ormAdminMaster", null);
                    req.getSession().setAttribute("ormUserMaster", null);
                    req.getSession().setAttribute("ormAgentMaster", null);
                    res.sendRedirect(contextPath + "/login");
                }

            } else {

                req.getSession().setAttribute("ormAdminMaster", null);
                req.getSession().setAttribute("ormUserMaster", null);
                req.getSession().setAttribute("ormAgentMaster", null);
                res.sendRedirect(contextPath + "/login");
            }

        }
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter((ServletRequest) request, (ServletResponse) response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }
}
