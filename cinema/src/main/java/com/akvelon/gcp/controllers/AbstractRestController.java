package com.akvelon.gcp.controllers;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * super for all rest controllers
 */
@RestControllerAdvice
public abstract  class AbstractRestController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRestController.class);


    /**
     * build body response - error
     *
     * @param data
     * @param status - status
     * @return
     */
    public static <T> ResponseEntity<T> error(T data, HttpStatus status) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(status).headers(responseHeaders).body(data);
    }

    /**
     * build body response - error
     *
     * @param data
     * @param status  - status
     * @param message - message
     * @return
     */
    public static <T> ResponseEntity<T> error(T data, HttpStatus status, String message) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(status).headers(responseHeaders).body(data);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    final ResponseEntity<?> handle(final Exception exception) throws Exception {
        logger.error(exception.getMessage());
        //TODO send error
        if (exception instanceof HttpStatusCodeException)
            return error((HttpStatusCodeException) exception,HttpStatus.NOT_EXTENDED );
        //ошибки валидации
        if (exception instanceof MethodArgumentNotValidException) {
            try {
                BindingResult rez = ((MethodArgumentNotValidException) exception).getBindingResult();
                return error(rez.toString(), HttpStatus.NOT_EXTENDED);
            } catch (IllegalArgumentException ex1) {
                logger.error("error cast", ex1);
            }
        }

        if (exception instanceof MissingServletRequestParameterException) {
            String paramName = ((MissingServletRequestParameterException) exception).getParameterName();
            return error(paramName , HttpStatus.NOT_EXTENDED);

        }
        if (exception instanceof ValidationException) {
            String paramName = null;
            if (exception instanceof ConstraintViolationException) {
                paramName = ((ConstraintViolationException) exception).getConstraintViolations().stream().map(cv -> cv.toString()).collect(Collectors.joining(","));
            } else {
                paramName = exception.getMessage();
            }
            return error(paramName, HttpStatus.NOT_EXTENDED);
        }

        if (exception instanceof NotFoundException) {
            return error(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

        logger.error("OVER ERROR" , exception);
        return error(exception, HttpStatus.NOT_EXTENDED);
    }


}
