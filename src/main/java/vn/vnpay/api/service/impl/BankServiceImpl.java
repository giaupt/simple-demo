package vn.vnpay.api.service.impl;

import vn.vnpay.api.config.BankConfig;
import vn.vnpay.api.dto.BankInformationDTO;
import vn.vnpay.api.enums.MessageType;
import vn.vnpay.api.model.BankEntity;
import vn.vnpay.api.model.ResultEntity;
import vn.vnpay.api.service.BaseService;
import vn.vnpay.api.service.IBankService;
import vn.vnpay.api.exception.CustomException;
import vn.vnpay.api.utils.Hash256Util;
import vn.vnpay.api.utils.JSONUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends BaseService implements IBankService {

    @Autowired
    private BankConfig bankConfig;

    @Autowired
    private BankRedisServiceImpl bankRedisService;

    private static final Logger LOGGER = LogManager.getLogger(BankServiceImpl.class);

    @Override
    public ResultEntity cacheBankInformation(BankInformationDTO bankInformationDTO) throws CustomException {
        LOGGER.info("Bank information : {}", bankInformationDTO);
        if (!this.isBankExist(bankInformationDTO.getBankCode()))
            throw getCustomException
                    (MessageType.BANK_CODE_NOT_EXITS.getValue(), MessageType.BANK_CODE_NOT_EXITS.getMessage());

        BankEntity bankEntity = this.getBankEntity(bankInformationDTO.getBankCode());

        Boolean isMatchCheckSum = Hash256Util.getInstanceUsingDoubleLocking()
                .checkSum(generateStringForCheckSum(bankInformationDTO, bankEntity), bankInformationDTO);

        if (!isMatchCheckSum)
            throw getCustomException
                    (MessageType.CHECK_SUM_NOT_VALID.getValue(), MessageType.CHECK_SUM_NOT_VALID.getMessage());

        String jsonObj = JSONUtil.getInstance().convertObjectToJSON(bankInformationDTO);

        if (!bankRedisService.exists(bankInformationDTO.getBankCode())) {
            Long result = bankRedisService.
                    hset(bankInformationDTO.getBankCode(), bankInformationDTO.getTokenKey(), jsonObj);
            if (-1L != result) {
                return new ResultEntity(
                        MessageType.SUCCESS.getValue(),
                        MessageType.SUCCESS.getMessage(),
                        jsonObj
                );
            }
            return new ResultEntity(
                    MessageType.SERVER_ERROR.getValue(),
                    MessageType.SERVER_ERROR.getMessage(),
                    ""
            );
        }
        return new ResultEntity(
                MessageType.SUCCESS.getValue(),
                MessageType.SUCCESS.getMessage(),
                jsonObj
        );
    }

    private Boolean isBankExist(String bankCode) {
        LOGGER.info("List banks: {}", bankConfig.getBanks());
        return bankConfig
                .getBanks()
                .stream()
                .anyMatch(bankEntity -> bankEntity.getBankCode().equals(bankCode));
    }

    private BankEntity getBankEntity(String bankCode) {
        return bankConfig
                .getBanks()
                .stream()
                .filter(bankEntity1 -> bankEntity1.getBankCode().equals(bankCode))
                .findAny()
                .orElse(null);
    }

    private String generateStringForCheckSum(BankInformationDTO bankInformationDTO, BankEntity bankEntity) {
        return new StringBuilder()
                .append(bankInformationDTO.getMobile())
                .append(bankInformationDTO.getBankCode())
                .append(bankInformationDTO.getAccountNo())
                .append(bankInformationDTO.getPayDate())
                .append(bankInformationDTO.getDebitAmount())
                .append(bankInformationDTO.getRespCode())
                .append(bankInformationDTO.getTraceTransfer())
                .append(bankInformationDTO.getMessageType())
                .append(bankEntity.getPrivateKey())
                .toString();
    }
}
