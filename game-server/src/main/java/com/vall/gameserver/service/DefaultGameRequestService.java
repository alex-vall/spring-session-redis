package com.vall.gameserver.service;

import com.vall.gameserver.model.GameServerObject;
import com.vall.gameserver.model.GameServerResponse;
import com.vall.gameserver.model.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Vall on 01.11.2016.
 */

@Service("gameRequestService")
public class DefaultGameRequestService implements GameRequestService {

    @Override
    public GameServerResponse execute(GameServerObject gameServerObject, int counter, int spinCount) {
        List<Integer> resultList = new ArrayList<Integer>();
        resultList.add(1);
        resultList.add(2);
        resultList.add(3);
        resultList.add(4);
        resultList.add(5);

        return new GameServerResponse(RequestStatus.ok, resultList);
    }

}
