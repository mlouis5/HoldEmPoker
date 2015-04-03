/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.HandType;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 *
 * @author Mac
 */
public interface HandRank extends Consumer<Card>, Comparator<Card>{
    
    HandType getHandType();
    
    Card[] getHand();
    
    boolean isValidHand();
    
    void clearHand();
    
    @Override
    public default int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
    
}
