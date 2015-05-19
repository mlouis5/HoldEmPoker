/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.enums.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class Seat {

    private Player p;
    private List<Status> status;

    public Seat(Player p) {
        if (Objects.isNull(p)) {
            throw new NullPointerException("Player may not be null");
        }
        this.p = p;
        status = new ArrayList();
    }
    
    public Player getPlayer(){
        return p;
    }
    
    public boolean containsPlayer(Player p){
        return this.p.equals(p);
    }
    
    public void addStatus(Status... status){
        if(Objects.nonNull(status) && status.length > 0){
            this.status.addAll(Arrays.asList(status));
        }
    }
    
    public List<Status> getStatus(){
        return status;
    }
    
    public boolean isEmpty(){
        return Objects.isNull(p);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.p);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Seat other = (Seat) obj;
        return Objects.equals(this.p, other.p);
    }
    
    
}
