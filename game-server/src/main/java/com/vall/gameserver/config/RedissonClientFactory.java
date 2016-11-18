package com.vall.gameserver.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by alex.volosatov
 * Creation date 18.11.2016.
 */

@Component
public class RedissonClientFactory {

    private ApplicationContext applicationContext;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Config config;
    private RedissonClient redissonClient;

//    @Value("${test.host}")
//    private String redisHost;

    @Autowired
    public RedissonClientFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

    }

    @PostConstruct
    public void initRedisonClient() {
        Resource resource =
                applicationContext.getResource("classpath:redisson-config.yml");

        try {
            InputStream is = resource.getInputStream();

            config = Config.fromYAML(is);
            config.useSingleServer().setAddress("172.16.10.7:6379");

            redissonClient = Redisson.create(config);


        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @PreDestroy
    public void shutdown() {
        redissonClient.shutdown();
    }


    public RedissonClient createRedissonClient() {
        return Redisson.create(config);
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

}
