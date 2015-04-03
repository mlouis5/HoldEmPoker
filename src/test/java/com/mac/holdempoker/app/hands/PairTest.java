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
public class PairTest {
    
    private static Pair instance;
    
    public PairTest() {
        instance = new Pair();
    }
    
    @Before
    public void setUp() {
        Card[] AllCards = {new SimpleCard(Suit.DIAMOND, Rank.TWO), 
        new SimpleCard(Suit.HEART, Rank.TWO), new SimpleCard(Suit.SPADE, Rank.TEN),
        new SimpleCard(Suit.CLUB, Rank.QUEEN), new SimpleCard(Suit.SPADE, Rank.KING),
        new SimpleCard(Suit.DIAMOND, Rank.FIVE)};
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
     * Test of getHandType method, of class Pair.
     */
    @Test
    public void testGetHandType() {
        assertEquals(HandType.PAIR, instance.getHandType());
    }

    /**
     * Test of getHand method, of class Pair.
     */
    @Test
    public void testGetHand() {
        System.out.println(getClass().getSimpleName() + ": getHand");
        Card[] result = instance.getHand();
        
        System.out.println("test results");
        System.out.println(Arrays.toString(result));
        Assert.noNullElements(result);
        Assert.isTrue(result.length == 5);
    }
    
}
