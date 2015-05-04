/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.PlayOrder;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.GameDealer;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import com.mac.holdempoker.app.hands.Game;
import com.mac.holdempoker.socket.JsonConverter;
import java.io.IOException;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Objects;
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
    
    private List<Pot> pots;
    
    private List<Player> players;
    
    private GameSetting settings;
    
    private final GameDealer dealer;
        
    public SimpleGame(GameSetting setting) {
        this.settings = setting;
        dealer = new SimpleGameDealer(settings);
    }
    
    public PlayOrder getPlayOrder(){
        return dealer.getPlayOrder();
    }
    
    public void setPots(List<Pot> pots){
        this.pots = pots;
    }
    
    public List<Pot> getPots(){
        return pots;
    }
    
    public Board getBoard(){
        return dealer.getBoard();
    }
    
    public GameSetting getSettings(){
        return settings;
    }
    
    public void setSettings(GameSetting settings){
        this.settings = settings;
    }
    
    @Override
    public String getGameId() {
        return this.settings.getGameid();
    }

    @Override
    public Temporal getGameStartTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameSetting getGameSetting() {
        return settings;
    }
    
    @Override
    public void start() {
        dealer.setPlayers(players);
        dealer.setBoard(new SimpleBoard());
        dealer.dealAround();
    }

    @Override
    public void addPlayer(Player p) {
        if(Objects.nonNull(settings)){
            if(players.size() < settings.getMaxPlayer()){
                players.add(p);
            }
        }
    }
    
    @Override
    public void actionPerformed(Action action) {
        dealer.actionPerformed(action);
    }
    
    public static void main(String[] args) throws IOException{
        SimpleGame sg = new SimpleGame(new GameSetting());
                        
        sg.addPlayer(new SimplePlayer());
        sg.addPlayer(new SimplePlayer());
        sg.addPlayer(new SimplePlayer());
        sg.addPlayer(new SimplePlayer());
        
        sg.start();
        
        System.out.println(JsonConverter.toJsonString(sg));
    }

    
}
