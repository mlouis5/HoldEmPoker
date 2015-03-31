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
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.impl.SimpleCard;
import com.mac.holdempoker.app.util.CommunityObserver;
import com.mac.holdempoker.app.util.PlayerObserver;
import java.util.Arrays;
import java.util.TreeSet;

/**
 *
 * @author Mac
 */
public class High implements HandRank, CommunityObserver, PlayerObserver {

    private final TreeSet<Card> hand;

    public High() {
        hand = new TreeSet(this);
    }

    @Override
    public HandType getHandType() {
        return HandType.HIGH;
    }

    @Override
    public Card[] getHand() {
        if(hand.size() < 5){
            return new Card[0];
        }        
        if(hand.size() > 5){
            while(hand.size() > 5){
                hand.pollFirst();
            }
        }
        return hand.toArray(new Card[5]);
    }

    @Override
    public boolean isValid() {
        return this.hand.size() >= 5 && this.hand.size() <= 7;
    }
    
    @Override
    public void haveCard(Card card) {
        dealt(card);
    }
    
    @Override
    public void dealt(Card... cards) {
        if(isValidDeal(hand, cards)){
            hand.addAll(Arrays.asList(cards));
        }
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
    
    public static void main(String[] args){
        High h = new High();
        
        Card[] cards = {new SimpleCard(Suit.CLUB, Rank.ACE), new SimpleCard(Suit.HEART, Rank.EIGHT)
        , new SimpleCard(Suit.SPADE, Rank.FIVE)};       
        
        h.dealt(cards);
        
        cards = new Card[1];
        cards[0] = new SimpleCard(Suit.CLUB, Rank.KING);
        
        h.dealt(cards);
        
        cards = new Card[1];
        cards[0] = new SimpleCard(Suit.CLUB, Rank.TWO);
        
        cards = new Card[1];
        cards[0] = new SimpleCard(Suit.DIAMOND, Rank.NINE);
        
        h.dealt(cards);
        
        cards = new Card[1];
        cards[0] = new SimpleCard(Suit.DIAMOND, Rank.QUEEN);
        
        h.dealt(cards);
        
        Card[] allCards = h.getHand();
        
        for(Card c : allCards){
            System.out.println(c.print());
        }
        
        System.out.println(Arrays.toString(h.getHand()));
    }

}
