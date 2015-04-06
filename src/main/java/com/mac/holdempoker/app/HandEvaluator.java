/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app;

/**
 *
 * @author MacDerson
 */
public interface HandEvaluator {
    
    public void haveCard(Card... card);
    
    public HandRank getBestHand() throws Exception;
    
    public void clearHands();
}
