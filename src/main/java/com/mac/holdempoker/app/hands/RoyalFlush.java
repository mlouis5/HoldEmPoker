/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.util.CommunityObserver;
import com.mac.holdempoker.app.util.HandDistributor;
import com.mac.holdempoker.app.util.PlayerObserver;
import java.util.function.Consumer;

/**
 *
 * @author Mac
 */
public class RoyalFlush implements Consumer<Card>, HandDistributor,
        HandRank, CommunityObserver, PlayerObserver {

    private final StraightFlush sFlush;
    
    public RoyalFlush(){
        sFlush = new StraightFlush();
    }
    
    @Override
    public void accept(Card card) {
        sFlush.accept(card);
    }

    @Override
    public Card[] getHand() {
        Card[] cards = sFlush.getHand();
        if(cards.length == 0){
            return cards;
        }
        if(cards[5].getRank() == Rank.ACE && cards[0].getRank() == Rank.TEN){
            return cards;
        }
        return new Card[0];
    }

    @Override
    public HandType getHandType() {
        return HandType.ROYAL_FLUSH;
    }

    @Override
    public void dealt(Card... cards) {
        for(Card card : cards){
            accept(card);
        }
    }

    @Override
    public void haveCard(Card card) {
        dealt(card);
    }
    
}
