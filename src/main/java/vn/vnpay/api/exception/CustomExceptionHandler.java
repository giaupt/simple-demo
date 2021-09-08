package vn.vnpay.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity<>(processFieldErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        Error error = new Error(1, "Validation error");
        List<Result> listErr = new ArrayList<>();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            Result result = new Result(fieldError.getField(), fieldError.getDefaultMessage());
            result.setMessage(fieldError.getDefaultMessage());
            listErr.add(result);
        }
        error.setData(listErr);
        return error;
    }

    static class Error {
        private final Integer code;
        private final String message;

        private List<Result> data = new ArrayList<>();

        Error(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public List<Result> getData() {
            return data;
        }

        public void setData(List<Result> data) {
            this.data = data;
        }
    }
}
