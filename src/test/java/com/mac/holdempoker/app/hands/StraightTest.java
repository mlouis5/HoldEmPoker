/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.hands;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.enums.HandType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mac
 */
public class StraightTest {
    
    public StraightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of accept method, of class Straight.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        Card card = null;
        Straight instance = new Straight();
        instance.accept(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHandType method, of class Straight.
     */
    @Test
    public void testGetHandType() {
        System.out.println("getHandType");
        Straight instance = new Straight();
        HandType expResult = null;
        HandType result = instance.getHandType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haveCard method, of class Straight.
     */
    @Test
    public void testHaveCard() {
        System.out.println("haveCard");
        Card card = null;
        Straight instance = new Straight();
        instance.haveCard(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dealt method, of class Straight.
     */
    @Test
    public void testDealt() {
        System.out.println("dealt");
        Card[] cards = null;
        Straight instance = new Straight();
        instance.dealt(cards);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHand method, of class Straight.
     */
    @Test
    public void testGetHand() {
        System.out.println("getHand");
        Straight instance = new Straight();
        Card[] expResult = null;
        Card[] result = instance.getHand();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
