package com.company;
import com.rest_api.jsonplaceholder_api;
import com.rest_api.rest_api;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileWriter;

public class Main
{
    public static void main(String[] args)
    {
        //Our base api
        rest_api api;

        //Now we choose to use json_placeholder
        api = new jsonplaceholder_api();

        //And we call for posts
        String res = api.get("posts");

        //This will fail, jsonplaceholder_api doesnt have "names"
        System.out.println(api.get("names"));

        //This will fail, we forgot to add post id
        System.out.println(api.get("post"));

        //Now we call for specified posts
        System.out.println(api.get("post", "44"));

        JSONParser parser = new JSONParser();
        try
        {
            JSONArray json_res = (JSONArray) parser.parse(res);
            File res_dir = new File("result");

            if(!res_dir.exists())
                res_dir.mkdir();

            for (Object json_re : json_res)
            {
                JSONObject obj = (JSONObject) json_re;

                FileWriter writer = new FileWriter("result/" + obj.get("userId") + "-" + obj.get("id")+ ".json");
                writer.write(obj.toJSONString());

                writer.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

