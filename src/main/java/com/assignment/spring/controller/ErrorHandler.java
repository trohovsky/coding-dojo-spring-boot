package com.assignment.spring.controller;

import com.assignment.spring.dto.ErrorDto;
import feign.FeignException.BadRequest;
import feign.FeignException.FeignClientException;
import feign.FeignException.NotFound;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFound.class)
    protected ResponseEntity<ErrorDto> handleNotFound(NotFound e) {
        return ResponseEntity.status(NOT_FOUND).body(toErroto(e));
    }

    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<ErrorDto> handleBadRequest(BadRequest e) {
        return ResponseEntity.status(BAD_REQUEST).body(toErroto(e));
    }

    private ErrorDto toErroto(FeignClientException e) {
        val pattern = Pattern.compile(".*?\\\"message\\\":\"(.*?)\".*");
        val matcher = pattern.matcher(e.getMessage());
        if (matcher.find()) {
            return new ErrorDto(matcher.group(1));
        } else {
            return new ErrorDto(e.getMessage());
        }
    }

}
