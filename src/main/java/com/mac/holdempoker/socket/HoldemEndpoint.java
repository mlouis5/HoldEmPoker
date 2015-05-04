/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket;

import java.net.InetSocketAddress;
import java.util.logging.Level;
import javax.websocket.DecodeException;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
public class HoldemEndpoint extends WebSocketServer {

    private static final int PORT = 9595;    
    private final Logger logger;
    
    private @Autowired MessageDecoder decoder;
    private @Autowired MessageEncoder encoder;
    
    public HoldemEndpoint(){
        super(new InetSocketAddress(PORT));
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake ch) {
        System.out.println(ws);
    }

    @Override
    public void onClose(WebSocket ws, int i, String string, boolean bln) {
        System.out.println(ws);
    }

    @Override
    public void onMessage(WebSocket ws, String string) {
        try {
            Message msg = decoder.decode(string);            
            System.out.println(msg.getHeader());
            System.out.println(msg.getPayload());            
        } catch (DecodeException ex) {
            java.util.logging.Logger.getLogger(HoldemEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onError(WebSocket ws, Exception excptn) {
        System.out.println(ws);
    }
 
}
