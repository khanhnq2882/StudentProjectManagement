package com.management.util;

public class Alert {

    public final static String SUCCESS = "success";
    public final static String ERROR = "error";
    public final static String WARNING = "warning";

    public static String alert(String title, String message, String state) {
        return "<script>swal(\"" + title + "\", \"" + message + "\", \"" + state + "\");</script>";
    }
}