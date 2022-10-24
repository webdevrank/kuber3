package com.rank.ccms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "FileDownLoadCustomServlet", urlPatterns = {"/FileDownLoadCustomServlet"})
public class FileDownLoadCustomServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(FileDownLoadCustomServlet.class);

    private static final int BYTES_DOWNLOAD = 1024;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {

            fileName = request.getParameter("fileName");

            log.info("filePath ---- " + fileName);

            String filePath = fileName.substring(fileName.indexOf("://") + 3);
            String finalFilePath = filePath.substring(filePath.indexOf(request.getContextPath() + "/") + request.getContextPath().length());
            log.info("New file filePath!!! : " + finalFilePath);

            response.setContentType(new MimetypesFileTypeMap().getContentType(finalFilePath));
            File file = new File(finalFilePath);

            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

            ServletContext servletContext = getServletContext();

            inputStream = new FileInputStream(servletContext.getRealPath(finalFilePath));

            int read;
            byte[] bytes = new byte[BYTES_DOWNLOAD];
            outputStream = response.getOutputStream();

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            log.error("ERROR : ", e);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
