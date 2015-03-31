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
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Mac
 */
public class Straight implements HandRank, CommunityObserver, PlayerObserver  {

    private final TreeSet<Card> hand;
    
    private int highIndex;
    private int lowIndex;
    private boolean isWheel;

    public Straight() {
        hand = new TreeSet(this);
        highIndex = -1;
        lowIndex = -1;
        isWheel = false;
    }
    
    @Override
    public HandType getHandType() {
        return HandType.STRAIGHT;
    }

    @Override
    public Card[] getHand() {
        if(!isValid()){
            return new Card[0];
        }
        return ArrayUtils.subarray(hand.toArray(new Card[hand.size()]), lowIndex + 1, highIndex + 1);
    }

    @Override
    public boolean isValid() {
        return isConsecutive() && hand.size() >= 5;
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
            hand.addAll(Arrays.asList(cards));
        }
    }
    
    public boolean isConsecutive(){
        Card[] cards = hand.toArray(new Card[hand.size()]);
        int i,j;
        boolean isCons = false;
        
        int diff = 1;
        for(i = cards.length - 1, j = i - 1; i >= 0 && j >= 0;){
            isCons = (cards[i].getRank().value() - cards[j].getRank().value() == diff);
            
            if(isCons){                
                highIndex = i;
                lowIndex = j >= 0 ? j : 0;
                j--;
                if(highIndex - lowIndex == 5){
                    break;
                }                
            }else{
                i--;
                j = i - 1;                
            }
            diff++;
        }
        isWheel = false;
        return highIndex - lowIndex == 5;
    }
    
    private boolean isWheel(){
        Card[] cards = hand.toArray(new Card[hand.size()]);
        int i,j;
        boolean isCons = false;
        
        int diff = 1;
        for(i = 0, j = i + 1; i < 4 && j < 4;){
            isCons = (cards[j].getRank().value() - cards[i].getRank().value() == diff);
            
            if(isCons){                
                highIndex = j;
                lowIndex = i;
                j++;
                if(highIndex - lowIndex == 4){
                    break;
                }                
            }else{
                i++;
                j = i + 1;                
            }
            diff++;
        }
        if(cards[cards.length - 1].getRank() == Rank.ACE){
            isWheel = true;
            highIndex = cards.length - 1;
        }
        return isWheel;
    }
    
    public static void main(String[] args){
        Straight h = new Straight();
        
        Card[] cards = {new SimpleCard(Suit.CLUB, Rank.ACE), new SimpleCard(Suit.HEART, Rank.JACK)
        , new SimpleCard(Suit.SPADE, Rank.TEN)};       
        
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
