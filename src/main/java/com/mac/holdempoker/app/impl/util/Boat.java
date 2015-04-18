/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class Boat extends AbstractHand {

    Map<Rank, Card[]> cards;
    private int insertSize;

    public Boat() {
        super(HandType.FULL_HOUSE);
        cards = new TreeMap(this);
    }

    @Override
    public Card[] getHand() {
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findBoat());
        }
        return super.getFinalHand();
    }

    @Override
    public void clear() {
        super.clearFinalHand();
        cards.clear();
        insertSize = 0;
    }

    @Override
    public void haveCard(Card card) {
        if (insertSize == 7) {
            return;
        }
        Card[] ranked = cards.get(card.getRank());
        if (Objects.isNull(ranked)) {
            ranked = new Card[4];
            cards.put(card.getRank(), ranked);
        }
        insert(ranked, card);
        insertSize++;
    }

    private Card[] findBoat() {
        Card[] trips = null;
        Card[] pair = null;
        for (Map.Entry<Rank, Card[]> entry : cards.entrySet()) {
            if (hasThree(entry.getValue())) {
                if (Objects.isNull(trips)) {
                    trips = ArrayUtils.subarray(entry.getValue(), 0, 3);
                }
            } else if (hasTwo(entry.getValue())) {
                if (Objects.isNull(pair)) {
                    pair = ArrayUtils.subarray(entry.getValue(), 0, 2);
                }
            }
        }
        if (Objects.nonNull(trips) && Objects.nonNull(pair)) {
            return ArrayUtils.addAll(trips, pair);
        } else {
            return null;
        }
    }

    private void insert(Card[] ranked, Card insert) {
        for (int i = 1; i < ranked.length; i++) {
            if (Objects.isNull(ranked[i])) {
                ranked[i] = insert;
                Arrays.sort(ranked, cardComparator());
                break;
            }
        }
    }

    private boolean hasThree(Card[] all) {
        int index = 0;
        for (Card c : all) {
            if (Objects.nonNull(c)) {
                index++;
                if (index == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasTwo(Card[] all) {
        int index = 0;
        for (Card c : all) {
            if (Objects.nonNull(c)) {
                index++;
                if (index == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
