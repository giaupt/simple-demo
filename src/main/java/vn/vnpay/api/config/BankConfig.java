package vn.vnpay.api.config;

import vn.vnpay.api.model.BankEntity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "banks")
public class BankConfig {
    private List<BankEntity> banks = new ArrayList<>();
}
