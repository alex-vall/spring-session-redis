package com.vall.gameserver.controller;

import com.vall.gameserver.model.GameServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vall on 31.10.2016.
 */

@RestController
public class SessionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/doPlay", method = RequestMethod.GET)
    public
    @ResponseBody
    GameServerResponse doPlay(@RequestParam(value = "counter") int counter,
                              @RequestParam(value = "spinCount") int spinCount,
                              HttpServletRequest request) {
        List<Integer> resultList = new ArrayList<Integer>();
        resultList.add(1);
        resultList.add(2);
        resultList.add(3);
        resultList.add(4);
        resultList.add(5);

        log.debug(request.toString());

        log.debug("counter = " + counter);
        log.debug("spinCount = " + spinCount);

        return new GameServerResponse(resultList);
    }


}
