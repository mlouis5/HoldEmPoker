/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.util.CommunityObserver;
import com.mac.holdempoker.app.util.PlayerObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * @author Mac
 */
public class Flush implements HandRank, CommunityObserver, PlayerObserver {

    private final Map<Suit, List<Card>> hand;
    
    public Flush(){
        hand = new HashMap();
    }
    
    @Override
    public void accept(Card card) {
        if(Objects.nonNull(card)){
            List<Card> cards = hand.get(card.getSuit());
            if(Objects.nonNull(cards)){
                cards.add(card);
            }else{
                cards = new ArrayList();
                cards.add(card);
                hand.put(card.getSuit(), cards);
            }
        }
    }
    
    @Override
    public HandType getHandType() {
        return HandType.FLUSH;
    }

    @Override
    public void dealt(Card... cards) {
        for(Card card : cards){
            dealt(card);
        }
    }

    @Override
    public void haveCard(Card card) {
        dealt(card);
    }

    @Override
    public Card[] getHand() {
        List<Card> all = new ArrayList(5);
        hand.entrySet().stream().map((entry) -> 
                entry.getValue()).filter((list) -> 
                        (list.size() >= 5)).forEach((list) -> {
            all.addAll(list);
        });
        Collections.sort(all, this);
        while (all.size() > 5) {
            all.remove(0);
        }
        return all.size() == 5 ? all.toArray(new Card[5]) : new Card[0];
    }
     
    @Override
    public void clearHand() {
        hand.clear();
    }

    @Override
    public boolean isValidHand() {
        return getHand().length == 5;
    }
}