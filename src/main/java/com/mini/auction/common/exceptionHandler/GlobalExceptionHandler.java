package com.mini.auction.common.exceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.mini.auction.common.exceptionHandler.customException.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
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
public class GlobalExceptionHandler{

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
    @ExceptionHandler(value = NotFoundAuthException.class)
    public ResponseEntity<?> notFoundUserException(NotFoundAuthException e) {
        logger.error("NotFoundAuthException: ", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getErrorMessage());
    }

    //uri 의 파라미터 타입이 맞지 않을 경우
    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity<?> missingPathVariableExceptionException(MissingPathVariableException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.E00002, e.getMessage());
        logger.error("MissingPathVariableException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    //필수 param 값이 없을 경우
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.E00001, e.getMessage());
        logger.error("MissingServletRequestParameterException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    //param 의 타입이 안맞는 경우
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchExceptionException(MethodArgumentTypeMismatchException e) {
        ExceptionResponse exceptionResponse =
            new ExceptionResponse(ExceptionCode.E00002, "'" + e.getName() + "' is must be '" + e.getRequiredType() + "' type.");
        logger.error("MethodArgumentTypeMismatchException: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    //dto Validation 실패
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String, String> params = new HashMap<String, String>();
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            params.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionCode.E00001, params);
        logger.error("MethodArgumentNotValidException: ", e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    //request body 에 대한 에러 발생 시. (ex. data type mismatch, deserialization error .. )
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable throwable = e.getMostSpecificCause();
        HashMap<String, Object> params = new HashMap<String, Object>();
        ExceptionResponse exceptionResponse;
        if(throwable instanceof InvalidFormatException exception) {
            logger.error("InvalidFormatException: ", exception);
            params.put("Value" , exception.getValue());
            params.put("Target Type", exception.getTargetType());
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, params);
        } else if(throwable instanceof MismatchedInputException exception) {
            logger.error("MismatchedInputException: ", exception);
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, exception.getOriginalMessage());
        } else if(throwable instanceof JsonParseException exception) {
            logger.error("JsonParseException: ", exception);
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, exception.getOriginalMessage());
        } else {
            logger.error("HttpMessageNotReadableException: ", e);
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00003, e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<?> jwtException(JwtException e) {
        ExceptionResponse exceptionResponse;
        if (e instanceof ExpiredJwtException exception){ // 토큰 만료
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00004, exception.getMessage());
            logger.error("ExpiredJwtException: ", exception);
        } else if (e instanceof UnsupportedJwtException exception){ // 토큰 형태 인증 불가
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, exception.getMessage());
            logger.error("UnsupportedJwtException: ", exception);
        } else if (e instanceof MalformedJwtException exception){ // 토큰 구조 불일치
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, exception.getMessage());
            logger.error("MalformedJwtException: ", exception);
        } else if (e instanceof SignatureException exception){ // jwt 서명실패
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, exception.getMessage());
            logger.error("SignatureException: ", exception);
        } else { // 그 외 모든 에러
            exceptionResponse = new ExceptionResponse(ExceptionCode.E00005, e.getMessage());
            logger.error("JwtException: ", e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionResponse);
    }

    //예상 불가능한 모든 익셉션
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> serverException(Exception e) {
        logger.error("Server Exception: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
