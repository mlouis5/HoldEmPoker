/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.Action;
import com.mac.holdempoker.app.Board;
import com.mac.holdempoker.app.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacDerson
 */
public class SimpleGameDealerTest {
    
    public SimpleGameDealerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setBoard method, of class SimpleGameDealer.
     */
    @Test
    public void testSetBoard() {
        System.out.println("setBoard");
        Board board = null;
        SimpleGameDealer instance = new SimpleGameDealer();
        instance.setBoard(board);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayers method, of class SimpleGameDealer.
     */
    @Test
    public void testSetPlayers() {
        System.out.println("setPlayers");
        Player[] players = null;
        SimpleGameDealer instance = new SimpleGameDealer();
        instance.setPlayers(players);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dealAround method, of class SimpleGameDealer.
     */
    @Test
    public void testDealAround() {
        System.out.println("dealAround");
        SimpleGameDealer instance = new SimpleGameDealer();
        instance.dealAround();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dealBoard method, of class SimpleGameDealer.
     */
    @Test
    public void testDealBoard() throws Exception {
        System.out.println("dealBoard");
        SimpleGameDealer instance = new SimpleGameDealer();
        instance.dealBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class SimpleGameDealer.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        Action action = null;
        SimpleGameDealer instance = new SimpleGameDealer();
        instance.actionPerformed(action);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
