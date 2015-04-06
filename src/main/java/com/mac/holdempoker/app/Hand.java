/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.util.BoardObserver;

/**
 *
 * @author MacDerson
 */
public interface Hand {
    
    Card[] getHand() throws Exception;
    
    HandType getHandType() throws Exception;
    
    void addToHand(Card card);
    
    void clearHand();
    
    HandRank getHandRank() throws Exception;
    
    void haveSharedCards(Card... cards);
}
