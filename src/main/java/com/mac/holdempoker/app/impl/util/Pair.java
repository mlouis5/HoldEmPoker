/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Mac
 */
public class Pair extends AbstractHand {

    Map<Rank, List<Card>> cards;
    private int insertSize;

    public Pair() {
        super(HandType.PAIR);
        cards = new TreeMap(this);
    }

    @Override
    public Card[] getHand() {
        if(Objects.isNull(super.getFinalHand())){
            this.setFinalHand(findPair());
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
        List<Card> ranked = cards.get(card.getRank());
        if (Objects.isNull(ranked)) {
            ranked = new ArrayList();
            cards.put(card.getRank(), ranked);
        }
        ranked.add(card);
        Collections.sort(ranked, cardComparator());
        insertSize++;
    }

    private Card[] findPair() {
        List<Card> pair = new ArrayList();
        List<Card> singles = new ArrayList();
        for (Map.Entry<Rank, List<Card>> entry : cards.entrySet()) {
            if (entry.getValue().size() == 2 && pair.isEmpty()) {
                pair = entry.getValue();
            } else if (entry.getValue().size() == 1 && singles.size() < 3) {
                singles.add(entry.getValue().get(0));
            }
        }
        if (pair.size() == 2 && singles.size() == 3) {
            return (Card[]) CollectionUtils.union(pair, singles).toArray(new Card[5]);
        } else {
            return null;
        }
    }
}
