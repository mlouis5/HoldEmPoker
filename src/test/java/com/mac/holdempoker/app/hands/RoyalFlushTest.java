/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
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
public class RoyalFlushTest {
    
    private static List<Card> cards;
    
    private static RoyalFlush instance;
    
    public RoyalFlushTest() {
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
        instance = new RoyalFlush();
        for (Card card : cards) {
            instance.accept(card);
        }
    }
    
    @After
    public void tearDown() {
        cards = null;
    }

    /**
     * Test of accept method, of class RoyalFlush.
     */
    @Test
    @Ignore
    public void testAccept() {
        System.out.println("accept");
        Card card = null;
        RoyalFlush instance = new RoyalFlush();
        instance.accept(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHand method, of class RoyalFlush.
     */
    @Test
    public void testGetHand() {
        System.out.println("getHand");
        Card[] result = instance.getHand();
//        Arrays.sort(expected);
        System.out.println("test results");
        System.out.println(Arrays.toString(result));
        Assert.noNullElements(result);
        Assert.isTrue(result.length == 5);
//        assertArrayEquals(expected, result);
    }

    /**
     * Test of getHandType method, of class RoyalFlush.
     */
    @Test
    @Ignore
    public void testGetHandType() {
        System.out.println("getHandType");
        RoyalFlush instance = new RoyalFlush();
        HandType expResult = null;
        HandType result = instance.getHandType();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of dealt method, of class RoyalFlush.
     */
    @Test
    @Ignore
    public void testDealt() {
        System.out.println("dealt");
        Card[] cards = null;
        RoyalFlush instance = new RoyalFlush();
        instance.dealt(cards);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haveCard method, of class RoyalFlush.
     */
    @Test
    @Ignore
    public void testHaveCard() {
        System.out.println("haveCard");
        Card card = null;
        RoyalFlush instance = new RoyalFlush();
        instance.haveCard(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
