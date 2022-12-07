package com.management.util;

import javax.servlet.http.HttpServletRequest;

public class Extracted {
    public static HttpServletRequest extracted(HttpServletRequest request, int count, int size) {
        String indexString = request.getParameter("index");
        if (indexString == null || indexString.trim().isEmpty()) {
            indexString = "1";
        }
        int index = 0;
        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            request.setAttribute("error", "Page invalid");
        }
        int lastPage = count / size;
        if (count % size != 0) {
            lastPage++;
        }

        request.setAttribute("index", index);
        request.setAttribute("lastPage", lastPage);
        request.setAttribute("countResult", count);

        return request;
    }
}
