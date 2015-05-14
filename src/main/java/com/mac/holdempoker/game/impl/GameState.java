/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.game.impl;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
//import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MacDerson
 */
public class GameState {
    private Board board;
    private Set<Player> players;
    private Pot pot;
    
    public Board getBoard(){
        return board;
    }
    
    public Set<Player> getPlayers(){
        return players;
    }
    
    public Pot getPot(){
        return pot;
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
    
    public void setPlayers(Set<Player> players){
        this.players = players;
    }
    
    public void setPot(Pot pot){
        this.pot = pot;
    }
    
//    public String[] getFieldNames(){
//        Field[] fields = this.getClass().getDeclaredFields();
//        String[] names = new String[fields.length];
//        int index = 0;
//        for(Field f : fields){
//            f.setAccessible(true);
//            names[index++] = f.getName();
//        }
//        return names;
//    }
}
