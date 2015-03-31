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
public class HighTest {
    
    public HighTest() {
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
     * Test of getHandType method, of class High.
     */
    @Test
    public void testGetHandType() {
        System.out.println("getHandType");
        High instance = new High();
        HandType expResult = null;
        HandType result = instance.getHandType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHand method, of class High.
     */
    @Test
    public void testGetHand() {
        System.out.println("getHand");
        High instance = new High();
        Card[] expResult = null;
        Card[] result = instance.getHand();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class High.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        High instance = new High();
        boolean expResult = false;
        boolean result = instance.isValid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haveCard method, of class High.
     */
    @Test
    public void testHaveCard() {
        System.out.println("haveCard");
        Card card = null;
        High instance = new High();
        instance.haveCard(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dealt method, of class High.
     */
    @Test
    public void testDealt() {
        System.out.println("dealt");
        Card[] cards = null;
        High instance = new High();
        instance.dealt(cards);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compare method, of class High.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Card o1 = null;
        Card o2 = null;
        High instance = new High();
        int expResult = 0;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class High.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        High.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
