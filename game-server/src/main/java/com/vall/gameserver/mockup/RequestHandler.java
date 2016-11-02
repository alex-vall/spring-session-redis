package com.vall.gameserver.mockup;

import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vall on 02.11.2016.
 */
public class RequestHandler {


    public String handleRequest(HttpServletRequest request) {

        BaseGameServer baseGameServer = (BaseGameServer) request.getSession().getAttribute("gs");
        String result = "";

        if (baseGameServer == null) {
            return "empty";
        }

        try {
            if (!baseGameServer.trySetProcessingNow()) {
                baseGameServer = null;
                return "pnow";
            }

            result = baseGameServer.execute();
        } finally {
            if (baseGameServer != null) {
                baseGameServer.releaseProcessingNow();
            }
        }

        return result;
    }
}
