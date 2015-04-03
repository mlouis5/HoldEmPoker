/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.util;

import com.mac.holdempoker.app.Card;
import java.util.Comparator;

/**
 *
 * @author Mac
 */
public interface HandDistributor extends Comparator<Card>{
    
    Card[] getHand();
    
    void clearHand();
    
    @Override
    public default int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
}
