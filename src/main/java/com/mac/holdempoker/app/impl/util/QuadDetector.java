/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.util.HandDistributor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * @author Mac
 */
public class QuadDetector implements Consumer<Card>, HandDistributor, Comparator<Card> {

    private Map<Rank, List<Card>> hand;
    
    public QuadDetector(){
        hand = new HashMap();
    }
    
    @Override
    public void accept(Card card) {
        if(Objects.nonNull(card)){
            List<Card> cards = hand.get(card.getRank());
            if(Objects.nonNull(cards)){
                cards.add(card);
            }else{
                cards = new ArrayList();
                cards.add(card);
                hand.put(card.getRank(), cards);
            }
        }
    }

    @Override
    public Card[] getHand() {
        Card highestCard = null;
        List<Card> all = new ArrayList(5);
        for(Map.Entry<Rank, List<Card>> entry : hand.entrySet()){
            List<Card> list = entry.getValue();
            if(list.size() == 1){
                if(Objects.isNull(highestCard)){
                    highestCard = list.get(0);
                }else{
                    if(entry.getKey().value() > highestCard.getRank().value()){
                        highestCard = list.get(0);
                    }
                }
            }else if(list.size() == 4){
                all.addAll(list);
            }
        }
        all.add(highestCard);
        Collections.sort(all, this);
        return all.size() == 5 ? all.toArray(new Card[5]) : new Card[0];
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
    
}
