package com.shkap.data;

import android.net.Uri;

/**
 * Created by Eduard Albu on 06.08.2015.
 */
public class ApiInfo {

    private static final String SHKAP_SERV = "/buldakov.org:8080";
    private static final String API_V = "v1";
    private static final String USERS = "users";
    private static final String THINGS = "things";
    private static final String ATTACHMENTS = "attachments";
    private static final String PROTOCOL = "http";
    private static final String REGISTER = "_register";
    private static final String VK = "vk";
    private static final String FB = "fb";

    public static Uri getUsers() {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(USERS)
                .build();
    }

    public static Uri getUser(String userId) {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(USERS)
                .appendPath(userId)
                .build();
    }

    public static Uri getThings() {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(THINGS)
                .build();
    }

    public static Uri getThing(String thingId) {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(THINGS)
                .appendPath(thingId)
                .build();
    }

    public static Uri regToVK() {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(VK)
                .appendPath(REGISTER)
                .build();
    }

    public static Uri regToFacebook() {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(FB)
                .appendPath(REGISTER)
                .build();
    }

    public static Uri getAttachments() {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(ATTACHMENTS)
                .build();
    }

    public static Uri getAttachment(String attachmentId) {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(ATTACHMENTS)
                .appendPath(attachmentId)
                .build();
    }
}
