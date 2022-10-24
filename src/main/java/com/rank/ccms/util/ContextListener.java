package com.rank.ccms.util;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.commons.io.FileUtils;

import org.apache.log4j.PropertyConfigurator;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ContextListener.class);
    private static ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);
        fileCopyFromContext(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // do nothing
    }

    private void fileCopyFromContext(ServletContext ctx) {
        // ServletContext ctx = (ServletContext)
        // FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath(File.separator);

        String dbFilePath = "/PromotionalVideo";
        String jbossHome = System.getenv("JBOSS_HOME");
        String path2 = jbossHome + File.separator + "standalone" + File.separator + "File_Upload";
        File srcDir = new File(path2);
        File destDir = new File(deploymentDirectoryPath + "/resources/File_Upload");
        logger.info("srcDir:" + srcDir + "destDir:" + destDir);
        String path = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs"
                + File.separator + "PromotionalVideo";
        File srcDir2 = new File(path);
        File destDir2 = new File(deploymentDirectoryPath + dbFilePath);
        logger.info("srcDir2:" + srcDir2 + "destDir2:" + destDir2);
        String path1 = jbossHome + File.separator + "standalone" + File.separator + "KYC_Upload";
        File srcDir3 = new File(path1);
        File destDir3 = new File(deploymentDirectoryPath);

        String path3 = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs"
                + File.separator + "crm";
        File srcDir4 = new File(path3);
        File destDir4 = new File(deploymentDirectoryPath + "crm");

        if (!srcDir4.exists()) {
            srcDir4.mkdir();
            logger.info("New Dir create... in  srcDir4 of " + srcDir4.getAbsolutePath());
        } else {
            try {
                FileUtils.copyDirectory(srcDir4, destDir4);
                logger.info("File Copy Was Success... in destDir4 of  " + destDir4.getAbsolutePath() + " from "
                        + srcDir4.getAbsolutePath());
            } catch (IOException ex) {
                logger.error(ex);
            }
        }

        if (!srcDir3.exists()) {
            srcDir3.mkdir();
            logger.info("New Dir create... in  srcDir3 of " + srcDir3.getAbsolutePath());
        } else {
            try {
                FileUtils.copyDirectory(srcDir3, destDir3);
                logger.info("File Copy Was Success... in destDir3 of  " + destDir3.getAbsolutePath() + " from "
                        + srcDir3.getAbsolutePath());
            } catch (IOException ex) {
                logger.error(ex);
            }
        }

        if (!srcDir2.exists()) {
            srcDir2.mkdir();
            logger.info("New Dir create... in " + srcDir2.getAbsolutePath());
        } else {
            try {
                FileUtils.copyDirectory(srcDir2, destDir2);
                logger.info("File Copy Was Success... in " + destDir2.getAbsolutePath() + " from "
                        + srcDir2.getAbsolutePath());
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        if (!srcDir.exists()) {
            srcDir.mkdir();
            logger.info("New Dir create...");
        } else {

            try {
                FileUtils.copyDirectory(srcDir, destDir);
                logger.info("File Copy Was Success...");
            } catch (IOException ex) {
                logger.error(ex);
            }
        }

    }

    public static ServletContext getContext() {

        return context;
    }

}
