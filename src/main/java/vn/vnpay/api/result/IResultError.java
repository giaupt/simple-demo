package vn.vnpay.api.result;

import vn.vnpay.api.enums.MessageType;
import vn.vnpay.api.model.ResultEntity;
import vn.vnpay.api.exception.CustomException;

public interface IResultError {
    default ResultEntity error(Exception ex) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setMessage(ex.getMessage());
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            resultEntity.setCode(customException.getCode());
            resultEntity.setData(customException.getData());
        } else {
            resultEntity.setCode(MessageType.SERVER_ERROR.getValue());
            resultEntity.setMessage(MessageType.SERVER_ERROR.getMessage());
        }
        return resultEntity;
    }
}
