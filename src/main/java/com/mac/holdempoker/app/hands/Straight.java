/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class Straight extends AbstractHand {

    private Card[] cards;
    private int insertSize;

    public Straight() {
        super(HandType.STRAIGHT);
        insertSize = 0;
        cards = new Card[14];
    }
    
    @Override
    public Card[] getHand(){
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findStraight());
        }
        return super.getFinalHand();
    }
    
    @Override
    public void clear(){
        super.clearFinalHand();
        insertSize = 0;
        cards = new Card[14];
    }
    
    @Override
    public void haveCard(Card card) {
        if(insertSize == 7 || Objects.isNull(card)){
            return;
        }
        cards[card.getRank().value() - 1] = card;
        if (card.getRank() == Rank.ACE) {
            cards[0] = card;
        }
        insertSize++;
    }

    private Card[] findStraight() {
        Card[] hand;
        for (int i = 9; i >= 0; i--) {
            int j = i + 5;
            hand = ArrayUtils.subarray(cards, i, j);
            if (isStraight(hand)) {
                return hand;
            }
        }
        return null;
    }

    private boolean isStraight(Card[] strt) {
        if (Objects.isNull(strt)) {
            return false;
        }
        for (Card c : strt) {
            if (Objects.isNull(c)) {
                return false;
            }
        }
        return true;
    }    
}
