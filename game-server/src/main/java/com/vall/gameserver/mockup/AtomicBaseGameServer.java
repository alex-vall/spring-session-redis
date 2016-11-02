package com.vall.gameserver.mockup;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * Created by Vall on 02.11.2016.
 */
public class AtomicBaseGameServer implements BaseGameServer {

    private AtomicBoolean processingNow = new AtomicBoolean(false);


    @Override
    public boolean trySetProcessingNow() {
        return processingNow.compareAndSet(false, true);
    }

    @Override
    public void releaseProcessingNow() {
        processingNow.set(false);
    }

    @Override
    public String execute() {
        throw new IllegalStateException("real execute called");
    }
}
