package vn.vnpay.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankEntity {
    private String bankCode;
    private String privateKey;
}
