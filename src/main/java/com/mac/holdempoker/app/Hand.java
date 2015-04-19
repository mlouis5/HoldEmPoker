/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.hands.AbstractHand;

/**
 *
 * @author MacDerson
 */
public interface Hand {
    
    AbstractHand getHand() throws Exception;
    
//    HandType getHandType() throws Exception;
    
//    void addToHand(Card card);
    
//    void clearHand();
    
//    HandRank getHandRank() throws Exception;
    
//    void haveSharedCards(Card... cards);
}
