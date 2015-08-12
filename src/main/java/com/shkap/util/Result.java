package com.shkap.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Eduard Albu on 12.08.2015.
 */
public class Result<T> {

    private T object;
    private final ObjectMapper mapper = new ObjectMapper();
}
