package com.common.utils;

import org.apache.log4j.MDC;

import java.util.Arrays;


public class LogUtils {

    public static void EmaException(String message) {
        String method = "";
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        StackTraceElement[] stenew;
        if(ste.length>7){
            stenew = Arrays.copyOfRange(ste,2,7);
        }else{
            stenew = ste;
        }
        for (StackTraceElement sm : stenew) {
            method += sm.getFileName()+":"+sm.getMethodName()+":line"+sm.getLineNumber()+",";
        }
        MDC.put("errorLocation",method);
        throw new RuntimeException(message+ "," + method);
    }

    public static void checkArgument(boolean expression,Object errorMessage) {
        if (!expression) {
            EmaException(String.valueOf(errorMessage));
        }
    }

    public static void Exception() {
        String method = "";
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        StackTraceElement[] stenew;
        if(ste.length>12){
            stenew = Arrays.copyOfRange(ste, 2, 12);
        }else{
            stenew = ste;
        }
        for (StackTraceElement sm : stenew) {
            method += sm.getFileName()+":"+sm.getMethodName()+":line"+sm.getLineNumber()+",";
        }
        org.slf4j.MDC.put("errorLocation", method);
    }
}
