package com.management.dao;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestSync {
    public static void Post(String id, String value){
        try {
            HttpClient client = HttpClientBuilder.create().build();
            URI uri = new URI("https://gitlab.com/api/v4/projects/"+id+"/milestones");
            RequestBuilder builder = RequestBuilder.post().setUri(uri);
            builder.addParameter("title", value);
            HttpUriRequest uri1 = builder.build();
            uri1.setHeader(HttpHeaders.AUTHORIZATION,"Bearer " + "glpat-ssKwPs8kdrmjBL8c1K7b");
            try {
                HttpResponse response = client.execute(uri1);
                System.out.println(response.getStatusLine().getStatusCode());
            } catch (IOException ex) {

                System.out.println(ex);
            }

        } catch (URISyntaxException ex) {

            System.out.println(ex);
        }
    }



    public static void main(String[] args) {

        Post("37673318", "abcd");

    }
}

