/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.holdempoker.app.impl.util;

import com.mac.holdempoker.app.Card;
import com.mac.holdempoker.app.Player;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacDerson
 */
public class PlayerOrderImplTest {
    
    public PlayerOrderImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDealOrder method, of class PlayerOrderImpl.
     */
    @Test
    public void testGetDealOrder() {
        System.out.println("getDealOrder");
        PlayerOrderImpl instance = null;
        List<Player> expResult = null;
        List<Player> result = instance.getDealOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compare method, of class PlayerOrderImpl.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Card o1 = null;
        Card o2 = null;
        PlayerOrderImpl instance = null;
        int expResult = 0;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
