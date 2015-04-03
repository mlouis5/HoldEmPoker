/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.HandEvaluator;
import com.mac.holdempoker.app.enums.HandType;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class SimpleHand implements Hand{
    
    private final HandEvaluator handEvaluator;
        
    public SimpleHand(){
        handEvaluator = new SimpleHandEvaluator();
    }

    @Override
    public Card[] getHand() throws Exception {
        return handEvaluator.getBestHand().getHand();
    }

    @Override
    public void addToHand(Card card) {
        handEvaluator.haveCard(card);
    }

    @Override
    public HandType getHandType() throws Exception {
        return handEvaluator.getBestHand().getHandType();
    }

    @Override
    public void dealt(Card... cards) {
        handEvaluator.haveCard(cards);
    }

}
