package com.ecommerce.exception;

import com.ecommerce.common.response.Response;
import com.ecommerce.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Global Exception Handler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Business Exception */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("Business Exception [{}] {}", request.getRequestURI(), e.getMessage());
        return Response.fail(e);
    }

    /** Python Service Exception */
    @ExceptionHandler(PythonServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Response<?> handlePythonServiceException(PythonServiceException e, HttpServletRequest request) {
        log.error("Python Service Exception [{}] {}", request.getRequestURI(), e.getMessage());
        return Response.fail(e.getCode().toString(), e.getMessage());
    }

    /** Parameter Validation Failed (@Valid) */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Parameter validation failed";
        log.error("Parameter validation failed: {}", message);
        return Response.badRequest(message);
    }

    /** Parameter Binding Exception (@Validated) */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleBindException(BindException e) {
        FieldError fieldError = e.getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Parameter binding failed";
        log.error("Parameter binding failed: {}", message);
        return Response.badRequest(message);
    }

    /** Path Parameter Type Mismatch */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = String.format("Parameter %s type mismatch, expected type: %s",
                e.getName(), e.getRequiredType().getSimpleName());
        log.error("Path parameter type mismatch: {}", message);
        return Response.badRequest(message);
    }

    /** Constraint Violation Exception (@Validated @NotNull etc.) */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Parameter validation failed");
        log.error("Constraint validation failed: {}", message);
        return Response.badRequest(message);
    }

    /** Illegal Argument Exception */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Illegal argument: {}", e.getMessage());
        return Response.badRequest(e.getMessage());
    }

    /** Null Pointer Exception */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleNullPointerException(NullPointerException e) {
        log.error("Null pointer exception", e);
        return Response.serverError("Internal server error");
    }

    /** Fallback Exception */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleException(Exception e, HttpServletRequest request) {
        log.error("Unknown exception [{}] {}", request.getRequestURI(), e.getMessage(), e);
        return Response.serverError("Internal server error");
    }
}