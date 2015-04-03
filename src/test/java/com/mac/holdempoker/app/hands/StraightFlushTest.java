/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.impl.SimpleCard;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.util.Assert;

/**
 *
 * @author Mac
 */
public class StraightFlushTest {
    
    private static StraightFlush instance;
    
    public StraightFlushTest() {
        instance = new StraightFlush();
    }
    
    @Before
    public void setUp() {
        Card[] AllCards = {new SimpleCard(Suit.CLUB, Rank.JACK), 
        new SimpleCard(Suit.CLUB, Rank.TEN), new SimpleCard(Suit.CLUB, Rank.NINE),
        new SimpleCard(Suit.CLUB, Rank.EIGHT), new SimpleCard(Suit.CLUB, Rank.SEVEN),
        new SimpleCard(Suit.DIAMOND, Rank.NINE)};
        List<Card> cards = Arrays.asList(AllCards);
        
        for (Card card : cards) {
            instance.accept(card);
        }
    }
    
    @After
    public void tearDown() {
        instance.clearHand();
    }

    /**
     * Test of getHand method, of class StraightFlush.
     */
    @Test
    public void testGetHand() {
        System.out.println("getHand");
        Card[] result = instance.getHand();
        System.out.println("StraightFlush: " + result);
        System.out.println("test results");
        System.out.println(Arrays.toString(result));
        Assert.noNullElements(result);
        Assert.isTrue(result.length == 5);
    }

    /**
     * Test of getHandType method, of class StraightFlush.
     */
    @Test
    public void testGetHandType() {
        assertEquals(HandType.STRAIGHT_FLUSH, instance.getHandType());
    }
    
}
