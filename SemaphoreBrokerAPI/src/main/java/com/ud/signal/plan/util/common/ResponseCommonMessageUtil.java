package com.ud.signal.plan.util.common;

import com.ud.signal.plan.response.ResponseCommonMessage;

public class ResponseCommonMessageUtil {

    public ResponseCommonMessage getResponseCommonMessageDefault(int statusCode) {
        ResponseCommonMessage responseCommonMessage = new ResponseCommonMessage();
        if (statusCode >= 200 && statusCode < 300) {
            responseCommonMessage.setCode("0");
            responseCommonMessage.setSeverity("info");
            responseCommonMessage.setMessage("Success");
            responseCommonMessage.setCodeSeverity(String.valueOf(statusCode));
        } else if (statusCode >= 400 && statusCode < 500) {
            responseCommonMessage.setCode("100");
            responseCommonMessage.setSeverity("Error");
            responseCommonMessage.setMessage("Invalidrequest");
            responseCommonMessage.setCodeSeverity(String.valueOf(statusCode));
        } else if (statusCode >= 500 && statusCode < 600) {
            responseCommonMessage.setCode("300");
            responseCommonMessage.setSeverity("Error");
            responseCommonMessage.setMessage("InternalServerError");
            responseCommonMessage.setCodeSeverity(String.valueOf(statusCode));
        }else{
            responseCommonMessage.setCodeSeverity(String.valueOf(statusCode));
        }

        return responseCommonMessage;
    }
}
