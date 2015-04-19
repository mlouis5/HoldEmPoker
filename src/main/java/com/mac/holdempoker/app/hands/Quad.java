/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class Quad extends AbstractHand {

    Map<Rank, Card[]> cards;
    private int insertSize;

    public Quad() {
        super(HandType.FOUR_OF_A_KIND);
        cards = new TreeMap(this);
    }

    @Override
    public Card[] getHand() {
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findQuad());
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

    private Card[] findQuad() {
        Card highest = null;
        Card[] quad = null;
        for (Entry<Rank, Card[]> entry : cards.entrySet()) {
            if (hasFour(entry.getValue())) {
                if (Objects.isNull(quad)) {
                    quad = entry.getValue();
                }
            } else if (Objects.isNull(highest)) {
                highest = getSingleCard(entry.getValue());
            }
        }
        if (Objects.nonNull(quad) && Objects.nonNull(highest)) {
            return ArrayUtils.add(quad, highest);
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

    private boolean hasFour(Card[] all) {
        int index = 0;
        for (Card c : all) {
            if (Objects.nonNull(c)) {
                index++;
                if (index == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private Card getSingleCard(Card[] all) {
        return all[0];
    }

}
