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
 * @author MacDerson
 */
public class Pair implements HandRank, CommunityObserver, PlayerObserver {

    private final Map<Rank, List<Card>> hand;

    public Pair() {
        hand = new HashMap();
    }
    
    @Override
    public HandType getHandType() {
        return HandType.PAIR;
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

    @Override
    public void accept(Card card) {
        if (Objects.nonNull(card)) {
            List<Card> cards = hand.get(card.getRank());
            if (Objects.nonNull(cards)) {
                cards.add(card);
            } else {
                cards = new ArrayList();
                cards.add(card);
                hand.put(card.getRank(), cards);
            }
        }
    }

    @Override
    public Card[] getHand() {
        List<Card> highestThree = new ArrayList(3);
        List<Card> all = new ArrayList(5);
        
        for (Map.Entry<Rank, List<Card>> entry : hand.entrySet()) {
            List<Card> list = entry.getValue();
            if (list.size() == 1) {
                highestThree.addAll(list);
            } else if (list.size() == 2) {
                if (all.isEmpty()) {
                    all.addAll(list);
                } else {
                    int rank = all.get(0).getRank().value();
                    if (entry.getKey().value() > rank) {
                        all.clear();
                        all.addAll(list);
                    }
                }
            }
        }
        Collections.sort(highestThree, this);
        while (highestThree.size() > 3) {
            highestThree.remove(0);
        }
        all.addAll(highestThree);
        Collections.sort(all, this);
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