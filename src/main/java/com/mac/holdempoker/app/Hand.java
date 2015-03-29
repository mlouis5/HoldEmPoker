/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.HandRank;
import java.util.Comparator;

/**
 *
 * @author MacDerson
 */
public interface Hand extends Comparator<Card>{
    
    Card[] getHand();
    
    void addToHand(Card card);
    
    void sortHand();
    
    boolean isFlush();
    
    boolean isStraight();
}
