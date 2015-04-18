/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public abstract class AbstractHand implements Comparator<Rank> {

    private final HandType ht;
    private static CardComparator cardComparator;
    private Card[] finalHand;

    public AbstractHand(HandType hr) {
        this.ht = hr;
        cardComparator = CardComparator.getInstance();
    }
    
    public Card[] getFinalHand(){
        return finalHand;
    }
    
    protected void setFinalHand(Card[] hand){
        this.finalHand = hand;
    }
    
    protected void clearFinalHand(){
        this.finalHand = null;
    }
    
    public CardComparator cardComparator(){
        return cardComparator;
    }

    public HandType getHandType() {
        return ht;
    }

    @Override
    public int compare(Rank o1, Rank o2) {
        return (o1.value() - o2.value()) * -1;
    }

    public abstract Card[] getHand();

    public abstract void clear();

    public abstract void haveCard(Card card);

    public void haveCard(Card... cards) {
        for (Card card : cards) {
            haveCard(card);
        }
    }

    public static class CardComparator implements Comparator<Card>{

        private static CardComparator instance;
        
        private static CardComparator getInstance(){
            if(Objects.isNull(instance)){
                instance = new CardComparator();
            }
            return instance;
        }
        @Override
        public int compare(Card o1, Card o2) {
            if(Objects.isNull(o1) || Objects.isNull(o2)){
                if(Objects.equals(o1, o2)){
                    return 0;
                }else if(Objects.nonNull(o1)){
                    return -1;
                }else{
                    return 1;
                }
            }else {
                return (o1.getRank().value() - o2.getRank().value()) * -1;
            }
        }
        
    }
}
