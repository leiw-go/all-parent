package cn.leiwspider.common.exception;

import cn.leiwspider.common.rest.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理参数校验异常
     * @param req 请求对象
     * @param exception 异常类型
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response<String> ValidationExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError : errors) {
                FieldError fieldError = (FieldError) objectError;
                log.error("Data check --> object: {}; field: {}; errorMessage: {}",
                        fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                errorMsg.append(objectError.getDefaultMessage());
                errorMsg.append(",");
            }
            errorMsg = new StringBuilder(errorMsg.substring(0, errorMsg.length() - 1));
        }
        return Response.error(HttpStatus.BAD_REQUEST.value(), errorMsg.toString());
    }

    /**
     * 处理空指针的异常
     * @param req 请求对象
     * @param exception 异常类型
     */
    @ExceptionHandler(value =NullPointerException.class)
    public Response<String> NullPointerException(HttpServletRequest req, NullPointerException exception){
        log.error("发生空指针异常！请查找异常原因",exception);
        return Response.error(exception.getMessage());
    }



    /**
     * 处理其他异常
     * @param req 请求对象
     * @param exception 异常类型
     */
    @ExceptionHandler(value =Exception.class)
    public Response<String> exceptionHandler(HttpServletRequest req, Exception exception){
        log.error("发生未知异常！请查找异常原因",exception);
        return Response.error(exception.getMessage());
    }
}
