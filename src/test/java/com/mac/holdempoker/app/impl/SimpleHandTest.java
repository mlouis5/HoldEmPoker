/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Hand;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.ACE));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.KING));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.QUEEN));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.JACK));
        hand.addToHand(new SimpleCard(Suit.DIAMOND, Rank.THREE));
        hand.addToHand(new SimpleCard(Suit.CLUB, Rank.TEN));
        hand.addToHand(new SimpleCard(Suit.SPADE, Rank.FOUR));
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
        
        Card[] result = hand.getHand();
        System.out.println(hand.getHandType());
        System.out.println("hand:" + Arrays.toString(result));
//        Assert.isTrue(hand.getHandType() == HandType.FULL_HOUSE);
        
        SimpleHandAggregator sha = new SimpleHandAggregator();
        System.out.println(sha.scoreHand(hand.getEvaluator()));
    }
    
}
