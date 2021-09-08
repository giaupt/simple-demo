package vn.vnpay.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankInformationDTO implements Serializable {

    @NotNull(message = "Token key không được để trống!")
    @NotBlank(message = "Token key không được để trống!")
    private String tokenKey;

    @NotNull(message = "ApiId không được để trống!")
    @NotBlank(message = "ApiId không được để trống!")
    private String apiID;

    @NotNull(message = "Số điện thoại không được để trống!")
    @NotBlank(message = "Số điện thoại không được để trống!")
    private String mobile;

    @NotNull(message = "Mã ngân hàng không được để trống!")
    @NotBlank(message = "Mã ngân hàng không được để trống!")
    private String bankCode;

    @NotNull(message = "Số tài khoản không được để trống!")
    @NotBlank(message = "Số tài khoản không được để trống!")
    private String accountNo;

    @NotNull(message = "Ngày thanh toán không được để trống!")
    @NotBlank(message = "Ngày thanh toán không được để trống!")
    private String payDate;

    @NotNull(message = "Dữ liệu bổ sung không được để trống!")
    private String additionalData;

    @NotNull(message = "Số tiền ghi nợ không được để trống!")
    private String debitAmount;

    @NotNull(message = "Mã phản hồi không được để trống!")
    @NotBlank(message = "Mã phản hồi không được để trống!")
    private String respCode;

    @NotNull(message = "Thông tin phản hồi không được để trống!")
    @NotBlank(message = "Thông tin phản hồi không được để trống!")
    private String respDesc;

    @NotNull(message = "TraceTransfer không được để trống!")
    @NotBlank(message = "TraceTransfer không được để trống!")
    private String traceTransfer;

    @NotNull(message = "Loại tin nhắn không được để trống!")
    @NotBlank(message = "Loại tin nhắn không được để trống!")
    private String messageType;

    @NotNull(message = "Checksum không được để trống!")
    @NotBlank(message = "Checksum không được để trống!")
    private String checkSum;

    @NotNull(message = "Order code không được để trống!")
    @NotBlank(message = "Order code không được để trống!")
    private String orderCode;

    @NotNull(message = "Username không được để trống!")
    @NotBlank(message = "Username không được để trống!")
    private String userName;

    @NotNull(message = "Real amount không được để trống!")
    @NotBlank(message = "Real amount không được để trống!")
    private String realAmount;

    @NotNull(message = "Promotion code không được để trống!")
    @NotBlank(message = "Promotion code không được để trống!")
    private String promotionCode;
}
