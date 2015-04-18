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
public class TwoPair extends AbstractHand {

    Map<Rank, Card[]> cards;
    private int insertSize;

    public TwoPair() {
        super(HandType.TWO_PAIR);
        cards = new TreeMap(this);
    }

    @Override
    public Card[] getHand() {
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findTwoPair());
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
        Arrays.sort(ranked, cardComparator());
        insertSize++;
    }

    private Card[] findTwoPair() {
        Card[] highPair = null;
        Card[] lowPair = null;
        Card highSingle = null;
        for (Map.Entry<Rank, Card[]> entry : cards.entrySet()) {            
//            System.out.println(Arrays.toString(entry.getValue()));            
            if (hasTwo(entry.getValue()) && Objects.isNull(highPair)) {
                highPair = ArrayUtils.subarray(entry.getValue(), 0, 2);
            } else if (hasTwo(entry.getValue()) && Objects.isNull(lowPair)) {
                lowPair = ArrayUtils.subarray(entry.getValue(), 0, 2);
            } else if (Objects.isNull(highSingle)) {
                highSingle = getSingleCard(entry.getValue());
            }
        }
//        System.out.println("high: " + Arrays.toString(highPair) + "\tlow: " + Arrays.toString(lowPair) + "\tsingle: " + highSingle);
        if (Objects.nonNull(highPair) && Objects.nonNull(lowPair)
                && Objects.nonNull(highSingle)) {
            Card[] cs = ArrayUtils.addAll(lowPair, highPair);
            return ArrayUtils.add(cs, highSingle);
        } else {
            return null;
        }
    }

    private void insert(Card[] ranked, Card insert) {
        for (int i = 1; i < ranked.length; i++) {
            if (Objects.isNull(ranked[i])) {
                ranked[i] = insert;
                break;
            }
        }
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

    private Card getSingleCard(Card[] all) {
        for(Card c : all){
            if(Objects.nonNull(c)){
                return c;
            }
        }
        return null;
    }
}
