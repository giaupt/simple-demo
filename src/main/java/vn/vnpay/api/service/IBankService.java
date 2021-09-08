package vn.vnpay.api.service;

import vn.vnpay.api.dto.BankInformationDTO;
import vn.vnpay.api.model.ResultEntity;
import vn.vnpay.api.exception.CustomException;

public interface IBankService {
    ResultEntity cacheBankInformation(BankInformationDTO bankInformationDTO) throws CustomException;
}
