package com.management.controller.labelsync;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabelSync {

    public static void getAPI() throws JSONException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://gitlab.com/api/v4/projects/36006741/labels?title=sth");
        get.setHeader(HttpHeaders.AUTHORIZATION, "Bearer" + "glpat-7k9p3T6bzzqTHr7ZDoVG");
        try {
            HttpResponse responses = client.execute(get);
            System.out.println(responses.getStatusLine().getStatusCode());
            JSONArray json = new JSONArray(IOUtils.toString(responses.getEntity().getContent()));
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObj = json.getJSONObject(i);
                System.out.println(jsonObj.get("title"));
            }
        } catch (IOException ex) {
            Logger.getLogger(LabelSync.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(LabelSync.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws JSONException {
        getAPI();
    }
}
