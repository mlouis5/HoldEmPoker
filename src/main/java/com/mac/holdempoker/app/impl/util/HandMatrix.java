/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.exceptions.InvalidHandException;
import com.mac.holdempoker.app.impl.SimpleHandRank;
import com.mac.holdempoker.app.impl.SimpleCard;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class HandMatrix implements Comparator<Card> {

    private static final Pattern METHOD_PATTERN = Pattern.compile("\\wHandRank");
    private final Map<Suit, Rank[]> hand;
    private final Map<Rank, Integer> rankHistogram;
    private final RankComparator rc;

    public HandMatrix() {
        hand = new HashMap();
        rankHistogram = new HashMap();
        rc = new RankComparator();
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

    public HandRank getHand() throws InvalidHandException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        if (hasValidHand()) {
            Method[] methods = this.getClass().getDeclaredMethods();
            Arrays.sort(methods, new MethodNameComparator());
            for (Method method : methods) {
                method.setAccessible(true);
                String methodName = method.getName();
//                System.out.println(methodName);
                Matcher m = METHOD_PATTERN.matcher(methodName);
                if (m.matches()) {
                    Object[] args = null;
                    Object obj = method.invoke(this, args);
                    if (Objects.nonNull(obj)) {
                        HandRank hr = (HandRank) obj;
                        if (Objects.nonNull(hr)) {
                            method.setAccessible(false);
                            return hr;
                        }
                    }
                }
                method.setAccessible(false);
            }
        }
        throw new InvalidHandException();
    }

    public void clearHand() {
        hand.clear();
        rankHistogram.clear();
    }

    /**
     * ROYAL FLUSH
     *
     * @return
     */
    private HandRank aHandRank() {
        HandRank hr = bHandRank();
        if (Objects.nonNull(hr)) {
            Card[] cards = hr.getHand();
            Arrays.sort(cards, this);
            if (cards[cards.length - 1].getRank().value() == 14
                    && cards[0].getRank().value() == 10) {
                return new SimpleHandRank(HandType.ROYAL_FLUSH, cards);
            }
        }
        return null;
    }

    /**
     * STRAIGHT FLUSH
     *
     * @return
     */
    private HandRank bHandRank() {
        HandRank hr = eHandRank();
        if (Objects.nonNull(hr)) {
            Card[] cards = hr.getHand();
            Arrays.sort(cards, this);
            if (isConsecutive(cards)) {
                return new SimpleHandRank(HandType.STRAIGHT_FLUSH, cards);
            }
        }
        return null;
    }

    /**
     * FOUR OF A KIND
     *
     * @return
     */
    private HandRank cHandRank() {
        Rank[] qRank = getRanksWithCount(4, 1);
        if (qRank.length != 1) {
            return null;
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
        Arrays.sort(cards, this);
        return new SimpleHandRank(HandType.FOUR_OF_A_KIND, cards);
    }

    /**
     * FULL HOUSE
     *
     * @return
     */
    private HandRank dHandRank() {
        Rank[] qRank = getRanksWithCount(3, 1);
        if (qRank.length == 1) {
            return null;
        }
        Card[] cards = new Card[5];
        int index = 0;
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards[index++] = makeCard(suit, r);
            }
        }
        Rank[] singleRanks = getRanksWithCount(2, 1);
        if (singleRanks.length != 1) {
            return null;
        }
        List<Suit> suit = getSuitsForRank(singleRanks[0]);
        cards[index++] = makeCard(suit.get(0), singleRanks[0]);
        Arrays.sort(cards, this);
        return new SimpleHandRank(HandType.FULL_HOUSE, cards);
    }

    /**
     * FLUSH
     *
     * @return
     */
    private HandRank eHandRank() {
        Entry<Suit, Rank[]> flush = getFlushSuit();
        if (Objects.isNull(flush)) {
            return null;
        }
        Rank[] ranks = flush.getValue();
        Arrays.sort(ranks, rc);
        CollectionUtils.reverseArray(ranks);

        List<Card> cards = new ArrayList();
        for (int i = 0; i < 13; i++) {
            Rank r = ranks[i];
            if (Objects.nonNull(r) && cards.size() < 5) {
                cards.add(makeCard(flush.getKey(), r));
            }
        }
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.FLUSH, cards.toArray(new Card[5]));
    }

    /**
     * STRAIGHT
     *
     * @return
     */
    private HandRank fHandRank() {
        Set<Rank> keys = rankHistogram.keySet();
        Rank[] ranks = keys.toArray(new Rank[keys.size()]);
        Rank[] cons = getConsecutiveRanks(ranks);
        if (Objects.isNull(cons)) {
            return null;
        }
        List<Card> cards = new ArrayList();
        for (Rank r : cons) {
            Suit s = getSuitForRank(r);
            cards.add(makeCard(s, r));
        }
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.STRAIGHT, cards.toArray(new Card[5]));
    }

    /**
     * TRIPS/SET
     *
     * @return
     */
    private HandRank gHandRank() {
        Rank[] qRank = getRanksWithCount(3);
        if (qRank.length != 1) {
            return null;
        }
        Rank r = qRank[0];
        List<Suit> suits = getSuitsForRank(r);
        Card[] cards = new Card[5];
        int index = 0;
        for (Suit suit : suits) {
            cards[index++] = makeCard(suit, r);
        }
        Rank[] singleRanks = getRanksWithCount(1, 2);
        if (singleRanks.length != 2) {
            return null;
        }
        for (Rank rank : singleRanks) {
            suits = getSuitsForRank(rank);
            cards[index++] = makeCard(suits.get(0), rank);
        }
        Arrays.sort(cards, this);
        return new SimpleHandRank(HandType.THREE_OF_A_KIND, cards);
    }

    /**
     * TWO PAIR
     *
     * @return
     */
    private HandRank hHandRank() {
        Rank[] qRank = getRanksWithCount(2, 2);
        if (qRank.length == 2) {
            return null;
        }
        Card[] cards = new Card[5];
        int index = 0;
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards[index++] = makeCard(suit, r);
            }
        }
        Rank[] singleRanks = getRanksWithCount(1, 1);
        if (singleRanks.length != 1) {
            return null;
        }
        List<Suit> suit = getSuitsForRank(singleRanks[0]);
        cards[index++] = makeCard(suit.get(0), singleRanks[0]);
        Arrays.sort(cards, this);
        return new SimpleHandRank(HandType.TWO_PAIR, cards);
    }

    /**
     * PAIR
     *
     * @return
     */
    private HandRank iHandRank() {
        Rank[] qRank = getRanksWithCount(2, 1);
        if (qRank.length == 1) {
            return null;
        }
        Card[] cards = new Card[5];
        int index = 0;
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards[index++] = makeCard(suit, r);
            }
        }
        Rank[] singleRanks = getRanksWithCount(1, 3);
        if (singleRanks.length != 3) {
            return null;
        }
        for (Rank r : singleRanks) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards[index++] = makeCard(suit, r);
            }
        }
        Arrays.sort(cards, this);
        return new SimpleHandRank(HandType.PAIR, cards);
    }

    /**
     * HIGH
     *
     * @return
     */
    private HandRank jHandRank() {
        Rank[] qRank = getRanksWithCount(1, 5);
        if (qRank.length == 5) {
            return null;
        }
        Card[] cards = new Card[5];
        int index = 0;
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards[index++] = makeCard(suit, r);
            }
        }
        Arrays.sort(cards, this);
        return new SimpleHandRank(HandType.HIGH, cards);
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

//    private Rank findHighestRank(Rank... ranks) {
//        Rank hr = null;
//        for (Rank r : ranks) {
//            if (Objects.nonNull(r)) {
//                if (Objects.isNull(hr)) {
//                    hr = r;
//                } else {
//                    if (r.value() > hr.value()) {
//                        hr = r;
//                    }
//                }
//            }
//        }
//        return hr;
//    }

    private Rank[] getRanksWithCount(int count, int amount) {
        List<Rank> foundRanks = new ArrayList(1);

        Set<Rank> keys = rankHistogram.keySet();
        Rank[] ranks = keys.toArray(new Rank[keys.size()]);
        Arrays.sort(ranks, rc);
        ArrayUtils.reverse(ranks);

        for (Rank r : ranks) {
            Integer val = rankHistogram.get(r);
            if (Objects.nonNull(val)) {
                if (val == count) {
                    foundRanks.add(r);
                    if (foundRanks.size() == amount) {
                        break;
                    }
                }
            }
        }
        return foundRanks.toArray(new Rank[foundRanks.size()]);
    }

//    private Rank getHighestRank() {
//        Rank highestRank = null;
//        for (Entry<Rank, Integer> entry : rankHistogram.entrySet()) {
//            if (entry.getValue() >= 1) {
//                if (Objects.isNull(highestRank)) {
//                    highestRank = entry.getKey();
//                } else {
//                    if (entry.getKey().value() > highestRank.value()) {
//                        highestRank = entry.getKey();
//                    }
//                }
//            }
//        }
//        return highestRank;
//    }

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

//    private Rank getNextHighestRank(Rank rank) {
//        Rank nextHighestRank = null;
//        Set<Rank> keys = rankHistogram.keySet();
//        Rank[] ranks = keys.toArray(new Rank[keys.size()]);
//        ArrayUtils.reverse(ranks);
//
//        for (Rank r : ranks) {
//            Integer val = rankHistogram.get(r);
//            if (Objects.nonNull(val)) {
//                if (val >= 1) {
//                    if (Objects.isNull(nextHighestRank)) {
//                        nextHighestRank = r;
//                    } else {
//                        if (r.value() < rank.value()) {
//                            return r;
//                        }
//                    }
//                }
//            }
//        }
//        return nextHighestRank;
//    }

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

        Arrays.sort(ranks, rc);
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

    private boolean isConsecutive(Card[] cards) {
        boolean isConsec = true;
        int i, j;
        for (i = 0, j = i + 1; j < cards.length; i++, j++) {
            if (Math.abs(cards[j].getRank().value() - cards[i].getRank().value()) != 1) {
                isConsec = false;
                break;
            }
        }
        return isConsec;

    }

    private Card makeCard(Suit suit, Rank rank) {
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

    private static class MethodNameComparator implements Comparator<Method> {
        @Override
        public int compare(Method o1, Method o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private static class RankComparator implements Comparator<Rank> {
        @Override
        public int compare(Rank o1, Rank o2) {
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                return o1.value() - o2.value();
            } else if (Objects.isNull(o1)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
