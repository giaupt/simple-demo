package vn.vnpay.api.service.impl;

import vn.vnpay.api.service.BaseService;
import vn.vnpay.api.service.IBankRedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class BankRedisServiceImpl extends BaseService implements IBankRedisService {

    @Autowired
    private JedisPool jedisPool;

    private static final Logger LOGGER = LogManager.getLogger(BankRedisServiceImpl.class);

    @Override
    public Long hset(String key, String field, String value) {
        LOGGER.info("Hset input: key={}, field={}, value={}", key, field, value);
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(key, field, value);
        } catch (Exception ex) {
            LOGGER.error("Exception hset redis: {}", ex);
            return -1L;
        }
    }

    @Override
    public Boolean exists(String key) {
        LOGGER.info("Key redis check exist: {}", key);
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        } catch (Exception ex) {
            LOGGER.error("Exception check exist redis: {}", ex);
            return false;
        }
    }
}
