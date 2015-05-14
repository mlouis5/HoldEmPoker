/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket;

import com.mac.holdempoker.app.enums.Header;
import com.mac.holdempoker.app.hands.Game;
import com.mac.holdempoker.app.impl.SimpleGame;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;
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
    
    @Autowired
    private SocketManager socketManager;
    private MessageHandler handler;        
    private Game simpleGame;
    
    public HoldemEndpoint(){
        super(new InetSocketAddress(PORT));
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
        simpleGame = new SimpleGame();
        simpleGame.start();
        handler = new MessageHandler(simpleGame);
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake ch) {
        System.out.println(ws);
        
    }

    @Override
    public void onClose(WebSocket ws, int i, String string, boolean bln) {
        System.out.println("i: " + i);
        System.out.println("string: " + string);
        System.out.println("boolean: " + bln);
        
        socketManager.removeConnection(ws);
    }

    @Override
    public void onMessage(WebSocket ws, String string) {
        try {
            Message msg = decoder.decode(string);
            Header h = Header.getHeader(msg.getHeader());
            if(Objects.isNull(h)){
                return;
            }else if(h == Header.SIGN_IN){
                boolean added = socketManager.addConnection(msg.getPid(), ws);
                if(!added){
                    System.out.println("Not added");
                    return;
                }
            }
            System.out.println(msg.getHeader());
            System.out.println(msg.getPayload()); 
            
            handler.handle(msg);
            String jo1 = JsonConverter.toJsonString(simpleGame.getGameState());
            System.out.println(jo1);
//            ws.send(jo1);
            socketManager.sendToAllOpenConns(jo1);
        } catch (DecodeException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(HoldemEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            java.util.logging.Logger.getLogger(HoldemEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onError(WebSocket ws, Exception excptn) {
        System.out.println("ERROR");
        System.out.println(excptn);
        System.out.println(excptn.getLocalizedMessage());
        System.out.println(excptn.getCause());
        System.out.println(excptn.getMessage());
        System.out.println(ws);
    }
 
}
