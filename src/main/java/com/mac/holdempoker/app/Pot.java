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

    void increasePot(int amount);
    
    int getPotAmount();
    
    void clearPot();
}
