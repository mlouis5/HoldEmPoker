/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.socket.SignIn;
import com.mac.holdempoker.socket.Message;

/**
 *
 * @author Mac
 */
public class MessageHandler {

    public void handle(Message msg) {
        String header = msg.getHeader();
        switch(header){
            case "sign in":{
                SignIn signIn = new SignIn(msg.getPayload());
            }
        }
    }
    
    private void handleSignIn(SignIn signIn){
        
    }
    
    private void handleAction(Action action){
        
    }
    
    private void handleSignOut(){
        
    }
    
}
