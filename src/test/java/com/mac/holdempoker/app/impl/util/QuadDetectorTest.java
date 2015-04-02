/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.Rank;
import com.mac.holdempoker.app.enums.Suit;
import com.mac.holdempoker.app.impl.SimpleCard;
import java.util.ArrayList;
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
 * @author MacDerson
 */
public class QuadDetectorTest {

    private static List<Card> cards;
    private static final Card[] expected = {
        new SimpleCard(Suit.CLUB, Rank.ACE), 
        new SimpleCard(Suit.HEART, Rank.ACE),
        new SimpleCard(Suit.SPADE, Rank.ACE),
        new SimpleCard(Suit.DIAMOND, Rank.ACE),
        new SimpleCard(Suit.SPADE, Rank.KING)};
    
    private static QuadDetector instance;

    public QuadDetectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {        
    }

    @Before
    public void setUp() {
        cards = Arrays.asList(HandDetectorTestSuite.cards);
        instance = new QuadDetector();
        for (Card card : cards) {
            instance.accept(card);
        }
    }

    @After
    public void tearDown() {
        cards = null;
    }

    /**
     * Test of accept method, of class QuadDetector.
     */
    @Test
    @Ignore
    public void testAccept() {
//        System.out.println("accept");
//        QuadDetector instance = new QuadDetector();
//        for (Card card : cards) {
//            instance.accept(card);
//        }
//        Card[] hand = instance.getHand();
//        System.out.println(Arrays.toString(hand));
        fail("Prototype");
    }

    /**
     * Test of getHand method, of class QuadDetector.
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

}
