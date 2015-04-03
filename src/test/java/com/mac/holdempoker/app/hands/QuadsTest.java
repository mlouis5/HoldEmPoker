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
public class QuadsTest {
    
    private static List<Card> cards;
    private static final Card[] expected = {
        new SimpleCard(Suit.CLUB, Rank.ACE), 
        new SimpleCard(Suit.HEART, Rank.ACE),
        new SimpleCard(Suit.SPADE, Rank.ACE),
        new SimpleCard(Suit.DIAMOND, Rank.ACE),
        new SimpleCard(Suit.SPADE, Rank.KING)};
    
    private static Quads instance;
    
    public QuadsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cards = Arrays.asList(HandsTestSuite.cards);
        instance = new Quads();
        for (Card card : cards) {
            instance.accept(card);
        }
    }
    
    @After
    public void tearDown() {
        cards = null;
    }

    /**
     * Test of accept method, of class Quads.
     */
    @Test
    @Ignore
    public void testAccept() {
        System.out.println("accept");
        Card card = null;
        Quads instance = new Quads();
        instance.accept(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHand method, of class Quads.
     */
    @Test
    public void testGetHand() {
        System.out.println("getHand");
        Card[] result = instance.getHand();
        Arrays.sort(expected);
        
        System.out.println(Arrays.toString(result));
        Assert.noNullElements(result);
        Assert.isTrue(result.length == 5);
        assertArrayEquals(expected, result);
    }

    /**
     * Test of getHandType method, of class Quads.
     */
    @Test
    @Ignore
    public void testGetHandType() {
        System.out.println("getHandType");
        Quads instance = new Quads();
        HandType expResult = null;
        HandType result = instance.getHandType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haveCard method, of class Quads.
     */
    @Test
    @Ignore
    public void testHaveCard() {
        System.out.println("haveCard");
        Card card = null;
        Quads instance = new Quads();
        instance.haveCard(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dealt method, of class Quads.
     */
    @Test
    @Ignore
    public void testDealt() {
        System.out.println("dealt");
        Card[] cards = null;
        Quads instance = new Quads();
        instance.dealt(cards);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
