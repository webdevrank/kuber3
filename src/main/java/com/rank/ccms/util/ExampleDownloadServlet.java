package com.rank.ccms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExampleDownloadServlet", urlPatterns = {"/ExampleDownloadServlet"})
public class ExampleDownloadServlet extends HttpServlet {

    private static final int BYTES_DOWNLOAD = 1024;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fName;
        OutputStream os = null;
        InputStream is = null;
        try {
            fName = (String) request.getSession().getAttribute("imagePath");
            response.setContentType(new MimetypesFileTypeMap().getContentType(fName));
            String flName = fName.substring(fName.lastIndexOf(File.separator));
            response.setHeader("Content-Disposition", "attachment;filename=" + flName);

            is = new FileInputStream(fName);

            int read;
            byte[] bytes = new byte[BYTES_DOWNLOAD];
            os = response.getOutputStream();
            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

        } catch (Exception e) {

        } finally {
            if (null != os) {
                os.close();
            }
            if (null != is) {
                is.close();
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
