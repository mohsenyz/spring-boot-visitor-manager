/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.http;

import com.utils.io.PathHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author mphj
 */
public class Uploader {

    private HttpServletRequest httpServletRequest;
    private Part part;
    private String fileName;
    private String path;

    public static Uploader from(Part part) {
        Uploader uploader = new Uploader();
        uploader.part = part;
        return uploader;
    }

    public Uploader withName(String string) {
        fileName = string;
        return this;
    }

    public void saveInto(String path) {
        try {
            this.path = path;
            FileOutputStream fileOutputStream
                    = new FileOutputStream(PathHelper.combine(path, fileName));
            InputStream inputStream = part.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
