/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Player;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class Table {
    
    private List<Seat> seats;
    
    private int numSeats;
    
    public Table(){
        seats = null;
        numSeats = 0;
    }
    
    public int getPlayerCount(){
        return numSeats;
    }
    
    public void initStatus(){
        
    }
    
    public void seatPlayers(List<Player> plyrs){
        if(Objects.nonNull(plyrs) && !plyrs.isEmpty()){
            plyrs.stream().forEach((p) -> {
                seatPlayer(p);
            });
        }
    }
    
    public void seatPlayer(Player p){
        if(Objects.nonNull(p)){
            seats.add(new Seat(p));
            playerSeated();
        }
    }
    
    public Seat removePlayer(Player p){
        return seats.remove(seats.indexOf(new Seat(p)));
    }
    
    private void playerSeated(){
        numSeats++;
    }
}
