/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl;

import com.mac.abstractrepository.entities.holdem.GameSetting;
import com.mac.holdempoker.app.PlayOrder;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Deck;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import com.mac.holdempoker.app.impl.SimpleBoard;
import com.mac.holdempoker.app.impl.SimpleDeck;
import com.mac.holdempoker.app.impl.SimplePlayOrder;
import com.mac.holdempoker.app.impl.SimplePlayer;
import com.mac.holdempoker.socket.utilities.JsonConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Represents the state of a poker game at an prior to or after an action is taken.
 * @author Mac
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GameState {
    
    private PlayOrder playOrder;
    
    private List<Pot> pots;
    
    private Board board;
    
    private GameSetting settings;
    
    public void setPlayOrder(PlayOrder po){
        this.playOrder = po;
    }
    
    public PlayOrder getPlayOrder(){
        return playOrder;
    }
    
    public void setPots(List<Pot> pots){
        this.pots = pots;
    }
    
    public List<Pot> getPots(){
        return pots;
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
    
    public Board getBoard(){
        return board;
    }
    
    public GameSetting getSettings(){
        return settings;
    }
    
    public void setSettings(GameSetting settings){
        this.settings = settings;
    }
    
    public static void main(String[] args) throws IOException{
        GameState gs = new GameState();
        
        Board b = new SimpleBoard();
        Deck d = new SimpleDeck();
        d.buildDeck();
        d.shuffleDeck();
        b.dealToBoard(d.drawNumCards(3));
        d.burnCard();
        b.dealToBoard(d.drawNextCard());
        d.burnCard();
        b.dealToBoard(d.drawNextCard());
        
        gs.setBoard(b);
        
        List<Player> plyrs = new ArrayList();
        plyrs.add(new SimplePlayer());
        plyrs.add(new SimplePlayer());
        plyrs.add(new SimplePlayer());
        plyrs.add(new SimplePlayer());
        
        PlayOrder po = new SimplePlayOrder(plyrs);
        po.order();
        
        gs.setPlayOrder(po);
        
        System.out.println(JsonConverter.toJsonString(gs));
    }
}
