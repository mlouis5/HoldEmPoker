/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.util.CommunityObserver;
import com.mac.holdempoker.app.util.PlayerObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mac
 */
public class StraightFlush implements HandRank, CommunityObserver, PlayerObserver {

    private final List<Card> hand;
    private Flush flush;
    private Straight straight;

    public StraightFlush() {
        hand = new ArrayList(7);
        flush = new Flush();
        straight = new Straight();
    }

    @Override
    public void accept(Card card) {
        if (Objects.nonNull(card)) {
//            hand.add(card);
            flush.accept(card);
            straight.accept(card);
        }
    }

    @Override
    public Card[] getHand() {
        
        Card[] f = flush.getHand();
        Card[] s = straight.getHand();
        
        System.out.println("f: " + Arrays.toString(f));
        System.out.println("s: " + Arrays.toString(s));
        
        if(!flush.isValidHand() || !straight.isValidHand()){
            return new Card[0];
        }
//        Card[] f = flush.getHand();
//        Card[] s = straight.getHand();
//        
//        System.out.println("f: " + Arrays.toString(f));
//        System.out.println("s: " + Arrays.toString(s));
        
        for(int i = 0; i < 5; i++){
            if(f[i].getRank() != s[i].getRank()){
                return new Card[0];
            }
        }
        return s;
//        Collections.sort(hand, this);
//        int i;
//        List<Card> finalList = new ArrayList(5);
//        Card lastCard = null;
//        for (i = hand.size() - 1; i >= 0; i--) {
//            Card c = hand.get(i);
//            System.out.println("lastCard: " + lastCard + "\t" + "current card: " + c);
//            if (Objects.isNull(lastCard)) {
//                finalList.add(c);
//                lastCard = c;
//            } else {
//                if (lastCard.getRank().value() - c.getRank().value() == 1
//                        && lastCard.getSuit() == c.getSuit()) {
//                    finalList.add(c);
//                    lastCard = c;
//                } else {
//                    if(lastCard.getRank().value() - c.getRank().value() == 1
//                            && lastCard.getSuit() != c.getSuit()){
//                        continue;
//                    }
//                    finalList.clear();
//                    lastCard = null;
//                    i++;
//                }
//            }
//            if (c.getRank().value() == 2) {
//                break;
//            }
//        }
//        Collections.sort(finalList, this);
//        while (finalList.size() > 5) {
//            finalList.remove(0);
//        }
//        int size = finalList.size();
//        if (size == 4 && ((finalList.get(size - 1).getRank().value()) == 5
//                && (finalList.get(0).getRank().value()) == 2)) {
//            if (hand.get(hand.size() - 1).getRank().value() == 14) {
//                finalList.add(hand.get(hand.size() - 1));
//                return finalList.toArray(new Card[5]);
//            }
//        } else if (size == 5) {
//            return finalList.toArray(new Card[5]);
//        }
//        return new Card[0];
    }

    @Override
    public HandType getHandType() {
        return HandType.STRAIGHT_FLUSH;
    }

    @Override
    public void dealt(Card... cards) {
        for (Card card : cards) {
            accept(card);
        }
    }

    @Override
    public void haveCard(Card card) {
        dealt(card);
    }

    @Override
    public void clearHand() {
        hand.clear();
    }

    @Override
    public boolean isValidHand() {
        return getHand().length == 5;
    }
}
