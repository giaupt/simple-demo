package vn.vnpay.api.controller;

import vn.vnpay.api.config.BankConfig;
import vn.vnpay.api.dto.BankInformationDTO;
import vn.vnpay.api.service.impl.BankServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/bank")
public class BankController extends BaseController {

    @Autowired
    BankServiceImpl bankService;

    @Autowired
    BankConfig bankConfig;

    private static final Logger LOGGER = LogManager.getLogger(BankController.class);

    @PostMapping(path = "")
    public ResponseEntity<?> cacheBankInformation(@Valid @RequestBody BankInformationDTO bankInformationDTO) {
        LOGGER.info("Payment request body: {}", bankInformationDTO);
        try {
            return response(bankService.cacheBankInformation(bankInformationDTO));
        } catch (Exception ex) {
            LOGGER.error("Exception cache route: {}", ex);
            return response(error(ex));
        }
    }
}
