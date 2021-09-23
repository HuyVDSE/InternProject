package com.huyvd.utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    private final static String IMAGE_FOLDER = "D:\\image_web";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getPathInfo().substring(1);
        System.out.println(fileName);
        File file = new File(IMAGE_FOLDER, fileName);
        response.setContentType(getServletContext().getMimeType(fileName));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
