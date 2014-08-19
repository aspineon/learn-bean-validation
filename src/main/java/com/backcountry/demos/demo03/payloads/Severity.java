package com.backcountry.demos.demo03.payloads;

import javax.validation.Payload;

/**
 * @author Edwin Dalorzo.
 */
public class Severity {
    public static class Info implements Payload{}
    public static class Error implements Payload{}
    public static class Warning implements Payload{}
}
