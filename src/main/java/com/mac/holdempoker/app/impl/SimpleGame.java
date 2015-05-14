/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.GameDealer;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.hands.Game;
import com.mac.holdempoker.game.impl.GameState;
import com.mac.holdempoker.socket.JsonConverter;
import java.io.IOException;
import java.time.temporal.Temporal;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Represents the state of a poker game at an prior to or after an action is taken.
 * @author Mac
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleGame implements Game{
        
    private GameSetting settings;
    
    private final GameDealer dealer;
        
    public SimpleGame() {
        dealer = new SimpleGameDealer();
    }
    
    public GameSetting getSettings(){
        return settings;
    }
    
    public void setSettings(GameSetting settings){
        this.settings = settings;
    }
    
    @Override
    public String getGameId() {
//        return this.settings.getGameid();
        return null;
    }

    @Override
    public Temporal getGameStartTime() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public GameSetting getGameSetting() {
        return settings;
    }
    
    @Override
    public void start() {
//        if(Objects.nonNull(players))
//        dealer.setPlayers(players);
//        dealer.dealAround();
    }

    @Override
    public void addPlayer(Player p) {
//        if(Objects.nonNull(settings)){
            dealer.addPlayer(p);
//        }
    }
    
    @Override
    public void actionPerformed(Action action) {
        dealer.actionPerformed(action);
    }
    
    public static void main(String[] args) throws IOException{
        SimpleGame sg = new SimpleGame();
                        
        sg.addPlayer(new SimplePlayer());
        sg.addPlayer(new SimplePlayer());
        sg.addPlayer(new SimplePlayer());
        sg.addPlayer(new SimplePlayer());
        
        sg.start();
        
        System.out.println(JsonConverter.toJsonString(sg));
    }

    @Override
    public GameState getGameState() {
        return dealer.getGameState();
    }

    
}
