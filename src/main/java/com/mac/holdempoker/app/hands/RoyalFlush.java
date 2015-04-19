/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class RoyalFlush extends AbstractHand {
    
    private final StraightFlush straightFlush;
    
    public RoyalFlush(){
        super(HandType.ROYAL_FLUSH);
        straightFlush = new StraightFlush();
    }
    
    @Override
    public Card[] getHand(){
        Card[] hand = straightFlush.getHand();
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand((hand == null ? 
                null : hand[0].getRank() == Rank.TEN 
                && hand[4].getRank() == Rank.ACE ? hand : null));
        }
        return super.getFinalHand();
    }
    
    @Override
    public void clear(){
        super.clearFinalHand();
        straightFlush.clear();
    }
    
    @Override
    public void haveCard(Card card) {
        straightFlush.haveCard(card);
    }

    @Override
    public void haveCards(Card... cards) {
        straightFlush.haveCards(cards);
    }
}
