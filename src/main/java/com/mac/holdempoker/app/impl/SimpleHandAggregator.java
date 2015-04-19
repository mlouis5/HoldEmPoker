/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.HandAggregator;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.hands.AbstractHand;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleHandAggregator implements HandAggregator, Comparator<Card> {

    @Override
    public long scoreHand(Hand hand) throws Exception {
        if (Objects.nonNull(hand)) {
            AbstractHand ah = hand.getHand();
            HandType ht = ah.getHandType();
            Card[] cards = ah.getHand();
            Arrays.sort(cards, this);
            switch (ht) {
                case ROYAL_FLUSH: {
                    return ht.getInterValue();
                }
                case STRAIGHT_FLUSH: {
                    return ht.getInterValue() + scoreHighHand(cards);
                }
                case FOUR_OF_A_KIND: {
                    return ht.getInterValue() + scoreTripsBoatQuads(cards);
                }
                case FULL_HOUSE: {
                    return ht.getInterValue() + scoreTripsBoatQuads(cards);
                }
                case FLUSH: {
                    return ht.getInterValue() + scoreHighHand(cards);
                }
                case STRAIGHT: {
                    return ht.getInterValue() + scoreHighHand(cards);
                }
                case THREE_OF_A_KIND: {
                    return ht.getInterValue() + scoreTripsBoatQuads(cards);
                }
                case TWO_PAIR: {
                    return ht.getInterValue() + scoreTwoPair(cards);
                }
                case PAIR: {
                    return ht.getInterValue() + scorePair(cards);
                }
                case HIGH: {
                    return scoreHighHand(cards);
                }
                default: {
                    return 0L;
                }
            }
        }
        return 0;
    }

    private long scoreHighHand(Card[] hand) {
        long val = 0L;
        for (int i = 0; i < hand.length; i++) {
            int rankVal = hand[i].getRank().value();
            val += Math.pow(14, i) * rankVal;
        }
        return val;
    }

    private long scorePair(Card[] hand) {
        long val;
        if (hand[0].getRank() == hand[1].getRank()) {
            // a a x y z
            val = (long) Math.pow(14, 3) * hand[0].getRank().value()
                    + hand[2].getRank().value() + 14 * hand[3].getRank().value()
                    + (long) Math.pow(14, 2) * hand[4].getRank().value();
        } else if (hand[1].getRank().value() == hand[2].getRank().value()) {
            // x a a y z
            val = (long) Math.pow(14, 3) * hand[1].getRank().value()
                    + +hand[0].getRank().value() + 14 * hand[3].getRank().value()
                    + (long) Math.pow(14, 2) * hand[4].getRank().value();
        } else if (hand[2].getRank().value() == hand[3].getRank().value()) {
            // x y a a z
            val = (long) Math.pow(14, 3) * hand[2].getRank().value()
                    + +hand[0].getRank().value() + 14 * hand[1].getRank().value()
                    + (long) Math.pow(14, 2) * hand[4].getRank().value();
        } else {
            // x y z a a
            val = (long) Math.pow(14, 3) * hand[3].getRank().value()
                    + +hand[0].getRank().value() + 14 * hand[1].getRank().value()
                    + (long) Math.pow(14, 2) * hand[2].getRank().value();
        }
        return val;
    }

    private long scoreTwoPair(Card[] hand) {
        long val;
        if (hand[0].getRank().value() == hand[1].getRank().value()
                && hand[2].getRank().value() == hand[3].getRank().value()) {
            // a a b b x
            val = (long) Math.pow(14, 2) * hand[2].getRank().value() + 14
                    * hand[0].getRank().value() + hand[4].getRank().value();
        } else if (hand[0].getRank().value() == hand[1].getRank().value()
                && hand[3].getRank().value() == hand[4].getRank().value()) {
            // a a x b b
            val = (long) Math.pow(14, 2) * hand[3].getRank().value() + 14
                    * hand[0].getRank().value() + hand[2].getRank().value();
        } else {
            // x a a b b
            val = (long) Math.pow(14, 2) * hand[3].getRank().value() + 14
                    * hand[1].getRank().value() + hand[0].getRank().value();
        }
        return val;
    }

    public long scoreTripsBoatQuads(Card[] cards) {
        return cards[2].getRank().value();
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

}
