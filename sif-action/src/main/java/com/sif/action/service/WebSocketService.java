package com.sif.action.service;

import java.io.IOException;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-21 16:49
 **/
public interface WebSocketService {

    void sendToUserByUid(String uid, String message) throws IOException;
}
