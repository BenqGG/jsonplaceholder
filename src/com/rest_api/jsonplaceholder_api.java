package com.rest_api;

public class jsonplaceholder_api extends rest_api
{
    public jsonplaceholder_api()
    {
        super("https://jsonplaceholder.typicode.com/");
    }

    @Override
    public String get(String request, String... args)
    {
        switch(request)
        {
            case "posts":
                return super.call_get("posts/");

            case "post":
                if(args.length == 0)
                    return "jsonplaceholder_api: No posts id included for '" + request + "' request.";
                return super.call_get("posts/" + args[0]);

            default:
                return "jsonplaceholder_api: unknown '" + request + "' request.";
        }
    }
}
