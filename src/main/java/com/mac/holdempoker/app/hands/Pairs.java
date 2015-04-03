/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.util.CommunityObserver;
import com.mac.holdempoker.app.util.HandDistributor;
import com.mac.holdempoker.app.util.PlayerObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.collections.ListUtils;

/**
 *
 * @author MacDerson
 */
public class Pairs implements Consumer<Card>, HandDistributor, 
        HandRank, CommunityObserver, PlayerObserver {

    private final Map<Rank, List<Card>> hand;

    public Pairs() {
        hand = new HashMap();
    }
    
    @Override
    public HandType getHandType() {
        return HandType.TWO_PAIR;
    }

    @Override
    public void dealt(Card... cards) {
        for(Card card: cards){
            accept(card);
        }
    }

    @Override
    public void haveCard(Card card) {
        dealt(card);
    }

    @Override
    public void accept(Card card) {
        if (Objects.nonNull(card)) {
            List<Card> cards = hand.get(card.getRank());
            if (Objects.nonNull(cards)) {
                cards.add(card);
            } else {
                cards = new ArrayList();
                cards.add(card);
                hand.put(card.getRank(), cards);
            }
        }
    }

    @Override
    public Card[] getHand() {
        Card highestSingle = null;
        List<Card> high = new ArrayList(2);
        List<Card> low = new ArrayList(2);
                
        for (Map.Entry<Rank, List<Card>> entry : hand.entrySet()) {
            List<Card> list = entry.getValue();
            if (list.size() == 1) {
                if(Objects.isNull(highestSingle)){
                    highestSingle = list.get(0);
                }else{
                    if(entry.getKey().value() > highestSingle.getRank().value()){
                        highestSingle = list.get(0);
                    }
                }
            } else if (list.size() == 2) {
                if (low.isEmpty() && high.isEmpty()) {
                    low.addAll(list);
                    high.addAll(list);
                } else {
                    int lowRank = low.get(0).getRank().value();
                    int highRank = high.get(0).getRank().value();
                    if(lowRank == highRank){
                        if(entry.getKey().value() > highRank){
                            high.clear();
                            high.addAll(list);
                        }
                    }else{
                        int entryRank = entry.getKey().value();
                        if(entryRank > highRank){
                            high.clear();
                            high.addAll(list);
                        }else if(entryRank > lowRank && entryRank < highRank){
                            low.clear();
                            low.addAll(list);
                        }
                    }
                    
                }
            }
        }
        List<Card> all = ListUtils.union(low, high); 
        
        if(Objects.nonNull(highestSingle)){
            all.add(highestSingle);
        }
        Collections.sort(all, this);
        return all.size() == 5 ? all.toArray(new Card[5]) : new Card[0];
    }    

}