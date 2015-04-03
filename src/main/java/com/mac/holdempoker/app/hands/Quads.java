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

/**
 *
 * @author Mac
 */
public class Quads implements Consumer<Card>, HandDistributor,
        HandRank, CommunityObserver, PlayerObserver {

    private Map<Rank, List<Card>> hand;
    
    public Quads(){
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
    public HandType getHandType() {
        return HandType.FOUR_OF_A_KIND;
    }

    @Override
    public void haveCard(Card card) {
        dealt(card);
    }

    @Override
    public void dealt(Card... cards) {
        for (Card card : cards) {
            this.accept(card);
        }
    }
    
    @Override
    public void clearHand() {
        hand.clear();
    }
}
