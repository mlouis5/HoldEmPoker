/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.util.CommunityObserver;
import java.util.Comparator;

/**
 *
 * @author MacDerson
 */
public interface Hand extends CommunityObserver{
    
    Card[] getHand() throws Exception;
    
    HandType getHandType() throws Exception;
    
    void addToHand(Card card);
}
