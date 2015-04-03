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
import org.junit.Ignore;
import org.springframework.util.Assert;

/**
 *
 * @author Mac
 */
public class HighTest {
    
    private static High instance;
    
    public HighTest() {
        instance = new High();
    }
    
    @Before
    public void setUp() {
        Card[] AllCards = {new SimpleCard(Suit.DIAMOND, Rank.TWO), 
        new SimpleCard(Suit.HEART, Rank.FOUR), new SimpleCard(Suit.SPADE, Rank.TEN),
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
     * Test of getHandType method, of class Flush.
     */
    @Test
    public void testGetHandType() {
        assertEquals(HandType.HIGH, instance.getHandType());
    }

    /**
     * Test of getHand method, of class High.
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
