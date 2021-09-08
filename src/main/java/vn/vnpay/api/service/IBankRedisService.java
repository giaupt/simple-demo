package vn.vnpay.api.service;

public interface IBankRedisService {

    Long hset(String key, String field, String value);

    Boolean exists(String key);
}
