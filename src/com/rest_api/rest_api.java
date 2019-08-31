package com.rest_api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

abstract public class rest_api
{
    abstract public String get(String request, String... args);

    rest_api(String endpoint)
    {
            this.endpoint = endpoint;
    }

    String call_get(String request)
    {
        try
        {
            URL url = new URL(this.endpoint+request);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            DataInputStream data = new DataInputStream(con.getInputStream());
            return convertStreamToString(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Get method failed!";
    }

    private String endpoint;

    private static <T extends DataInputStream> String convertStreamToString(T stream) throws Exception
    {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read_len=-1;
        while ((read_len = stream.read(buffer)) != -1)
        {
            result.write(buffer, 0, read_len);
        }

        return result.toString(StandardCharsets.UTF_8.name());
    }

}

