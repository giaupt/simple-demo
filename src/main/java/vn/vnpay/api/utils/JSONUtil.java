package vn.vnpay.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.vnpay.api.enums.MessageType;
import vn.vnpay.api.exception.CustomException;

public class JSONUtil{

    private static JSONUtil instance;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private JSONUtil () {}

    private static final Logger LOGGER = LogManager.getLogger(JSONUtil.class);

    public static JSONUtil getInstance() {
        if(instance == null) {
            synchronized (JSONUtil.class) {
                if(null == instance) {
                    instance = new JSONUtil();
                }
            }
        }
        return instance;
    }

    public String convertObjectToJSON(Object obj) throws CustomException {
        LOGGER.info(obj);
        try {
            return this.objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new CustomException(MessageType.SERVER_ERROR.getValue(), ex);
        }
    }
}
