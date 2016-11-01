package com.vall.gameserver.controller;

import com.vall.gameserver.model.GameServerObject;
import com.vall.gameserver.model.GameServerResponse;
import com.vall.gameserver.model.RequestStatus;
import com.vall.gameserver.service.GameRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * Created by Vall on 31.10.2016.
 */

@RestController
public class GameRequestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String SESSION_ATTRIBUTE_NAME = "gameserver";

    private GameRequestService gameRequestService;

    @Autowired
    public GameRequestController(GameRequestService gameRequestService) {
        this.gameRequestService = gameRequestService;
    }

    @RequestMapping(value = "/doPlay", method = RequestMethod.GET)
    public
    @ResponseBody
    GameServerResponse doPlay(@RequestParam(value = "counter") int counter,
                              @RequestParam(value = "spinCount") int spinCount,
                              HttpServletRequest request) {

        log.debug(request.toString());

        log.debug("counter = " + counter);
        log.debug("spinCount = " + spinCount);

        HttpSession httpSession = request.getSession();

        GameServerObject gameServerObject = (GameServerObject) httpSession.getAttribute(SESSION_ATTRIBUTE_NAME);

        if (gameServerObject == null) {
            gameServerObject = buildGameServerObject(httpSession.getId());
            httpSession.setAttribute(SESSION_ATTRIBUTE_NAME, gameServerObject);
        }

        if (gameServerObject.isProcessingNow()) {
            return new GameServerResponse(RequestStatus.pnow, null);
        }

        gameServerObject.setProcessingNow(true);
        httpSession.setAttribute(SESSION_ATTRIBUTE_NAME, gameServerObject);


        GameServerResponse gameResponse = gameRequestService.execute(gameServerObject, counter, spinCount);

        gameServerObject.setProcessingNow(false);

        return gameResponse;
    }

    private GameServerObject buildGameServerObject(String sessionID) {
        return new GameServerObject(sessionID);
    }


}
