/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket;

import org.glassfish.tyrus.server.Server;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
//@Component
public class PokerServer extends Server{
    
    public PokerServer(){
        super("localhost", 9595, "/poker", null, HoldemEndpoint.class);
    }
}
