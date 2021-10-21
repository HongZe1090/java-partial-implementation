package com.ni.sourceCode.framwork.protocal.Http;

import com.alibaba.fastjson.JSONObject;
import com.ni.sourceCode.framwork.protocal.Invocation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {
    //地址，端口，要调用的对象
    public String send(String hostName , Integer port , Invocation invocation) {

        try {
            var request = HttpRequest.newBuilder()
                    .uri(new URI("http",null,hostName,port, "/",null , null))
                    .POST(HttpRequest.BodyPublishers.ofString(JSONObject.toJSONString(invocation)))
                    .build();
            var client = java.net.http.HttpClient.newHttpClient();

            HttpResponse<String> response = null;
            try {
                response = client.send(request , HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String result = response.body();
            System.out.println(result);
                return result;


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return hostName;
    }
}
