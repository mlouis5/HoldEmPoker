/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.PotName;
import java.util.List;

/**
 *
 * @author MacDerson
 */
public interface Round {
    void setPlayersInRound(List<Player> playersInRound);
    
    void removePlayerFromRound(Player p);
    
    void addPotToRound(PotName potName, Pot pot);
    
    int numPotsInRound();
    
    int numPlayersInRound();
    
    List<Player> getPlayersInRound();
    
    List<Pot> getPotsInRound();
    
    boolean getHasDefaultWinner();
    
    Player getDefaultWinners();
}
