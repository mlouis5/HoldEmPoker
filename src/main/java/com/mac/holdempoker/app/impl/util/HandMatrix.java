/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.exceptions.InvalidHandException;
import com.mac.holdempoker.app.impl.SimpleCard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class HandMatrix implements Comparator<Card> {

    private final Map<Suit, Rank[]> hand;
    private final Map<Rank, Integer> rankHistogram;

    public HandMatrix() {
        hand = new HashMap();
        rankHistogram = new HashMap();
    }

    public void haveCard(Card card) {
        if (Objects.nonNull(card)) {
            Rank rank = card.getRank();
            Suit suit = card.getSuit();
            Rank[] suitedRank = hand.get(suit);

            if (Objects.nonNull(suitedRank)) {
                suitedRank[rank.value() - 2] = rank;
            } else {
                Rank[] newSet = new Rank[13];
                newSet[rank.value() - 2] = rank;
                hand.put(suit, newSet);
            }
            Integer count = rankHistogram.get(rank);
            if (Objects.nonNull(count)) {
                rankHistogram.put(rank, ++count);
            } else {
                count = 1;
                rankHistogram.put(rank, count);
            }
        }
    }

    public Card[] getHand() throws InvalidHandException {
        if (hasValidHand()) {

        }
        throw new InvalidHandException();
    }

    private Card[] getRoyalFlush() {
        Card[] cards = getStraightFlush();
        if (cards.length == 0) {
            return cards;
        }
        if (cards[cards.length - 1].getRank().value() == 14
                && cards[0].getRank().value() == 10) {
            return cards;
        }
        return new Card[0];
    }

    private Card[] getStraightFlush() {
        Entry<Suit, Rank[]> flush = getFlushSuit();

        if (Objects.nonNull(flush)) {
            Rank[] cons = getConsecutiveRanks(flush.getValue());
            if (Objects.isNull(cons)) {
                return new Card[0];
            }
            List<Card> cards = new ArrayList();
            Suit s = flush.getKey();
            for (Rank r : cons) {
                cards.add(makeCard(s, r));
            }
            Collections.sort(cards, this);
            return cards.toArray(new Card[5]);
        }
        return new Card[0];
    }

    private Card[] getQuads() {
        Rank[] qRank = getRanksWithCount(4);
        if (qRank.length != 1) {
            return new Card[0];
        }
        Rank r = qRank[0];
        List<Suit> suits = getSuitsForRank(r);
        Card[] cards = new Card[5];
        int index = 0;
        for (Suit suit : suits) {
            cards[index++] = makeCard(suit, r);
        }
        Rank hRank = getHighestSingleRank();
        suits = getSuitsForRank(r);
        cards[index] = makeCard(suits.get(0), hRank);
        return cards;
    }

    private Card[] getFlush() {
        Entry<Suit, Rank[]> flush = getFlushSuit();
        if (Objects.isNull(flush)) {
            return new Card[0];
        }
        Rank[] ranks = flush.getValue();
        Arrays.sort(ranks);
        CollectionUtils.reverseArray(ranks);

        List<Card> cards = new ArrayList();
        for (int i = 0; i < 13; i++) {
            Rank r = ranks[i];
            if (Objects.nonNull(r) && cards.size() < 5) {
                cards.add(makeCard(flush.getKey(), r));
            }
        }
        Collections.sort(cards, this);
        return cards.toArray(new Card[5]);
    }

    private Card[] getStraight() {
        Set<Rank> keys = rankHistogram.keySet();
        Rank[] ranks = keys.toArray(new Rank[keys.size()]);
        Rank[] cons = getConsecutiveRanks(ranks);
        if (Objects.isNull(cons)) {
            return new Card[0];
        }
        List<Card> cards = new ArrayList();
        for (Rank r : cons) {
            Suit s = getSuitForRank(r);
            cards.add(makeCard(s, r));
        }
        Collections.sort(cards, this);
        return cards.toArray(new Card[5]);
    }

    private Card[] getTrips() {
        Rank[] qRank = getRanksWithCount(3);
        if (qRank.length != 1) {
            return new Card[0];
        }
        Rank r = qRank[0];
        List<Suit> suits = getSuitsForRank(r);
        Card[] cards = new Card[5];
        int index = 0;
        for (Suit suit : suits) {
            cards[index++] = makeCard(suit, r);
        }
        Rank[] singleRanks = getRanksWithCount(1, 2);
        if(singleRanks.length != 2){
            return new Card[0];
        }
        for(Rank rank : singleRanks){
            suits = getSuitsForRank(rank);
            cards[index++] = makeCard(suits.get(0), rank);
        }        
        return cards;
    }
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////HELPERS////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private Rank[] getRanksWithCount(int count) {
        List<Rank> ranks = new ArrayList(1);
        for (Entry<Rank, Integer> entry : rankHistogram.entrySet()) {
            if (entry.getValue() == count) {
                ranks.add(entry.getKey());
            }
        }
        return ranks.toArray(new Rank[ranks.size()]);
    }

    private Rank findHighestRank(Rank... ranks) {
        Rank hr = null;
        for (Rank r : ranks) {
            if (Objects.nonNull(r)) {
                if (Objects.isNull(hr)) {
                    hr = r;
                } else {
                    if (r.value() > hr.value()) {
                        hr = r;
                    }
                }
            }
        }
        return hr;
    }

    private Rank[] getRanksWithCount(int count, int amount) {
        List<Rank> foundRanks = new ArrayList(1);

        Set<Rank> keys = rankHistogram.keySet();
        Rank[] ranks = keys.toArray(new Rank[keys.size()]);
        Arrays.sort(ranks);
        ArrayUtils.reverse(ranks);

        for (Rank r : ranks) {
            Integer val = rankHistogram.get(r);
            if (Objects.nonNull(val)) {
                if (val == count) {
                    foundRanks.add(r);
                    if(foundRanks.size() == amount){
                        break;
                    }
                }
            }
        }
        return foundRanks.toArray(new Rank[foundRanks.size()]);
    }

    private Rank getHighestRank() {
        Rank highestRank = null;
        for (Entry<Rank, Integer> entry : rankHistogram.entrySet()) {
            if (entry.getValue() >= 1) {
                if (Objects.isNull(highestRank)) {
                    highestRank = entry.getKey();
                } else {
                    if (entry.getKey().value() > highestRank.value()) {
                        highestRank = entry.getKey();
                    }
                }
            }
        }
        return highestRank;
    }

    private Rank getHighestSingleRank() {
        Rank highestRank = null;
        for (Entry<Rank, Integer> entry : rankHistogram.entrySet()) {
            if (entry.getValue() == 1) {
                if (Objects.isNull(highestRank)) {
                    highestRank = entry.getKey();
                } else {
                    if (entry.getKey().value() > highestRank.value()) {
                        highestRank = entry.getKey();
                    }
                }
            }
        }
        return highestRank;
    }

    private Rank getNextHighestRank(Rank rank) {
        Rank nextHighestRank = null;
        Set<Rank> keys = rankHistogram.keySet();
        Rank[] ranks = keys.toArray(new Rank[keys.size()]);
        ArrayUtils.reverse(ranks);

        for (Rank r : ranks) {
            Integer val = rankHistogram.get(r);
            if (Objects.nonNull(val)) {
                if (val >= 1) {
                    if (Objects.isNull(nextHighestRank)) {
                        nextHighestRank = r;
                    } else {
                        if (r.value() < rank.value()) {
                            return r;
                        }
                    }
                }
            }
        }
        return nextHighestRank;
    }

    private List<Suit> getSuitsForRank(Rank rank) {
        List<Suit> suites = new ArrayList();
        for (Entry<Suit, Rank[]> entry : hand.entrySet()) {
            if (Objects.nonNull(entry.getValue()[rank.value() - 2])) {
                suites.add(entry.getKey());
            }
        }
        return suites;
    }

    private Suit getSuitForRank(Rank rank) {
        Suit suit = null;
        for (Entry<Suit, Rank[]> entry : hand.entrySet()) {
            if (Objects.nonNull(entry.getValue()[rank.value() - 2])) {
                suit = entry.getKey();
                break;
            }
        }
        return suit;
    }

    private Entry<Suit, Rank[]> getFlushSuit() {
        for (Entry<Suit, Rank[]> entry : hand.entrySet()) {
            int count = 0;
            Rank[] ranks = entry.getValue();
            for (Rank r : ranks) {
                if (Objects.nonNull(r)) {
                    count++;
                }
            }
            if (count >= 5) {
                return entry;
            }
        }
        return null;
    }

    private Rank[] getConsecutiveRanks(Rank[] ranks) {
        List<Rank> consRanks = new ArrayList();

        Arrays.sort(ranks);
        ArrayUtils.reverse(ranks);

        for (Rank r : ranks) {
            if (consRanks.size() == 5) {
                Collections.reverse(consRanks);
                return consRanks.toArray(new Rank[5]);
            }
            Integer count = rankHistogram.get(r);
            if (Objects.nonNull(count) && count >= 1) {
                consRanks.add(r);
            } else {
                consRanks.clear();
            }
        }
        return null;
    }
    
    private Card makeCard(Suit suit, Rank rank){
        return new SimpleCard(suit, rank);
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

    private boolean hasValidHand() {
        for (Entry<Suit, Rank[]> entry : hand.entrySet()) {
            int count = 0;
            for (Rank r : entry.getValue()) {
                if (Objects.nonNull(r)) {
                    count++;
                    if (count >= 5 && count <= 7) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    
}
