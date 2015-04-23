/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket.utilities.encoders;

import com.mac.holdempoker.socket.utilities.Message;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Mac
 */
public class MessageEncoder implements Encoder.Text<Message>{

    @Override
    public String encode(Message message) throws EncodeException {
        return message.getPayload().toString();
    }
 
    @Override
    public void init(EndpointConfig config) {
        System.out.println("Init");
    }
 
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    
}
