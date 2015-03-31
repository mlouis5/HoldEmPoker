/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.HandType;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public interface HandRank extends Comparator<Card> {
    
    HandType getHandType();
    
    Card[] getHand();
    
    boolean isValid();
    
    public default boolean isValidDeal(Collection hand, Card... card){
        return hand.size() < 7 && Objects.nonNull(card) && card.length >= 1 && card.length <= 3
                && (hand.size() + card.length <= 7);
    }
}
