package com.mini.auction.common.exceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.mini.auction.common.exceptionHandler.customException.BadRequestException;
import com.mini.auction.common.exceptionHandler.customException.NotFoundResourceException;
import com.mini.auction.common.exceptionHandler.customException.NotFoundUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //잘 못 된 요청에 대한 익셉션
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException e) {
        logger.error("BadRequestException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrorMessage());
    }

    //요청 값으로 조회된 결과가 없는 경우
    @ExceptionHandler(value = NotFoundResourceException.class)
    public ResponseEntity<?> notFoundException(NotFoundResourceException e) {
        logger.error("NotFoundException: ", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorMessage());
    }

    //로그인 실패 시
    @ExceptionHandler(value = NotFoundUserException.class)
    public ResponseEntity<?> notFoundUserException(NotFoundUserException e) {
        logger.error("NotFoundUserException: ", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getErrorMessage());
    }

    //uri 의 파라미터 타입이 맞지 않을 경우
    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity<?> missingPathVariableExceptionException(MissingPathVariableException e) {
        ErrorResponse error = new ErrorResponse(ErrorCode.E00002, e.getMessage());
        logger.error("MissingPathVariableException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //필수 param 값이 없을 경우
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        ErrorResponse error = new ErrorResponse(ErrorCode.E00001, e.getMessage());
        logger.error("MissingServletRequestParameterException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //param 의 타입이 안맞는 경우
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchExceptionException(MethodArgumentTypeMismatchException e) {
        ErrorResponse error =
            new ErrorResponse(ErrorCode.E00002, "'" + e.getName() + "' is must be '" + e.getRequiredType() + "' type.");
        logger.error("MethodArgumentTypeMismatchException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //dto Validation 실패
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String, String> params = new HashMap<String, String>();
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            params.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse(ErrorCode.E00001, params);
        logger.error("MethodArgumentNotValidException: ", e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //request body 에 대한 에러 발생 시. (ex. data type mismatch, deserialization error .. )
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable throwable = e.getMostSpecificCause();
        HashMap<String, Object> params = new HashMap<String, Object>();
        ErrorResponse error;
        if(throwable instanceof InvalidFormatException) {
            InvalidFormatException t = ((InvalidFormatException) throwable);
            logger.error("InvalidFormatException: ", t);
            params.put("Value" , t.getValue());
            params.put("Target Type", t.getTargetType());
            error = new ErrorResponse(ErrorCode.E00003, params);
        } else if (throwable instanceof MismatchedInputException) {
            MismatchedInputException t = ((MismatchedInputException) throwable);
            logger.error("MismatchedInputException: ", t);
            error = new ErrorResponse(ErrorCode.E00003, t.getOriginalMessage());
        } else if (throwable instanceof JsonParseException) {
            JsonParseException t = ((JsonParseException) throwable);
            logger.error("JsonParseException: ", t);
            error = new ErrorResponse(ErrorCode.E00003, t.getOriginalMessage());
        } else {
            logger.error("HttpMessageNotReadableException: ", e);
            error = new ErrorResponse(ErrorCode.E00003, e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //예상 불가능한 모든 익셉션
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> serverException(Exception e) {
        logger.error("Server Exception: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
