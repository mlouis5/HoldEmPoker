/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.Player;
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
    
    public HandRank getHand(Player p, Board b) throws InvalidHandException, 
            IllegalAccessException, IllegalArgumentException, 
            InvocationTargetException{
        Card[] holeCards = p.getHoleCards();
        Card[] board = b.getBoard();
        
        Card[] c = ArrayUtils.addAll(board, holeCards);
        Arrays.stream(c).forEach((card) -> {
            haveCard(card);
        });        
        return getHand();
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
//            Arrays.sort(cards, this);
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
//            Arrays.sort(cards, this);
            if (isConsecutive(cards)) {
                return new SimpleHandRank(HandType.STRAIGHT_FLUSH, cards);
            } else {
                Entry<Suit, Rank[]> flush = getFlushEntry();
                List<Card> wheel = new ArrayList();
                int i = 0;
                Rank[] posWheel = Arrays.copyOf(flush.getValue(), flush.getValue().length);
                Arrays.sort(posWheel, rc);
                for (Rank r : posWheel) {
                    if (i < 4 && Objects.nonNull(r)) {
                        if ((i + 2) == r.value()) {
                            wheel.add(makeCard(flush.getKey(), r));
                        }
                    }
                    i++;
                }
                int aceIndex = ArrayUtils.indexOf(posWheel, Rank.ACE);
                if (aceIndex >= 0) {
                    wheel.add(makeCard(flush.getKey(), posWheel[aceIndex]));
                }
                if (wheel.size() != 5) {
                    return null;
                }
                return new SimpleHandRank(HandType.STRAIGHT_FLUSH,
                        wheel.toArray(new Card[5]));
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
//        System.out.println("qRank: " + Arrays.toString(qRank));
        if (qRank.length != 1) {
            return null;
        }
//        Rank r = null;//qRank[0];
        List<Card> cards = new ArrayList(5);
        int index = 0;
        for (Suit suit : Suit.values()) {
            cards.add(makeCard(suit, qRank[index]));
        }
        Rank r = getHighestRankNotEqualTo(qRank[0]);
//        System.out.println("singleRank: " + r);
        if (Objects.isNull(r)) {
            return null;
        }
        List<Suit> suits = getSuitsForRank(r);
        cards.add(makeCard(suits.get(0), r));
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.FOUR_OF_A_KIND, cards.toArray(new Card[5]));
    }

    /**
     * FULL HOUSE
     *
     * @return
     */
    private HandRank dHandRank() {
        Rank[] qRank = getRanksWithCount(3, 2);
        if (qRank.length < 1) {
            return null;
        }
        List<Card> cards = new ArrayList();
        Arrays.sort(qRank, rc);
        ArrayUtils.reverse(qRank);
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
//            System.out.println(suits);
            for (Suit suit : suits) {
                cards.add(makeCard(suit, r));
                if(cards.size() == 5){
                    Collections.sort(cards, this);
                    return new SimpleHandRank(HandType.FULL_HOUSE, cards.toArray(new Card[5]));
                }
            }
        }
        qRank = getRanksWithCount(2, 1);
        if (qRank.length != 1) {
            return null;
        }
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards.add(makeCard(suit, r));
            }
        }
        if (cards.size() != 5) {
            return null;
        }
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.FULL_HOUSE, cards.toArray(new Card[5]));
    }

    /**
     * FLUSH
     *
     * @return
     */
    private HandRank eHandRank() {
        Entry<Suit, Rank[]> flush = getFlushEntry();
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
        if (Objects.isNull(cons) || cons.length < 5) {
            cons = getWheelRanks(ranks);
            if (Objects.isNull(cons)) {
                return null;
            }
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
        List<Card> cards = new ArrayList();
        for (Suit suit : suits) {
            cards.add(makeCard(suit, r));
        }
        qRank = getRanksWithCount(1, 2);
        if (qRank.length != 2) {
            return null;
        }
        for (Rank rank : qRank) {
            suits = getSuitsForRank(rank);
            cards.add(makeCard(suits.get(0), rank));
        }
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.THREE_OF_A_KIND, cards.toArray(new Card[5]));
    }

    /**
     * TWO PAIR
     *
     * @return
     */
    private HandRank hHandRank() {
        Rank[] qRank = getRanksWithCount(2, 2);
        if (qRank.length < 1) {
            return null;
        }
        List<Card> cards = new ArrayList();
        Arrays.sort(qRank, rc);
        ArrayUtils.reverse(qRank);
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
//            System.out.println(suits);
            for (Suit suit : suits) {
                cards.add(makeCard(suit, r));
                if(cards.size() == 5){
                    return new SimpleHandRank(HandType.FULL_HOUSE, cards.toArray(new Card[5]));
                }
            }
        }
        qRank = getRanksWithCount(1, 1);
        if (qRank.length < 1) {
            return null;
        }
        Arrays.sort(qRank, rc);
        ArrayUtils.reverse(qRank);
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards.add(makeCard(suit, r));
            }
        }
        if (cards.size() != 5) {
            return null;
        }
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.TWO_PAIR, cards.toArray(new Card[5]));
    }

    /**
     * PAIR
     *
     * @return
     */
    private HandRank iHandRank() {
        Rank[] qRank = getRanksWithCount(2, 1);
        if (qRank.length != 1) {
            return null;
        }
        List<Card> cards = new ArrayList();
        int index = 0;
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards.add(makeCard(suit, r));
            }
        }
        qRank = getRanksWithCount(1, 3);
        if (qRank.length != 3) {
            return null;
        }
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards.add(makeCard(suit, r));
            }
        }
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.PAIR, cards.toArray(new Card[5]));
    }

    /**
     * HIGH
     *
     * @return
     */
    private HandRank jHandRank() {
        Rank[] qRank = getRanksWithCount(1, 5);
        if (qRank.length != 5) {
            return null;
        }
        List<Card> cards = new ArrayList();
        for (Rank r : qRank) {
            List<Suit> suits = getSuitsForRank(r);
            for (Suit suit : suits) {
                cards.add(makeCard(suit, r));
            }
        }
        Collections.sort(cards, this);
        return new SimpleHandRank(HandType.HIGH, cards.toArray(new Card[5]));
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

    private Rank getHighestRankNotEqualTo(Rank r){
        Rank rank = this.getHighestRank();
        
        if(rank == r){
            rank = this.getNextHighestRank(rank);
        }
        return rank;
    }
    
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

    private Entry<Suit, Rank[]> getFlushEntry() {
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
                Collections.sort(consRanks, rc);
                return consRanks.toArray(new Rank[5]);
            }
            if(consRanks.isEmpty()){
                consRanks.add(r);
            }else{
                if(consRanks.get(consRanks.size() - 1).value() - r.value() == 1){
                    consRanks.add(r);
                }else{
                    consRanks.clear();
                    consRanks.add(r);
                }
            }
        }
        return null;
    }

    private Rank[] getWheelRanks(Rank[] ranks) {
        List<Rank> wheelRanks = new ArrayList();

        int i;
        int rIndex;
        for (i = 0; i < ranks.length; i++) {
            Rank r = ranks[i];
            rIndex = ArrayUtils.indexOf(ranks, Rank.getRank(i + 2));
            if (rIndex >= 0) {
                wheelRanks.add(ranks[rIndex]);
            }
        }
        rIndex = ArrayUtils.indexOf(ranks, Rank.ACE);
        if (rIndex >= 0) {
            wheelRanks.add(ranks[rIndex]);
        }
        if (wheelRanks.size() != 5) {
            return null;
        }
        return wheelRanks.toArray(new Rank[5]);
    }

    private boolean isConsecutive(Card[] cards) {
        if (Objects.isNull(cards)) {
            return false;
        }
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

    /**
     * A 5 high straight flush, with ace as the low card
     *
     * @param cards
     * @return
     */
    private boolean isWheel(Card[] cards) {
        if (Objects.nonNull(cards) && cards.length == 5) {
            Arrays.sort(cards, this);
            int i, j;
            boolean isConsec = false;
            for (i = cards.length - 2, j = i - 1; j >= 0; i--, j--) {
                if (Math.abs(cards[j].getRank().value() - cards[i].getRank().value()) != 1) {
                    isConsec = true;
                } else {
                    break;
                }
            }
            return isConsec
                    && cards[0].getRank() == Rank.TWO
                    && cards[cards.length - 1].getRank() == Rank.ACE;
        }
        return false;
    }

    private Card makeCard(Suit suit, Rank rank) {
        return new SimpleCard(suit, rank);
    }

    @Override
    public int compare(Card o1, Card o2) {
        if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
            return o1.compareTo(o2);
        } else if (Objects.isNull(o1)) {
            return 1;
        } else {
            return -1;
        }
    }

    private boolean hasValidHand() {
        int count = 0;
        for (Integer rCount : rankHistogram.values()) {
            if (Objects.nonNull(rCount)) {
                count += rCount;
                if (count >= 5 && count <= 7) {
                    return true;
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
