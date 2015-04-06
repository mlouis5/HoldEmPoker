/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author MacDerson
 */
public class SimpleHandRank implements HandRank{
    
    private final HandType type;
    private Card[] hand;
    
    public SimpleHandRank(HandType type, Card[] hand){
        this.type = type;
        this.hand = hand;
    }

    @Override
    public HandType getHandType() {
        return type;
    }

    @Override
    public Card[] getHand() {
        return hand;
    }

    @Override
    public boolean isValidHand() {
        return this.hand.length == 5 && ArrayUtils.isNotEmpty(hand);
    }

    @Override
    public void clearHand() {
        this.hand = null;
    }

    @Override
    public void accept(Card t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(Card o1, Card o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
