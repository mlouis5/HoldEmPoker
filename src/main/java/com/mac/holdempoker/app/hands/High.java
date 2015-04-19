/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author Mac
 */
public class High extends AbstractHand {
    Map<Rank, List<Card>> cards;
    private int insertSize;
    
    public High(){
        super(HandType.HIGH);
        cards = new TreeMap(this);
    }
    
    @Override
    public Card[] getHand(){
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findHigh());
        }
        return super.getFinalHand();
    }
    
    @Override
    public void clear(){
        super.clearFinalHand();
        cards.clear();
        insertSize = 0;
    }
    
    @Override
    public void haveCard(Card card) {
        if(insertSize == 7){
            return;
        }        
        List<Card> ranked = cards.get(card.getRank());        
        if(Objects.isNull(ranked)){
            ranked = new ArrayList();
            cards.put(card.getRank(), ranked);
        }
        ranked.add(card);
        Collections.sort(ranked, cardComparator());
        insertSize++;
    }
    
    private Card[] findHigh(){ 
        List<Card> singles = new ArrayList();
        for(Map.Entry<Rank, List<Card>> entry : cards.entrySet()){
            if(entry.getValue().size() == 1){
                if(singles.size() < 5){
                    singles.add(entry.getValue().get(0));
                }
            }
        }
        if(singles.size() == 5){
            return (Card[]) singles.toArray(new Card[5]);
        }else{
            return null;
        }
    }
}
