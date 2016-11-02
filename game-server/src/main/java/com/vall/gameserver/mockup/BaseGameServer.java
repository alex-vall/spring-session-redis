package com.vall.gameserver.mockup;

/**
 *
 * Created by Vall on 02.11.2016.
 */
public interface BaseGameServer {

    boolean trySetProcessingNow();

    void releaseProcessingNow();

    String execute();

}
