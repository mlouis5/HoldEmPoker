/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Community;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.enums.HandRank;
import com.mac.holdempoker.app.enums.Suit;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class SimpleHand implements Hand{

    @Autowired
    @Qualifier("communityImpl")
    private Community community;
    
    private static final int MAX_HAND_SIZE = 7;
    private Card[] hand;
    private int handIndex;
    
    public SimpleHand(){
        hand = new Card[MAX_HAND_SIZE];
        this.handIndex = 0;
    }

    @Override
    public Card[] getHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToHand(Card card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

    @Override
    public void sortHand() {
        Arrays.sort(hand, this);
    }

    @Override
    public boolean isFlush() {
       Suit[] suits = Suit.values();
       for(Suit suit : suits){
           int totalValue = suit.suitValue() * 5;           
           for(int i = 0; i < 7 && i < hand.length; i++){
               int bestValue = 0;
               for(int j = 0; j < 5 && j < hand.length; j++){
                   Card card = hand[j];
                   bestValue += card.getSuit().suitValue();
               }
               if(bestValue == totalValue){
                   return true;
               }
           }
       }
       return false;
    }

    @Override
    public boolean isStraight() {
        
    }
    
}
