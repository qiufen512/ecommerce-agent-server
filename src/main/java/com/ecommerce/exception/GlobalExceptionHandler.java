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
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 业务异常 */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("业务异常 [{}] {}", request.getRequestURI(), e.getMessage());
        return Response.fail(e);
    }

    /** Python 服务异常 */
    @ExceptionHandler(PythonServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Response<?> handlePythonServiceException(PythonServiceException e, HttpServletRequest request) {
        log.error("Python服务异常 [{}] {}", request.getRequestURI(), e.getMessage());
        return Response.fail(e.getCode().toString(), e.getMessage());
    }

    /** 参数校验失败（@Valid） */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "参数校验失败";
        log.error("参数校验失败: {}", message);
        return Response.badRequest(message);
    }

    /** 参数绑定异常（@Validated） */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleBindException(BindException e) {
        FieldError fieldError = e.getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "参数绑定失败";
        log.error("参数绑定失败: {}", message);
        return Response.badRequest(message);
    }

    /** 路径参数类型不匹配 */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = String.format("参数 %s 类型不匹配，期望类型: %s",
                e.getName(), e.getRequiredType().getSimpleName());
        log.error("路径参数类型不匹配: {}", message);
        return Response.badRequest(message);
    }

    /** 约束校验异常（@Validated 的 @NotNull 等） */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("参数校验失败");
        log.error("约束校验失败: {}", message);
        return Response.badRequest(message);
    }

    /** 非法参数异常 */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数: {}", e.getMessage());
        return Response.badRequest(e.getMessage());
    }

    /** 空指针异常 */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleNullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return Response.serverError("服务器内部错误");
    }

    /** 兜底异常 */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> handleException(Exception e, HttpServletRequest request) {
        log.error("未知异常 [{}] {}", request.getRequestURI(), e.getMessage(), e);
        return Response.serverError("服务器内部错误");
    }
}