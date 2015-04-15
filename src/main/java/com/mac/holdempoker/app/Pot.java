/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.PotName;

/**
 *
 * @author MacDerson
 */
public interface Pot{
    
    void setPotName(PotName potName);
    
    PotName getPotName();

    void increasePot(MonetaryAction action);
    
    int getMinBetAmount();
    
    int getMinRaiseAmount();
    
    int getPotAmount();
    
    void clearPot();
    
    public int getPlayerPotCommitment(Player p);
}
