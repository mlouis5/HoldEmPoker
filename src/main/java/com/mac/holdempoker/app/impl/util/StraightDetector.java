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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class StraightDetector implements Consumer<Card>, HandDistributor {

    private final List<Card> hand;

    public StraightDetector() {
        hand = new ArrayList(7);
    }

    @Override
    public void accept(Card card) {
        if (Objects.nonNull(card)) {
            hand.add(card);
        }
    }

    @Override
    public Card[] getHand() {        
        Collections.sort(hand, this);
        int i;
        List<Card> finalList = new ArrayList(5);
        Card lastCard = null;
        for(i = hand.size() - 1; i >= 0; i--){
            Card c = hand.get(i);
            if(Objects.isNull(lastCard)){
                finalList.add(c);
                lastCard = c;
            }else{
                if(lastCard.getRank().value() - c.getRank().value() == 1){
                    finalList.add(c);
                    lastCard = c;
                }else{
                    finalList.clear();
                    lastCard = null;
                    i++;
                }
            }
            if(c.getRank().value() == 2){
                break;
            }
        }
        Collections.sort(finalList, this);
        int size = finalList.size();
        if(size == 4 && ((finalList.get(size - 1).getRank().value()) == 5 && (finalList.get(0).getRank().value()) == 2)){
            if(hand.get(hand.size() - 1).getRank().value() == 14){
                finalList.add(hand.get(hand.size() - 1));
                return finalList.toArray(new Card[5]);
            }
        }else if(size == 5){
            return finalList.toArray(new Card[5]);
        }
        return new Card[0];
    }

}
