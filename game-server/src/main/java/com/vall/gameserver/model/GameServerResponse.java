package com.vall.gameserver.model;

import java.util.List;

/**
 *
 * Created by Vall on 31.10.2016.
 */
public class GameServerResponse {

    private final List<Integer> gameResult;

    public GameServerResponse(List<Integer> gameResult) {
        this.gameResult = gameResult;
    }

    public List<Integer> getGameResult() {
        return gameResult;
    }
}
