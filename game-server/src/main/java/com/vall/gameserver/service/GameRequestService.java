package com.vall.gameserver.service;

import com.vall.gameserver.model.GameServerObject;
import com.vall.gameserver.model.GameServerResponse;

/**
 *
 * Created by Vall on 31.10.2016.
 */
public interface GameRequestService {

    GameServerResponse execute(GameServerObject gameServerObject, int counter, int spinCount);

}
