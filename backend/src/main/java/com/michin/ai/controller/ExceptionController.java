package com.michin.ai.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.michin.ai.common.Result;
import com.michin.ai.common.util.notification.NotificationManager;

@ControllerAdvice
public class ExceptionController {
    @Autowired
    private NotificationManager notificationManager;

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionTest(Exception e, HttpServletRequest req) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.append("- ").append(key).append(" : ").append(req.getParameter(key)).append('\n');
        }

        e.printStackTrace();
        notificationManager.sendNotification(e, req.getRequestURI(), params.toString());

        return Result.failure("잘못된 요청입니다.");
    }
}
