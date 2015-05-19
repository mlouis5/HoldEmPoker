/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.Status;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class Table {
    
    private List<Seat> seats;
    private int actorIndex;
    
    public Table(){
        seats = null;
    }
    
    public int getPlayerCount(){
        return seats.size();
    }
    
    private void initStatus(){
        if(seats.size() == 2){
            seats.get(0).addStatus(Status.BUTTON, Status.SMALL_BLIND, Status.ACTOR);
        }else{
            int i,c;
//            int 
            for(i = 0, c = 4; i < c; i++){
//                seat
            }
        }
    }
    
    public void seatPlayers(List<Player> plyrs){        
        if(Objects.nonNull(plyrs) && !plyrs.isEmpty()){            
            plyrs.stream().filter(p -> Objects.nonNull(p)).forEach((p) -> {
                seatPlayer(p);
            });
            initStatus();
        }
    }
    
    public void seatPlayer(Player p){
        if(Objects.nonNull(p)){
            seats.add(new Seat(p));
        }
    }
    
    public Seat removePlayer(Player p){
        int indexOf = seats.indexOf(new Seat(p));
        if(indexOf >= 0){
            return seats.remove(indexOf);
        }
        return new Seat(null);//returns an empty seat
    }
}
