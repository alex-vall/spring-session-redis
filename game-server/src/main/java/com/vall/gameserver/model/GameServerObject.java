package com.vall.gameserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Vall on 31.10.2016.
 */
public class GameServerObject implements Serializable {

    private final Map<Integer, String> requestMap;
    private final Map<Integer, String> responseMap;

    private final List<Integer> resultList;

    private boolean isProcessingNow = false;

    private final String playerID;

    public GameServerObject(String playerID) {

        this.playerID = playerID;

        requestMap = new HashMap<>();
        responseMap = new HashMap<>();

        resultList = new ArrayList<Integer>();
    }

    public List<Integer> getResultList() {
        return resultList;
    }

    public Map<Integer, String> getRequestMap() {
        return requestMap;
    }

    public Map<Integer, String> getResponseMap() {
        return responseMap;
    }

    public boolean isProcessingNow() {
        return isProcessingNow;
    }

    public void setProcessingNow(boolean processingNow) {
        isProcessingNow = processingNow;
    }

    public String getPlayerID() {
        return playerID;
    }

    @Override
    public String toString() {
        return "GameServerObject{" +
                "requestMap=" + requestMap +
                ", responseMap=" + responseMap +
                ", resultList=" + resultList +
                ", isProcessingNow=" + isProcessingNow +
                ", playerID='" + playerID + '\'' +
                '}';
    }
}
