package vn.vnpay.api.enums;

interface Option {

    Integer getValue();

    String getMessage();
}

public enum MessageType implements Option {
    SUCCESS(0, "Success"),
    BANK_CODE_NOT_EXITS(2, "Bank code is not exist!"),
    CHECK_SUM_NOT_VALID(3, "Check sum is not valid"),
    SERVER_ERROR(4, "Internal server error!");

    private Integer value;
    private String message;

    MessageType(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
