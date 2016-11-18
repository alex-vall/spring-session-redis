package com.vall.gameserver.controller;

import com.vall.gameserver.config.RedissonClientFactory;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by Vall on 31.10.2016.
 */
@RestController
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RedissonClientFactory redissonClientFactory;


    @Autowired
    public HelloController(RedissonClientFactory redissonClientFactory) {
        this.redissonClientFactory = redissonClientFactory;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/doTest")
    public String doTest(HttpServletRequest request) {
        final String id = request.getSession().getId();


        log.debug("/doTest called with session: " + id);

//        RedissonClient redissonClient = redissonClientFactory.createRedissonClient();
        RedissonClient redissonClient = redissonClientFactory.getRedissonClient();

        RLock rLock = redissonClient.getLock(id);

        boolean bRes = false;
        try {
            bRes = rLock.tryLock(0, 30, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            log.error(e.getMessage());
        }

        String response = bRes ? "Lock ok" : "Lock fail";

        log.debug(response);

        return response;
    }

}
