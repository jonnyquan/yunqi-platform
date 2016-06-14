package com.yunqi.apis.user.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = UserController.class)
public class FooControllerAdvice extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(YourException.class)
//    @ResponseBody
//    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
//    }
//
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }

}