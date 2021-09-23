package com.huyvd.utils;

import java.io.*;

public class DataFileUtil {

    private final static String IMAGE_STORE = "D:\\image_web";

    public static String saveToDisk(String fileName, InputStream inputStream) throws IOException{
        File file = new File(IMAGE_STORE + File.separator + fileName);
        System.out.println("--Save image: " + file.getPath());

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[10240];
            while (true) {
                try {
                    if ((read = inputStream.read(bytes)) == -1) break;
                    outputStream.write(bytes, 0, read);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputStream.close();
            inputStream.close();
            return file.getPath();
        } catch (IOException e) {
            if (null != outputStream) outputStream.close();
            if (null != inputStream) inputStream.close();
        }
        return null;
    }
}
