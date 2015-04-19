/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Suit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class Flush extends AbstractHand {

    Map<Suit, Card[]> cards;
    private int insertSize;

    public Flush() {
        super(HandType.FLUSH);
        cards = new HashMap();
        insertSize = 0;
    }

    @Override
    public Card[] getHand() {
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findFlush());
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
        Card[] suited = cards.get(card.getSuit());
        if (Objects.isNull(suited)) {
            suited = new Card[13];
            cards.put(card.getSuit(), suited);
        }
        suited[card.getRank().value() - 2] = card;
        Arrays.sort(suited, cardComparator());
        insertSize++;
    }

    private Card[] findFlush() {
        for (Entry<Suit, Card[]> suited : cards.entrySet()) {
            if (hasFive(suited.getValue())) {
                return getFlush(suited.getValue());
            }
        }
        return null;
    }

    private boolean hasFive(Card[] all) {
        int count = 0;
        for (Card c : all) {
            if (Objects.nonNull(c)) {
                count++;
            }
        }
        return count >= 5;
    }

    private Card[] getFlush(Card[] suited) {
        Card[] all = new Card[5];
        int index = 4;
        for (int i = suited.length - 1; i >= 0; i--) {
            Card c = suited[i];
            if (Objects.nonNull(c)) {
                all[index--] = c;
                if (index == -1) {
                    break;
                }
            }
        }
        return all;
    }
}
