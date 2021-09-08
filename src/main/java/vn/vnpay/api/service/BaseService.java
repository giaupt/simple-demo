package vn.vnpay.api.service;

import vn.vnpay.api.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected CustomException getCustomException(int code, String message, Object... objects) {
        return new CustomException(code, String.format(message, objects));
    }
}