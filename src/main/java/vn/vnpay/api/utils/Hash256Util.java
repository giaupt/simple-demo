package vn.vnpay.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.vnpay.api.dto.BankInformationDTO;
import vn.vnpay.api.enums.MessageType;
import vn.vnpay.api.exception.CustomException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Hash256Util {
    private static Hash256Util instance;

    private static final Logger LOGGER = LogManager.getLogger(Hash256Util.class);

    private Hash256Util() {
    }

    public static Hash256Util getInstanceUsingDoubleLocking() {
        if (instance == null) {
            synchronized (Hash256Util.class) {
                if (null == instance) {
                    instance = new Hash256Util();
                }
            }
        }
        return instance;
    }

    private String getSHA256Hash(String data) throws CustomException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            String hashString = bytesToHex(hash);
            LOGGER.info("Hash string gen: {}", hashString);
            return hashString;
        } catch (Exception ex) {
            LOGGER.error("Hash error gen: {}", ex);
            throw new CustomException
                    (MessageType.SERVER_ERROR.getValue(), ex);
        }
    }

    private String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

    public Boolean checkSum(String data, BankInformationDTO model) throws CustomException {
        return model
                .getCheckSum()
                .equals(getSHA256Hash(data));
    }
}
