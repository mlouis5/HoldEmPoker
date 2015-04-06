/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.HandRank;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 * @author Mac
 */
public class SimpleHandTest {
    
    private final Hand hand;
    
    public SimpleHandTest() {
        hand = new SimpleHand();
    }
    
    @Before
    public void setUp() {
        hand.addToHand(new SimpleCard(Suit.HEART, Rank.KING));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.JACK));
        hand.addToHand(new SimpleCard(Suit.DIAMOND, Rank.NINE));
        hand.addToHand(new SimpleCard(Suit.HEART, Rank.SEVEN));
        hand.addToHand(new SimpleCard(Suit.SPADE, Rank.FIVE));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.THREE));
        hand.addToHand(new SimpleCard(Suit.SPADE, Rank.ACE));
    }
    
    @After
    public void tearDown() {
        hand.clearHand();
    }

    /**
     * Test of getHand method, of class SimpleHand.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetHand() throws Exception {
        System.out.println(getClass().getSimpleName() + ": getHand");
        
        HandRank hr = hand.getHandRank();
        Card[] result = hr.getHand();
        System.out.println(hr.getHandType());
        System.out.println("hand:" + Arrays.toString(result));
        Assert.isTrue(hr.getHandType() == HandType.HIGH);
        
        SimpleHandAggregator sha = new SimpleHandAggregator();
        System.out.println(sha.scoreHand(hand));
    }
    
}
