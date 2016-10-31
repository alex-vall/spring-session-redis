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

    private final Map<String, String> requestMap;
    private final Map<String, String> responseMap;

    private final List<Integer> resultList;

    public GameServerObject() {
        requestMap = new HashMap<String, String>();
        responseMap = new HashMap<String, String>();

        resultList = new ArrayList<Integer>();
    }

    public List<Integer> getResultList() {
        return resultList;
    }

    public Map<String, String> getRequestMap() {
        return requestMap;
    }

    public Map<String, String> getResponseMap() {
        return responseMap;
    }

    @Override
    public String toString() {
        return "GameServerObject{" +
                "requestMap=" + requestMap +
                ", responseMap=" + responseMap +
                ", resultList=" + resultList +
                '}';
    }
}
