package org.pickwicksoft.mountainfever.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebResourceHelper {

    public static byte[] downloadUrl(URL toDownload) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            byte[] chunk = new byte[4096];
            int bytesRead;
            InputStream stream = toDownload.openStream();

            while ((bytesRead = stream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return outputStream.toByteArray();
    }

    public static String getContentType(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            return connection.getContentType();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
