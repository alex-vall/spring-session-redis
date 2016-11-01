package com.vall.gameserver.model;

import java.util.List;

/**
 *
 * Created by Vall on 31.10.2016.
 */
public class GameServerResponse {

    private final RequestStatus requestStatus;

    private final List<Integer> gameResult;

    public GameServerResponse(RequestStatus requestStatus, List<Integer> gameResult) {
        this.requestStatus = requestStatus;
        this.gameResult = gameResult;
    }

    public List<Integer> getGameResult() {
        return gameResult;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }
}
