/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class StraightFlush extends AbstractHand {

    Map<Suit, Card[]> cards;
    private int insertSize;
    private final Straight straight;

    public StraightFlush() {
        super(HandType.STRAIGHT_FLUSH);
        cards = new HashMap();
        straight = new Straight();
    }

    @Override
    public Card[] getHand() {
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findStraightFlush());
        }
        return super.getFinalHand();
    }

    @Override
    public void clear() {
        super.clearFinalHand();
        cards = new HashMap();
        insertSize = 0;
        straight.clear();
    }

    @Override
    public void haveCard(Card card) {
        if (insertSize == 7) {
            return;
        }
        Card[] suited = cards.get(card.getSuit());
        if (Objects.isNull(suited)) {
            suited = new Card[14];
            cards.put(card.getSuit(), suited);
        }
        suited[card.getRank().value() - 1] = card;
//        System.out.println(Arrays.toString(suited));
        if (card.getRank() == Rank.ACE) {
            suited[0] = card;
        }
        insertSize++;
    }

    private Card[] findStraightFlush() {
        for (Map.Entry<Suit, Card[]> suited : cards.entrySet()) {
            if (hasFive(suited.getValue())) {
//                System.out.println(Arrays.toString(suited.getValue()));
                straight.haveCards(suited.getValue());
                return straight.getHand();
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
}
