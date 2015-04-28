/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket;

import com.mac.holdempoker.socket.utilities.Message;
import com.mac.holdempoker.socket.utilities.decoders.MessageDecoder;
import com.mac.holdempoker.socket.utilities.encoders.MessageEncoder;
import java.io.IOException;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mac
 */
@ServerEndpoint(value = "/holdem", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class HoldemEndpoint {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }
 
    @OnMessage
    public String onMessage(Message message, Session session) {
        String header = message.getHeader();
        switch (header) {
        case "sing in":
//            try {
//                session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Game ended"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            break;
        }
//        return message;
        return null;
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}
