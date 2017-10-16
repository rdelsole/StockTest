package nl.payconiq.controller;

import javassist.NotFoundException;
import javax.servlet.http.HttpServletRequest;

import nl.payconiq.response.ResultErrorVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Controller handler all error occurred in application
 * @author Rafael Del Sole
 */
@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    ResultErrorVO notfoundHandler(NotFoundException ex) {
        LOGGER.error("m=notfoundHandler ex={}",ex.getMessage(),ex);
        return new ResultErrorVO(HttpStatus.NOT_FOUND,ex.getMessage(),null);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResultErrorVO argumentNotValid(MethodArgumentNotValidException ex) {
        LOGGER.error("m=argumentNotValid ex={}",ex.getMessage(),ex);
        return new ResultErrorVO(HttpStatus.BAD_REQUEST,"An fieldError has occurred",ex.getBindingResult().getFieldErrors());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ResultErrorVO genericError(Exception ex) {
        LOGGER.error("m=genericError ex={}",ex.getMessage(),ex);
        return new ResultErrorVO(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),null);
    }

}
