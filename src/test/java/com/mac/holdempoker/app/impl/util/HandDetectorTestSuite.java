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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author MacDerson
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.mac.holdempoker.app.impl.util.PairsDetectorTest.class, 
    com.mac.holdempoker.app.impl.util.HighDetectorTest.class, 
    com.mac.holdempoker.app.impl.util.TripsDetectorTest.class, 
    com.mac.holdempoker.app.impl.util.BoatDetectorTest.class, 
    com.mac.holdempoker.app.impl.util.QuadDetectorTest.class, 
    com.mac.holdempoker.app.impl.util.PairDetectorTest.class, 
    com.mac.holdempoker.app.impl.util.StraightDetectorTest.class, 
    com.mac.holdempoker.app.impl.util.FlushDetectorTest.class
})
public class HandDetectorTestSuite {
    
    public static final Card[] cards = {
        new SimpleCard(Suit.CLUB, Rank.ACE), 
        new SimpleCard(Suit.HEART, Rank.ACE),
        new SimpleCard(Suit.SPADE, Rank.ACE),
        new SimpleCard(Suit.DIAMOND, Rank.ACE),
        new SimpleCard(Suit.SPADE, Rank.KING),
        new SimpleCard(Suit.SPADE, Rank.JACK),
        new SimpleCard(Suit.DIAMOND, Rank.FOUR)};

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
