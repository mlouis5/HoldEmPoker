/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.Pot;
import com.mac.holdempoker.app.enums.PotName;

/**
 *
 * @author MacDerson
 */
public class SimplePot implements Pot{

    private int potAmount;
    private PotName potName;
    
    public SimplePot(){
        potAmount = 0;
    }
    
    @Override
    public void increasePot(int amount) {
        if(amount > 0){
            this.potAmount += amount;
        }   
    }

    @Override
    public int getPotAmount() {
        return this.potAmount;
    }

    @Override
    public void clearPot() {
        this.potAmount = 0;
    } 

    @Override
    public void setPotName(PotName potName) {
        this.potName = potName;
    }

    @Override
    public PotName getPotName() {
        return potName;
    }
}
