package com.vall.gameserver.mockup;

/**
 *
 * Created by Vall on 02.11.2016.
 */
public class BooleanBaseGameServer implements BaseGameServer {

    private boolean processingNow;


    @Override
    public boolean trySetProcessingNow() {
        boolean result = (!processingNow);
        processingNow = true;
        return result;
    }

    @Override
    public void releaseProcessingNow() {
        processingNow = false;
    }

    @Override
    public String execute() {
        throw new IllegalStateException("real execute called");
    }
}
