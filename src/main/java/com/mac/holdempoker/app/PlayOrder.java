/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import java.util.List;

/**
 *
 * @author MacDerson
 */
public interface PlayOrder {
    
    public Player getButton(); 
    
    public Player getBigBlind();
    
    public Player getSmallBlind();
    
    public Player getNextToAct();
    
    public void order(List<Player> players);
    
    public void dealToNext(Card card);
    
    public List<Player> getPlayers();
    
    public void clear();
    
    public boolean canPlayerAct(Player p);
}
