/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.util.CommunityObserver;
import com.mac.holdempoker.app.util.PlayerObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mac
 */
public class Pairs implements HandRank, CommunityObserver, PlayerObserver{

    private final List<Card> hand;
    
    public Pairs(){
        hand = new ArrayList();
    }
    
    @Override
    public HandType getHandType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Card[] getHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

    @Override
    public void haveCard(Card card) {
        dealt(card);
    }
    
    @Override
    public void dealt(Card... cards) {
        if(isValidDeal(hand, cards)){
            for(Card card : cards){
                hand.add(card);
                //histo.haveCard(card);
            }
        }
    }
    
}
