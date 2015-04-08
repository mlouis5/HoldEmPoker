/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacDerson
 */
public class SimplePlayerTest {
    
    public SimplePlayerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPlayerName method, of class SimplePlayer.
     */
    @Test
    public void testGetPlayerName() {
        System.out.println("getPlayerName");
        SimplePlayer instance = new SimplePlayer();
        String expResult = "";
        String result = instance.getPlayerName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerNumber method, of class SimplePlayer.
     */
    @Test
    public void testSetPlayerNumber() {
        System.out.println("setPlayerNumber");
        int num = 0;
        SimplePlayer instance = new SimplePlayer();
        instance.setPlayerNumber(num);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerNumber method, of class SimplePlayer.
     */
    @Test
    public void testGetPlayerNumber() {
        System.out.println("getPlayerNumber");
        SimplePlayer instance = new SimplePlayer();
        int expResult = 0;
        int result = instance.getPlayerNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetHand method, of class SimplePlayer.
     */
    @Test
    public void testResetHand() {
        System.out.println("resetHand");
        SimplePlayer instance = new SimplePlayer();
        instance.resetHand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decreaseStack method, of class SimplePlayer.
     */
    @Test
    public void testDecreaseStack() {
        System.out.println("decreaseStack");
        int amount = 0;
        SimplePlayer instance = new SimplePlayer();
        instance.decreaseStack(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increaseStack method, of class SimplePlayer.
     */
    @Test
    public void testIncreaseStack() {
        System.out.println("increaseStack");
        int amount = 0;
        SimplePlayer instance = new SimplePlayer();
        instance.increaseStack(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haveSharedCards method, of class SimplePlayer.
     */
    @Test
    public void testHaveSharedCards() {
        System.out.println("haveSharedCards");
        Card[] cards = null;
        SimplePlayer instance = new SimplePlayer();
        instance.haveSharedCards(cards);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of haveHoleCard method, of class SimplePlayer.
     */
    @Test
    public void testHaveHoleCard() {
        System.out.println("haveHoleCard");
        Card card = null;
        SimplePlayer instance = new SimplePlayer();
        instance.haveHoleCard(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEliminated method, of class SimplePlayer.
     */
    @Test
    public void testIsEliminated() {
        System.out.println("isEliminated");
        SimplePlayer instance = new SimplePlayer();
        boolean expResult = false;
        boolean result = instance.isEliminated();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsDealer method, of class SimplePlayer.
     */
    @Test
    public void testSetIsDealer() {
        System.out.println("setIsDealer");
        boolean isDealer = false;
        SimplePlayer instance = new SimplePlayer();
        instance.setIsDealer(isDealer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsBigBlind method, of class SimplePlayer.
     */
    @Test
    public void testSetIsBigBlind() {
        System.out.println("setIsBigBlind");
        boolean isBigBlind = false;
        SimplePlayer instance = new SimplePlayer();
        instance.setIsBigBlind(isBigBlind);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsSmallBlind method, of class SimplePlayer.
     */
    @Test
    public void testSetIsSmallBlind() {
        System.out.println("setIsSmallBlind");
        boolean isSmallBlind = false;
        SimplePlayer instance = new SimplePlayer();
        instance.setIsSmallBlind(isSmallBlind);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsBigBlind method, of class SimplePlayer.
     */
    @Test
    public void testGetIsBigBlind() {
        System.out.println("getIsBigBlind");
        SimplePlayer instance = new SimplePlayer();
        boolean expResult = false;
        boolean result = instance.getIsBigBlind();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsSmallBlined method, of class SimplePlayer.
     */
    @Test
    public void testGetIsSmallBlined() {
        System.out.println("getIsSmallBlined");
        SimplePlayer instance = new SimplePlayer();
        boolean expResult = false;
        boolean result = instance.getIsSmallBlined();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsDealer method, of class SimplePlayer.
     */
    @Test
    public void testGetIsDealer() {
        System.out.println("getIsDealer");
        SimplePlayer instance = new SimplePlayer();
        boolean expResult = false;
        boolean result = instance.getIsDealer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActionOrder method, of class SimplePlayer.
     */
    @Test
    public void testSetActionOrder() {
        System.out.println("setActionOrder");
        int actionOrder = 0;
        SimplePlayer instance = new SimplePlayer();
        instance.setActionOrder(actionOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAction method, of class SimplePlayer.
     */
    @Test
    public void testSetAction() {
        System.out.println("setAction");
        Action action = null;
        SimplePlayer instance = new SimplePlayer();
        instance.setAction(action);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAction method, of class SimplePlayer.
     */
    @Test
    public void testGetAction() {
        System.out.println("getAction");
        SimplePlayer instance = new SimplePlayer();
        Action expResult = null;
        Action result = instance.getAction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerId method, of class SimplePlayer.
     */
    @Test
    public void testSetPlayerId() {
        System.out.println("setPlayerId");
        String pId = "";
        SimplePlayer instance = new SimplePlayer();
        instance.setPlayerId(pId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerId method, of class SimplePlayer.
     */
    @Test
    public void testGetPlayerId() {
        System.out.println("getPlayerId");
        SimplePlayer instance = new SimplePlayer();
        String expResult = "";
        String result = instance.getPlayerId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerEmail method, of class SimplePlayer.
     */
    @Test
    public void testSetPlayerEmail() {
        System.out.println("setPlayerEmail");
        String pEmail = "";
        SimplePlayer instance = new SimplePlayer();
        instance.setPlayerEmail(pEmail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerEmail method, of class SimplePlayer.
     */
    @Test
    public void testGetPlayerEmail() {
        System.out.println("getPlayerEmail");
        SimplePlayer instance = new SimplePlayer();
        String expResult = "";
        String result = instance.getPlayerEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerFirstName method, of class SimplePlayer.
     */
    @Test
    public void testSetPlayerFirstName() {
        System.out.println("setPlayerFirstName");
        String fName = "";
        SimplePlayer instance = new SimplePlayer();
        instance.setPlayerFirstName(fName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerFirstName method, of class SimplePlayer.
     */
    @Test
    public void testGetPlayerFirstName() {
        System.out.println("getPlayerFirstName");
        SimplePlayer instance = new SimplePlayer();
        String expResult = "";
        String result = instance.getPlayerFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerLastName method, of class SimplePlayer.
     */
    @Test
    public void testSetPlayerLastName() {
        System.out.println("setPlayerLastName");
        String lName = "";
        SimplePlayer instance = new SimplePlayer();
        instance.setPlayerLastName(lName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerLastName method, of class SimplePlayer.
     */
    @Test
    public void testGetPlayerLastName() {
        System.out.println("getPlayerLastName");
        SimplePlayer instance = new SimplePlayer();
        String expResult = "";
        String result = instance.getPlayerLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActionOrder method, of class SimplePlayer.
     */
    @Test
    public void testGetActionOrder() {
        System.out.println("getActionOrder");
        SimplePlayer instance = new SimplePlayer();
        int expResult = 0;
        int result = instance.getActionOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
