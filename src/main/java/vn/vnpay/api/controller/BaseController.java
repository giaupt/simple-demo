package vn.vnpay.api.controller;

import vn.vnpay.api.model.ResultEntity;
import vn.vnpay.api.result.IResultError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin("*")
public class BaseController implements IResultError {

    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    /**
     * @param entity object muon chuyen sang json
     * @return {"code": 0, "message": "no message", data: {}}
     */
    protected ResponseEntity<?> response(ResultEntity entity) {
        LOGGER.info("Response entity: {}", entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
