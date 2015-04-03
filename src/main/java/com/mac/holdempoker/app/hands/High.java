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
public class High implements Consumer<Card>, HandDistributor, 
        HandRank, CommunityObserver, PlayerObserver {

    private final Map<Rank, List<Card>> hand;

    public High() {
        hand = new HashMap();
    }
    
    @Override
    public HandType getHandType() {
        return HandType.HIGH;
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
        List<Card> all = new ArrayList(5);
        for (Map.Entry<Rank, List<Card>> entry : hand.entrySet()) {
            List<Card> list = entry.getValue();
            if (list.size() == 1) {
                all.addAll(list);
            }
        }
        Collections.sort(all, this);
        while (all.size() > 5) {
            all.remove(0);
        }
        return all.size() == 5 ? all.toArray(new Card[5]) : new Card[0];
    }

}
