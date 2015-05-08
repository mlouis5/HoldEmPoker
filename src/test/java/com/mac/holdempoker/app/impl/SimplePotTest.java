/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl;

import com.mac.holdempoker.app.MoneyAction;
import com.mac.holdempoker.app.Player;
import com.mac.holdempoker.app.actions.Bet;
import com.mac.holdempoker.app.actions.Call;
import com.mac.holdempoker.app.enums.RoundType;
import com.mac.holdempoker.game.impl.util.WinnerContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacDerson
 */
public class SimplePotTest {
    
    public SimplePotTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of newRound method, of class SimplePot.
     */
    @Test
    public void testNewRound() {
        System.out.println("newRound");
        RoundType roundType = null;
        SimplePot instance = new SimplePot();
        instance.newRound(roundType);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of increasePot method, of class SimplePot.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        MoneyAction action = new Bet();
        action.setAmount(30);
        action.setActingPlayer(new SimplePlayer());
        SimplePot instance = new SimplePot();
        instance.increasePot(action);
        System.out.println(instance.getPurse());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of multiplePlayersWon method, of class SimplePot.
     */
    @Test
    public void testMultiplePlayersWon() {
        System.out.println("multiplePlayersWon");
        WinnerContainer wc = null;
        SimplePot instance = new SimplePot();
        instance.multiplePlayersWon(wc);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of singlePlayerWon method, of class SimplePot.
     */
    @Test
    public void testSinglePlayerWon() {
        System.out.println("singlePlayerWon");
        Player p = new SimplePlayer();
        p.setPlayerId("test");
        SimplePot instance = new SimplePot();
        
        MoneyAction action = new Bet();
        action.setAmount(20);
        action.setActingPlayer(p);
        
        instance.increasePot(action);
        
        action = new Call();
        action.setAmount(30);
        action.setActingPlayer(new SimplePlayer());
        
        instance.increasePot(action);
        
        instance.singlePlayerWon(p);
//        System.out.println(instance.getPurse());
        int result = instance.getPurse();
        assertEquals(40, p.getStack());
    }

    /**
     * Test of getPurse method, of class SimplePot.
     */
    @Test
    public void testGetPurse() {
        System.out.println("getPurse");
        SimplePot instance = new SimplePot();
        
        MoneyAction action = new Bet();
        action.setAmount(30);
        action.setActingPlayer(new SimplePlayer());
        
        instance.increasePot(action);
        
        action = new Call();
        action.setAmount(30);
        action.setActingPlayer(new SimplePlayer());
        
        instance.increasePot(action);
        
        System.out.println(instance.getPurse());
        int result = instance.getPurse();
        assertEquals(60, result);
    }
    
}
