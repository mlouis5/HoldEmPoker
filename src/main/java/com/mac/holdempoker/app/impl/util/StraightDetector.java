/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.util.HandDistributor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * @author Mac
 */
public class StraightDetector implements Consumer<Card>, HandDistributor {

    private List<Card> hand;

    public StraightDetector() {
        hand = new ArrayList(7);
    }

    @Override
    public void accept(Card card) {
        if (Objects.nonNull(card)) {
            hand.add(card);
        }
    }

    @Override
    public Card[] getHand() {
        Collections.sort(hand);
        while (hand.size() > 5) {
            hand.remove(0);
        }
        return hand.get(hand.size() - 1).getRank().value() 
                - hand.get(0).getRank().value() == 4 ? 
                hand.toArray(new Card[5]) : new Card[0];
    }

}
