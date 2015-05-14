/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.socket;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.Header;
import com.mac.holdempoker.app.hands.Game;
import com.mac.holdempoker.app.impl.SimplePlayer;
import java.util.Objects;
import javax.json.JsonObject;

/**
 *
 * @author Mac
 */
public class MessageHandler {

    private Game game;

    public MessageHandler(Game game){
        this.game = game;
    }
    
    public void setGame(Game game){
        this.game = game;
    }

    public void handle(Message msg) {
        String header = msg.getHeader();
        Header h = Header.getHeader(header);
        if (Objects.isNull(h)) {
            return;
        }
        switch (h) {
            case SIGN_IN: {
                handleSignIn(msg.getPayload());
            }
        }
    }

    private void handleSignIn(JsonObject payload) {
        try{
        SignIn signIn = new SignIn(payload);
        Player p = new SimplePlayer(signIn);
        game.addPlayer(p);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void handleAction(Action action) {

    }

    private void handleSignOut() {

    }

}
