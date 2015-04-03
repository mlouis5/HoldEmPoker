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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.util.Assert;

/**
 *
 * @author Mac
 */
public class QuadsTest {
        
    private static Quads instance;
    
    public QuadsTest() {
        instance = new Quads();
    }
    
    @Before
    public void setUp() {
        Card[] AllCards = {new SimpleCard(Suit.CLUB, Rank.FOUR), 
        new SimpleCard(Suit.DIAMOND, Rank.FOUR), new SimpleCard(Suit.HEART, Rank.FOUR),
        new SimpleCard(Suit.CLUB, Rank.JACK), new SimpleCard(Suit.CLUB, Rank.TEN),
        new SimpleCard(Suit.SPADE, Rank.FOUR)};
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
     * Test of getHandType method, of class Quads.
     */
    @Test
    public void testGetHandType() {
        assertEquals(HandType.FOUR_OF_A_KIND, instance.getHandType());
    }

    /**
     * Test of getHand method, of class Quads.
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
