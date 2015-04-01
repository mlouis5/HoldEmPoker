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
import com.mac.holdempoker.app.impl.util.BoatHistogram;
import com.mac.holdempoker.app.util.CommunityObserver;
import com.mac.holdempoker.app.util.PlayerObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mac
 */
public class Quads implements HandRank, CommunityObserver, PlayerObserver{

    private final BoatHistogram histo;
    private final List<Card> hand;
    
    public Quads(){
        histo = new BoatHistogram();
        hand = new ArrayList();
    }
    @Override
    public HandType getHandType() {
        return HandType.FOUR_OF_A_KIND;
    }

    @Override
    public Card[] getHand() {
        if(!isValid()){
            return new Card[0];
        }
        Rank quad = histo.getQuad();
        Rank single = histo.getSingle();
        
        Card[] all = new SimpleCard[5];
        Card s = getSingle(single);        
        
        System.arraycopy(getQuads(quad), 0, all, 0, 4);
        all[4] = getSingle(single);
        Arrays.sort(all, this);
        return all;
    }

    @Override
    public boolean isValid() {
       return histo.isBoat();
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }

    @Override
    public void haveCard(Card card) {
        dealt(card);
    }
    
    @Override
    public void dealt(Card... cards) {
        if(isValidDeal(hand, cards)){
            for(Card card : cards){
                hand.add(card);
                histo.haveCard(card);
            }
        }
    }
    
    private Card[] getQuads(Rank rank){
        Card[] q = new SimpleCard[4];
        int index = 0;
        for(Card card : hand){
            if(card.getRank().value() == rank.value()){
                q[index++] = card;
            }
        }
        return q;
    }
    
    private Card getSingle(Rank rank){
        Card s = null;
        for(Card card : hand){
            if(card.getRank().value() == rank.value()){
                s = card;
                break;
            }
        }
        return s;
    }
    
    public static void main(String[] args){
        Quads h = new Quads();
        
        Card[] cards = {new SimpleCard(Suit.CLUB, Rank.ACE), new SimpleCard(Suit.HEART, Rank.ACE)
        , new SimpleCard(Suit.SPADE, Rank.ACE)};       
        
        h.dealt(cards);
        
        cards = new Card[1];
        cards[0] = new SimpleCard(Suit.CLUB, Rank.KING);
        
        h.dealt(cards);
        
        cards = new Card[1];
        cards[0] = new SimpleCard(Suit.CLUB, Rank.TWO);
        
        cards = new Card[1];
        cards[0] = new SimpleCard(Suit.DIAMOND, Rank.ACE);
        
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
