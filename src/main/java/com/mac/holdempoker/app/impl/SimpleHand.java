/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Community;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.impl.util.Histogram;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author MacDerson
 */
@Component
public class SimpleHand implements Hand{

    @Autowired
    @Qualifier("communityImpl")
    private Community community;
    
    private static final int MAX_HAND_SIZE = 7;
    private Card[] hand;
    private int handIndex;
    
    public SimpleHand(){
        hand = new Card[MAX_HAND_SIZE];
        this.handIndex = 0;
    }

    @Override
    public Card[] getHand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToHand(Card card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
    
    @Override
    public void setCommunityCards(Community communityCards) {
        this.community = communityCards;
    }

    @Override
    public void sortHand() {
        Arrays.sort(hand, this);
    }
    
    private Card[] getAllCards(Community community, Card[] hand){
        return ArrayUtils.addAll(community.getCommunityCards(), hand);
    }
    
    private Histogram getHistogram(Card[] allCards){
        Map<Rank, Integer> histogram = new HashMap();
        
        Histogram histo = new Histogram();
        for(Card card : allCards){
            histo.addToRankCount(card.getRank(), 1);
            histo.addToSuitCount(card.getSuit(), 1);
        }
        return histo;
    }

}
