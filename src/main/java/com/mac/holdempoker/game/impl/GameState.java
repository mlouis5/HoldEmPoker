/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import java.util.List;

/**
 *
 * @author MacDerson
 */
public class GameState {
    private Board board;
    private List<Player> players;
    private Pot pot;
    
    public Board getBoard(){
        return board;
    }
    
    public List<Player> getPlayers(){
        return players;
    }
    
    public Pot getPot(){
        return pot;
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
    
    public void setPlayers(List<Player> players){
        this.players = players;
    }
    
    public void setPot(Pot pot){
        this.pot = pot;
    }
}
