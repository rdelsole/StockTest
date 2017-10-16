package nl.payconiq.controller;

import javassist.NotFoundException;
import javax.servlet.http.HttpServletRequest;

import nl.payconiq.response.ResultErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    ResultErrorVO notfoundHandler(HttpServletRequest req, NotFoundException ex) {

        return new ResultErrorVO(HttpStatus.NOT_FOUND,ex.getMessage(),null);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResultErrorVO argumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {

        return new ResultErrorVO(HttpStatus.BAD_REQUEST,"An fieldError has occurred",ex.getBindingResult().getFieldErrors());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ResultErrorVO genericError(HttpServletRequest req, Exception ex) {

        return new ResultErrorVO(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),null);
    }

}
