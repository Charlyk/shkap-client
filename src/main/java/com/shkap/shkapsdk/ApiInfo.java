package com.shkap.shkapsdk;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eduard Albu on 06.08.2015.
 */
public class ApiInfo {

    private static final String SHKAP_SERV = "buldakov.org";
    private static final String API_V = "v1";
    private static final int PORT = 8080;
    private static final String USERS = "users";
    private static final String THINGS = "things";
    private static final String ATTACHMENTS = "attachments";
    private static final String PROTOCOL = "http";
    private static final String REGISTER = "_register";
    private static final String VK = "vk";
    private static final String FB = "fb";

    public static URL getUsers() throws MalformedURLException {
        Uri.Builder builder = new Uri.Builder();
        builder.appendPath(API_V)
                .appendPath(USERS)
                .build();
        return new URL(PROTOCOL, SHKAP_SERV, PORT, builder.toString());
    }

    public static URL getUser(String userId) throws MalformedURLException {
        Uri.Builder builder = new Uri.Builder();
        builder.appendPath(API_V)
                .appendPath(USERS)
                .appendPath(userId)
                .build();
        return new URL(PROTOCOL, SHKAP_SERV, PORT, builder.toString());
    }

    public static Uri things() {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(THINGS)
                .build();
    }

    public static Uri thing(String thingId) {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme(PROTOCOL)
                .appendEncodedPath(SHKAP_SERV)
                .appendPath(API_V)
                .appendPath(THINGS)
                .appendPath(thingId)
                .build();
    }

    public static URL regWithVK() throws MalformedURLException {
        Uri.Builder builder = new Uri.Builder();
        builder.appendPath(API_V)
                .appendPath(VK)
                .appendPath(REGISTER)
                .build();
        return new URL(PROTOCOL, SHKAP_SERV, PORT, builder.toString());
    }

    public static URL regWithFacebook() throws MalformedURLException {
            Uri.Builder builder = new Uri.Builder();
            builder.appendPath(API_V)
                    .appendPath(FB)
                    .appendPath(REGISTER)
                    .build();
        return new URL(PROTOCOL, SHKAP_SERV, PORT, builder.toString());
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
